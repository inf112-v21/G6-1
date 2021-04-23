package inf112.skeleton.app.game;


import inf112.skeleton.app.BoardItems.*;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.card.CardMoveLogic;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.graphics.TileLayers;
import inf112.skeleton.app.networking.GameClient;
import inf112.skeleton.app.networking.GameServer;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class Game implements IGame {


    public Player myHumanPlayer = new HumanPlayer(Direction.NORTH,69,Color.GREEN);
    public Graphics graphics;
    public ArrayList<Player> players = new ArrayList<>();
    public HashMap<Integer, ArrayList<HashMap<Integer, Action>>> allPlayerMoves = new HashMap<>();
    public GameServer server;
    GameClient client;
    public int myId;
    BoardItems boardItems = new BoardItems();
    public CheckPoint checkpoint = new CheckPoint();
    public GameType typeOfGameStarted = GameType.NONE;
    final CardMoveLogic cardMoveLogic = new CardMoveLogic();
    public GameScreen currentScreen = GameScreen.MENU;
    public GameScreen hostGameScreen = GameScreen.HOST;
    public GameScreen winScreen = GameScreen.WIN;
    public GameScreen loseScreen = GameScreen.LOSE;
    public int numberOfPlayersConnected;
    private boolean runOnceAtStartOfRound = false;
    private int countCardsPlayedPerRound = 0;

    @Override
    public Graphics startGame() {
        graphics = new Graphics(this);
        return graphics;
    }

    /**
     * InetAddress object created by the host. This is the only place server.run() should be used,
     * to avoid creating more than one server.
     *
     *
     */
    public InetAddress hostNewGame() {
        server = new GameServer();
        server.run();
        client = new GameClient(server.getAddress(),Game.this);
        return server.getAddress();
    }


    /**
     * Creates client object for user. This is also ran for the host
     *
     * @param ip InetAddress object, is being called properly in @chooseHostOrJoin()
     */
    public void joinNewGame(String ip) {
        InetAddress hostIp = null;
        try {
            hostIp = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        client = new GameClient(hostIp, Game.this);
    }


    /**
     * Create a list of players for a game, from a Hashmap containing player info provided by the server.
     * The Hashmap playerInfo contains player id and start position for all players connected to the game.
     *
     * @param playerInfo HashMap<Integer (player id),ArrayList<Float (XY position index: 0 = X, 1 = Y)>>
     * @return ArrayList<Player> list of players
     */
    public ArrayList<Player> createListOfPlayers(HashMap<Integer,ArrayList<Float>> playerInfo){
        Set<Integer> playerID = playerInfo.keySet();
        for(Integer player : playerID){
            players.add(new HumanPlayer(Direction.NORTH,player,Color.getPlayerColor(player)));

        }
        setStartPos(playerInfo);
        return players;
    }


    /**
     * Sets all players start position and initialize the local player
     * @param playerInfo HashMap<Integer (player id),ArrayList<Float (XY position index: 0 = X, 1 = Y)>>
     */
    public void setStartPos(HashMap<Integer,ArrayList<Float>> playerInfo){
        for(Player player: players){
            ArrayList<Float> playerStartPos = playerInfo.get(player.id);
            player.setPlayerStartXPosition(playerStartPos.get(0));
            player.setPlayerStartYPosition(playerStartPos.get(1));
            if(player.id == myId){
                initializeMyHumanPlayer(player);
            }

        }
    }


    /**
     * Sets the local human player equal the game class variable myHumanPlayer and
     * provide a start deck of card for the local player.
     * The local player to pass this method is the player in the players list with
     * the id matching the class variable myId.
     * @param player the local player
     */
    public void initializeMyHumanPlayer(Player player){
        myHumanPlayer = player;
        myHumanPlayer.playerDeck = cardMoveLogic.playerDeck();
    }


    /**
     * This method returns a Hashmap of cards the local player want to play this round with the local players id as key.
     * It converts the chosenCard list from the local player to a HashMap that ist possible to send
     * via network. The card Hashmap has card priority as key and its action as value.
     *
     * @return HashMap<Integer (playerID), ArrayList<HashMap<Integer(Card Priority), Action (Card action)>>>
     */
    public HashMap<Integer, ArrayList<HashMap<Integer, Action>>> getPlayerActionList(){
        HashMap<Integer, ArrayList<HashMap<Integer, Action>>>  sendAbleChosenCards = new HashMap();
        ArrayList<HashMap<Integer, Action>> sendAbleCards =
                cardMoveLogic.convertToSendAbleCard(myHumanPlayer.chosenCards);
        sendAbleChosenCards.put(myHumanPlayer.id,sendAbleCards);
        return sendAbleChosenCards;
    }


    /**
     * This method takes a HashMap<Integer (playerID), ArrayList<HashMap<Integer(Card Priority), Action (Card action)>>>
     * containing all players cards. It then converts them into card objects and add the list of card object to
     * every players chosenCard list by player id.
     * @param actionList Hashmap
     */
    public void giveAllPlayersCardObjects(HashMap<Integer, ArrayList<HashMap<Integer, Action>>> actionList){
        for(Player player : players){
            ArrayList<HashMap<Integer, Action>> sendAbleCard = actionList.get(player.id);
            ArrayList<Card> cardObject = cardMoveLogic.convertToCardObject(sendAbleCard);
            player.chosenCards = cardObject;
        }
    }


    /**
     * Adds all players chosenCard list as value to a Hashmap with the players id as key
     * @return  HashMap<Integer (Player id), ArrayList<Card>>
     */
    public HashMap<Integer, ArrayList<Card>> playerMoves(){
        HashMap<Integer, ArrayList<Card>> playerMoves = new HashMap();
        for(Player player : players){
            playerMoves.put(player.id,player.chosenCards);
        }
        return playerMoves;
    }


    /**
     * Reset the chosenCard list for all players to make them ready from the next round
     * @param players list of players in the game
     */
    public void resetOtherPlayers(ArrayList<Player> players){
        for(Player player : players){
            player.chosenCards = new ArrayList<>();
        }
    }

    /**
     * Checks how many players are in the started game. The number comes from the server-side. Used to keep
     * track of how many cards to be played per round.
     *
     */
    public void connectedPlayers(int totalPlayersConnectedToServer){
        numberOfPlayersConnected = totalPlayersConnectedToServer;
    }



    /**
     * Finds the largest size of a list found in the playerMoves across all keys.
     *
     * @param playerMoves
     * @return
     */
    public int findMaxValue( HashMap<Integer, ArrayList<Card>> playerMoves){
        int maxValue = 0;
        Set<Integer> keySet = playerMoves.keySet();

        for(Integer key : keySet){
            if(playerMoves.get(key).size() > maxValue){
                maxValue = playerMoves.get(key).size();
            }
        }
        return maxValue;
    }

    /**
     * Finds the player(s) to move, based on which players hold the highest amount of cards.
     *
     * @return Returns all the players which havent played their card yet in a hashmap
     */

    public HashMap<Integer, ArrayList<Card>> playersToMove(){
        HashMap<Integer, ArrayList<Card>> playerMoves = playerMoves();
        int maxValue = findMaxValue(playerMoves);
        Set<Integer> keySet = playerMoves.keySet();
        HashMap<Integer, ArrayList<Card>> playersToMove = new HashMap<>();

        for(Integer key : keySet){
            if(playerMoves.get(key).size() != 0 && playerMoves.get(key).size() == maxValue){
                playersToMove.put(key, playerMoves.get(key));
            }
        }
        return playersToMove;
    }

    /**
     * Uses the hashmap from playersToMove and checks which of the players has played the card with the
     * highest priority
     *
     * @return
     */

    public HashMap<Integer, ArrayList<Card>> moveThisPlayer(){

        HashMap<Integer, ArrayList<Card>> playerMoves = playersToMove();
        HashMap<Integer, ArrayList<Card>> moveThisPlayer = new HashMap<>();

        Set<Integer> keySet = playerMoves.keySet();
        int highestValuedPriorityCard = 0;
        Player player = null;

        for(Integer key : keySet){
            if(playerMoves.get(key).get(0).priority > highestValuedPriorityCard){

                ArrayList<Card> cardToPlay = new ArrayList<>();
                moveThisPlayer = new HashMap<>();

                highestValuedPriorityCard = playerMoves.get(key).get(0).priority;
                player = players.get(key);
                
                cardToPlay.add(playerMoves.get(key).get(0));
                moveThisPlayer.put(key,cardToPlay);
            }
        }
        player.chosenCards.remove(0);
        return moveThisPlayer;
    }


    /**
     * Checks if all cards has been played, and if os resets all players' cards and gives players new cards to prepare
     * for a new round.
     *
     */
    public void keepPlaying() {
        if (!allPlayerMoves.isEmpty()) {
            countCardsPlayedPerRound++;
            int amountOfCardsToBePlayedEachRound = players.size() * 5;
            if (countCardsPlayedPerRound == amountOfCardsToBePlayedEachRound+1) {
                myHumanPlayer.resetPlayer(myHumanPlayer);
                resetOtherPlayers(players);
                allPlayerMoves = new HashMap<>();
                countCardsPlayedPerRound = 0;
                runOnceAtStartOfRound = false;
            }
        }
    }

    /**
     * Method to activate all board items. This will only execute if all players has played their card for the given
     * round.
     *
     * @param player
     * @param layer
     */

    public void boardItemsMove(Player player, TileLayers layer){
        int amountOfCardsToBePlayedEachRound = players.size() * 5;
        if(countCardsPlayedPerRound % players.size()  == 0 &&
                countCardsPlayedPerRound <= amountOfCardsToBePlayedEachRound+1) {
            boardItems.activateBoardItems(players, layer);
            checkpoint.findCheckpoints(player, layer.checkpoint);
        }
    }

    /**
     * Main function for multiplayer round.
     *
     * @param layer
     */
    public void multiplayerRound(TileLayers layer){
        keepPlaying();
        if(!allPlayerMoves.isEmpty()) {
            if(runOnceAtStartOfRound == false ){
                giveAllPlayersCardObjects(allPlayerMoves);
                runOnceAtStartOfRound = true;
            }

            HashMap<Integer, ArrayList<Card>> moveThisPlayer = moveThisPlayer();
            int playerId = (int) moveThisPlayer.keySet().toArray()[0];
            Player player = players.get(playerId);
            Card move = moveThisPlayer.get(playerId).get(0);
            player.doPlayerMove(move, layer);
            boardItemsMove(player,layer);

            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean createSinglePlayer(){
        myHumanPlayer = new HumanPlayer(Direction.NORTH,69,Color.GREEN);
        myHumanPlayer.playerDeck = cardMoveLogic.playerDeck();
        return false;
    }

    /**
     * Keeps count to check when player has played their 5 cards to avoid the program trying to play next card when
     * there is none
     *
     * @param player
     * @param layer
     */
    public void cardCount(Player player, TileLayers layer){

        if(player.chosenCards.isEmpty() && player.movedCards.size()==5){

            player.resetPlayer(player);
        }
    }

    /**
     * When playing single player, this executed the moves and waits in between each move.
     *
     * @param layer
     */
    public void doSinglePlayerMove(TileLayers layer){
        ArrayList<Player> myHumanPlayerList = new ArrayList<>(Arrays.asList(myHumanPlayer));
        if(myHumanPlayer.ready && !myHumanPlayer.chosenCards.isEmpty()){
            myHumanPlayer.doPlayerMove(myHumanPlayer.chosenCards.remove(0), layer);
            boardItems.activateBoardItems(myHumanPlayerList, layer);
            checkpoint.findCheckpoints(myHumanPlayer, layer.checkpoint);
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cardCount(myHumanPlayer, layer);

    }


    /**
     * This updates the location of all players in the game during the game session.
     *
     * @param playerInfo
     */
    public void updatePlayerInfo(HashMap<Integer, ArrayList<Float>> playerInfo) {
        for (Player player : players) {
            ArrayList<Float> useThisToUpdateCoordinates = playerInfo.get(player.id);
            player.playerCurrentXPosition = useThisToUpdateCoordinates.get(0);
            player.playerCurrentYPosition = useThisToUpdateCoordinates.get(1);
        }
    }
}