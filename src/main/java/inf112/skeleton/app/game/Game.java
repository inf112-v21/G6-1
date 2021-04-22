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


    public Player myHumanPlayer;
    public ArrayList<Player> players = new ArrayList<>();
    public HashMap<Integer, ArrayList<HashMap<Integer, Action>>> allPlayerMoves = new HashMap<>();
    GameServer server;
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


    @Override
    public Graphics startGame() {
        return new Graphics(this);
    }

    public void chooseHostOrJoin () {
        Scanner HostOrJoin = new Scanner(System.in);
        System.out.println("Host (1), join (2) or start single player (3)?: ");

        String choice = HostOrJoin.nextLine();
        System.out.println("You choose " + choice);

        if(choice.equals("1")){
            hostNewGame("RiskyExchange");
            typeOfGameStarted = GameType.NETWORK_HOST;
        }
        else if(choice.equals("2")){
            InetAddress hostIp = null;
            Scanner askForIpAddress = new Scanner(System.in);
            System.out.println("Please enter the server IP to join: ");

            String ChosenIP = askForIpAddress.nextLine();
            try {
                hostIp = InetAddress.getByName(ChosenIP);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            System.out.println(hostIp.getHostAddress());
            typeOfGameStarted = GameType.NETWORK_JOIN;
            joinNewGame(hostIp);

        }
        else if(choice.equals("3")){
            System.out.println("Single player selected");
            typeOfGameStarted = GameType.SINGLE_PLAYER;
        }
        else {
            System.out.println("Please enter 1 or 2 when asked to");
            chooseHostOrJoin();
        }
    }


    /**
     * InetAddress object created by the host. This is the only place server.run() should be used,
     * to avoid creating more than one server.
     *
     * @param map - What map to be used in the hosted game.
     */
    public InetAddress hostNewGame(String map) {
        server = new GameServer(map);
        server.run();
        client = new GameClient(server.getAddress(),Game.this);
        return server.getAddress();
    }


    /**
     * Creates client object for user. Might need more in this method.
     *
     * @param ip InetAddress object, is being called properly in @chooseHostOrJoin()
     */
    public void joinNewGame(InetAddress ip) {
        client = new GameClient(ip, Game.this);
    }


    /**
     * Create a list of players for a game, from a Hashmap containing player info provided by the server.
     * The Hashmap playerInfo contains player id and start position for all players connected to the game.
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
     * Does a multiplayer round when the server sends the list of all moves for all players
     * @param layer TileLayers
     */
    public void doRoundNetworkMultiplayer(TileLayers layer) {
        if(!allPlayerMoves.isEmpty()){
            giveAllPlayersCardObjects(allPlayerMoves);
            HashMap<Integer, ArrayList<Card>> playerMoves = playerMoves();

            for (int moveNumber = 0; moveNumber < 5; moveNumber++){
                ArrayList<Card> roundMoves = new ArrayList<>();
                for (Player p: players) {
                    int playerId = p.id;
                    Card playerMove = playerMoves.get(playerId).get(moveNumber);
                    roundMoves.add(playerMove);
                }
                Collections.sort(roundMoves);
                for (Card move: roundMoves) {
                    for (Player player: players) {
                        if (player.chosenCards.get(moveNumber).equals(move)) {
                            player.doPlayerMove(move, layer);
                            checkpoint.findCheckpoints(player, layer.checkpoint);
                        }
                    }
                }
                boardItems.activateBoardItems(players, layer);
            }
            myHumanPlayer.resetPlayer(myHumanPlayer);
            resetOtherPlayers(players);
            allPlayerMoves = new HashMap<>();
        }
    }
/*
    public boolean getConnection(){
        return client.getConnection();
    }

 */


    /**
     * Does a single player round when the single player press the ready button
     * @param players list of player
     * @param layer TileLayers
     */
    public void singlePlayerRound(ArrayList<Player> players,TileLayers layer) {
        Player singlePlayer = players.get(0);
        if (singlePlayer.ready) {
            for(int round = 0; round < 5; round ++) {
                players.get(0).doPlayerMove(singlePlayer.chosenCards.get(round), layer);
            }
            boardItems.activateBoardItems(players, layer);
            checkpoint.findCheckpoints(singlePlayer, layer.checkpoint);
            singlePlayer.resetPlayer(singlePlayer);
        }
    }


    public void updatePlayerInfo(HashMap<Integer, ArrayList<Float>> playerInfo) {
        for (Player player : players) {
            ArrayList<Float> useThisToUpdateCoordinates = playerInfo.get(player.id);
            player.playerCurrentXPosition = useThisToUpdateCoordinates.get(0);
            player.playerCurrentYPosition = useThisToUpdateCoordinates.get(1);
        }
    }


    public boolean getConnection() {
        return client.getConnection();
    }



}