package inf112.skeleton.app.game;


import com.badlogic.gdx.InputProcessor;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.card.CardMoveLogic;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.networking.GameClient;
import inf112.skeleton.app.networking.GameServer;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Game implements IGame, InputProcessor {

    /** The number of players in this game */
    private int numberOfPlayers;
    /** The current players in this game */
    public HashMap<Integer, HumanPlayer> idPlayerHashMap;
    public HumanPlayer myHumanPlayer;
    public ArrayList<Player> players = new ArrayList<>();
    GameServer server;
    GameClient client;
    private boolean host;
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
        //client.connect(ip.getHostAddress());
    }

    @Override
    public void executeMoves(HashMap<Integer, ArrayList<Card>> playerMoves) {
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
                        player.updatePlayerLocation(move);
                    }
                }
            }
        }
        dealPlayerDecks();
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

    public HumanPlayer setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        myHumanPlayer = createPlayers(numberOfPlayers-1);
        return myHumanPlayer;
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
        return humanPlayer;
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