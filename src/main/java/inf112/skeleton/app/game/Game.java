package inf112.skeleton.app.game;


import com.badlogic.gdx.InputProcessor;
import inf112.skeleton.app.BoardItems.*;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.card.CardMoveLogic;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.graphics.TileLayers;
import inf112.skeleton.app.networking.GameClient;
import inf112.skeleton.app.networking.GameServer;
import inf112.skeleton.app.networking.listeners.ClientListener;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class Game implements IGame, InputProcessor {

    /** The number of players in this game */
    private int numberOfPlayers;
    /** The current players in this game */
    public HashMap<Integer, HumanPlayer> idPlayerHashMap;
    public Player myHumanPlayer;
    public ArrayList<Player> players = new ArrayList<>();
    public HashMap<Integer, ArrayList<HashMap<Integer, Action>>> allPlayerMoves = new HashMap<>();
    GameServer server;
    GameClient client;
    public int myId;
    private boolean host;
    public Conveyor conveyor = new Conveyor();
    public Gear gear = new Gear();
    public Laser laser = new Laser();
    public Hole hole = new Hole();
    BoardItems boardItems = new BoardItems();
    public CheckPoint checkpoint = new CheckPoint();
    public GameType typeOfGameStarted = GameType.NONE;
    final CardMoveLogic cardMoveLogic = new CardMoveLogic();
    public GameScreen currentScreen = GameScreen.MENU;


    @Override
    public Graphics startGame() {
        //chooseHostOrJoin();
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
        host = true;
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

    public ArrayList<Player> createListOfPlayers(HashMap<Integer,ArrayList<Float>> playerInfo){
        Set<Integer> playerID = playerInfo.keySet();
        for(Integer player : playerID){
            players.add(new HumanPlayer(Direction.NORTH,player,Color.getPlayerColor(player)));

        }
        setStartPos(playerInfo);
        return players;
    }

    public void setStartPos(HashMap<Integer,ArrayList<Float>> playerInfo){
        for(Player player: players){
            ArrayList<Float> playerStartPos = playerInfo.get(player.id);
            player.setPlayerStartXPosition(playerStartPos.get(0));
            player.setPlayerStartYPosition(playerStartPos.get(1));
            if(player.id == myId){
                System.out.println(myId +" dette e min id");
                initializeMyHumanPlayer(player);
            }

        }
    }
    public void initializeMyHumanPlayer(Player player){
        myHumanPlayer = player;
        myHumanPlayer.playerDeck = cardMoveLogic.playerDeck();
    }

    public HashMap<Integer, ArrayList<HashMap<Integer, Action>>> getPlayerActionList(){
        HashMap<Integer, ArrayList<HashMap<Integer, Action>>>  actionList = new HashMap();
        ArrayList<HashMap<Integer, Action>> sendAbleCards =
                cardMoveLogic.convertToSendAbleCard(myHumanPlayer.chosenCards);
        actionList.put(myHumanPlayer.id,sendAbleCards);
        return actionList;
    }
    public void giveAllPlayersCardObjects(HashMap<Integer, ArrayList<HashMap<Integer, Action>>> actionList){
        for(Player player : players){
            ArrayList<HashMap<Integer, Action>> sendAbleCard = actionList.get(player.id);
            ArrayList<Card> cardObject = cardMoveLogic.convertToCardObject(sendAbleCard);
            player.chosenCards = cardObject;
        }
    }
    /**
    if (this.ready) {
        for(int round = 0; round < 5; round ++) {
            //for this player
            doPlayerMove(chosenCards.get(round), layer);
        }
        conveyor.runConveyor(players, layer.yellowConveyor, layer.blueConveyor);
        gear.runGears(players,layer.redGear, layer.greenGear);
        laser.fireAllLasers(players,layer.laser);
        hole.hole(players, layer.hole);
        checkpoint.findCheckpoints(this, layer.checkpoint);

        cardMoveLogic.readyButtonClickable(this);

    }
}

*/
    public HashMap<Integer, ArrayList<Card>> playerMoves(){
        HashMap<Integer, ArrayList<Card>> playerMoves = new HashMap();
        for(Player player : players){
            playerMoves.put(player.id,player.chosenCards);
        }
        return playerMoves;
    }

    public void resetMyPlayer(Player player){
        player.movedCards = new ArrayList<>();
        player.chosenCards = new ArrayList<>();
        player.playerDeck = new ArrayList<>();
        player.ready = false;
        player.playerDeck = cardMoveLogic.playerDeck();
        player.cardCoordinates = cardMoveLogic.resetCardCoordinates();
    }
    public void resetOtherPlayers(ArrayList<Player> players){
        for(Player player : players){
            player.chosenCards = new ArrayList<>();
        }
    }
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
                //TODO method this
                conveyor.runConveyor(players, layer.yellowConveyor, layer.blueConveyor);
                gear.runGears(players,layer.redGear, layer.greenGear);
                laser.fireAllLasers(players,layer.laser);
                hole.hole(players, layer.hole);
            }
            resetMyPlayer(myHumanPlayer);
            resetOtherPlayers(players);
            allPlayerMoves = new HashMap<>();
        }
    }

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


    public void dealPlayerDeck(Player player) {
        player.cardCoordinates = cardMoveLogic.resetCardCoordinates();
        player.playerDeck = cardMoveLogic.playerDeck();
    }

    public void dealPlayerDecks() {
        for (Player player: players) {
            dealPlayerDeck(player);
        }
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        createPlayers(numberOfPlayers-1);
    }


    @Override
    public HumanPlayer createPlayers(int playerNumber) {
        System.out.println("Creating player ID " + playerNumber);
        float startPositionX = playerNumber*300;
        Color playerColor = Color.getPlayerColor(playerNumber);
        HumanPlayer humanPlayer = new HumanPlayer(Direction.NORTH, playerNumber, playerColor);
        humanPlayer.playerDeck = cardMoveLogic.playerDeck();
        humanPlayer.id = playerNumber;
        humanPlayer.setPlayerStartXPosition(startPositionX);
        players.add(humanPlayer);
        return humanPlayer;
    }
    public void updatePlayerInfo(HashMap<Integer, ArrayList<Float>> playerInfo) {
        for (Player player : players) {
            ArrayList<Float> useThisToUpdateCoordinates = playerInfo.get(player.id);
            player.playerCurrentXPosition = useThisToUpdateCoordinates.get(0);
            player.playerCurrentYPosition = useThisToUpdateCoordinates.get(1);
        }

    }

    public boolean getConnection(){
        return client.getConnection();
    }

    public int getId() {
        return client.getId();
    }

    /**
     *
     * @return Returns number of people in the game.
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }


    public void setMyHumanPlayer(HumanPlayer humanPlayer) {
        myHumanPlayer = humanPlayer;
    }

    @Override
    public boolean keyDown(int i) { return true; }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }

}