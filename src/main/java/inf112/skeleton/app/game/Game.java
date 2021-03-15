package inf112.skeleton.app.game;


import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.card.CardDeck;
import inf112.skeleton.app.card.CardMoveLogic;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.networking.GameClient;
import inf112.skeleton.app.networking.GameServer;
import inf112.skeleton.app.networking.packets.Packets;
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

    private Graphics graphics;
    /** The number of players in this game */
    private int numberOfPlayers;
    /** The current players in this game */
    public HashMap<Integer, HumanPlayer> idPlayerHashMap;
    public HumanPlayer myHumanPlayer;
    public ArrayList<Player> players = new ArrayList<Player>();
    /** The card handler */
    CardDeck cardDeck;
    GameServer server;
    GameClient client;
    public String typeOfGameStarted;
    private ArrayList<Packets.CardsPacket> allPlayerCards;
    private boolean[] ready;
    CardMoveLogic cardMoveLogic = new CardMoveLogic();


    @Override
    public Graphics startGame() {
        graphics = new Graphics(this);
        chooseHostOrJoin();
        return graphics;
    }

    /**
     * InetAddress object created by the host. This is the only place server.run() should be used,
     * to avoid creating more than on server.
     *
     * @param map - What map to be used in the hosted game.
     * @return
     */

    public InetAddress hostNewGame(String map) {
        server = new GameServer(map);
        server.run();
        client = new GameClient(server.getAddress(),this);

        return server.getAddress();
    }

    /**
     * Creates client object for user. Might need more in this method.
     *
     * @param ip InetAddress object, is being called properly in @chooseHostOrJoin()
     */
    public void joinNewGame(InetAddress ip) {
        client = new GameClient(ip,this);

    }

    /**
     * Method to prompt if user is hosting a game or joining a game. In this method, a proper InetAddress object is
     * created for @joinNewGame()
     *
     */
    public void chooseHostOrJoin () {
        Scanner HostOrJoin = new Scanner(System.in);
        System.out.println("Host (1), join (2) or start single player (3)?: ");

        String choice = HostOrJoin.nextLine();
        System.out.println("You choose " + choice);

        if(choice.equals("1")){
            hostNewGame("RiskyExchange");
            typeOfGameStarted = "host";
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
            typeOfGameStarted = "join";
            joinNewGame(hostIp);

        }
        else if(choice.equals("3")){
            System.out.println("Single player selected");
            typeOfGameStarted = "single player";
        }
        else {
            System.out.println("Please enter 1 or 2 when asked to");
            chooseHostOrJoin();
        }
    }

    @Override
    public void executeMoves(HashMap<Integer, ArrayList<Card>> playerMoves) {
        // Assume all players have chosen their moves
        for (int moveNumber = 0; moveNumber < 5; moveNumber++){
            ArrayList<Card> roundMoves = new ArrayList<Card>();
            for (Player p: players) {
                int playerId = p.id;
                Card playerMove = playerMoves.get(playerId).get(moveNumber);
                roundMoves.add(playerMove);
            }
            // Sort moves by priority
            Collections.sort(roundMoves);

            // Execute moves: for each move
            for (Card move: roundMoves) {
                // Do the move on the correct player

                for (Player player: players) {
                    if (player.chosenCards.get(moveNumber).equals(move)) {
                        player.updatePlayerLocation(move);
                    }
                }
            }
        }
        dealPlayerDecks();
    }

    public void dealPlayerDecks() {
        for (Player player: players) {
            player.cardCoordinates = cardMoveLogic.resetCardCoordinates();
            //player.chosenCards = new ArrayList<>();
            player.playerDeck = cardMoveLogic.playerDeck();
        }
    }


    public void isReady(Packets.CardsPacket p) {
        for (Packets.CardsPacket pc : allPlayerCards) {
            if (pc.playerId == p.playerId) {
                return;
            }
        }
        allPlayerCards.add(p);

        if (allPlayerCards.size() == numberOfPlayers) {
            boolean contains = false;
            for (int i = 1; i < numberOfPlayers; i++) {
                if (p.playerId == i) {
                    contains = true;
                    break;
                }
            }
        }
    }

    public void getAllReady(boolean[] ready) {
        this.ready = ready;
    }



    // numberOfPlayers
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        createPlayers();
    }

    // Returns the number of players currently in the game
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    // public void deleteDisconnectedPlayers() {}


    // The host sends out start signal to alert other players that the game is starting.
    public void sendStartSignal() {
        client.sendStartSignal();
    }

    @Override
    public boolean isGameOver(TiledMapTileLayer flagLayer) {
        for (Player p: players) {
            if (p.isPlayerOnFlag(flagLayer)){
                return true;
            }
        }
        return false;
    }

    // Initializes idPlayerHashMap,
    // Creates the number of players needed and puts them into the idPlayerHashMap.
    @Override
    public ArrayList<Player> createPlayers() {
        System.out.println("Creating players " + numberOfPlayers);
        ArrayList <Player> playerList = new ArrayList<>();
        float startPositionX = 0;
        for (int i = 0; i < numberOfPlayers; i++) {
            Color playerColor = Color.getPlayerColor(i);
            HumanPlayer humanPlayer = new HumanPlayer(Direction.NORTH, i, playerColor);
            humanPlayer.setId(i);
            playerList.add(humanPlayer);
            playerList.get(i).setPlayerStartXPosition(startPositionX);
            startPositionX += 300;
            idPlayerHashMap.put(i, humanPlayer);
        }
        setMyHumanPlayer(idPlayerHashMap.get(client.getId()));
        this.players = playerList;
        return playerList;
    }

    public int getId() {
        return client.getId();
    }


    public void setMyHumanPlayer(HumanPlayer humanPlayer) {
        myHumanPlayer = humanPlayer;
    }

    // idPlayerHashMap
    public HashMap<Integer, HumanPlayer> getIdPlayerHashMap() {
        return idPlayerHashMap;
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