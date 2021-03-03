package inf112.skeleton.app.game;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.card.CardDeck;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.networking.GameClient;
import inf112.skeleton.app.networking.GameServer;
import inf112.skeleton.app.networking.packets.Packets;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

// Game is the centerpiece, it sends information to the
// GameClient which in turn send information to the GameServer

public class Game implements IGame, InputProcessor {

    private Graphics graphics;
    /** The number of players in this game */
    int numberOfPlayers;
    /** The current players in this game */
    public ArrayList<Player> players;
    /** The card handler */
    CardDeck cardDeck;
    GameServer server;
    GameClient client;
    private ArrayList<Packets.CardsPacket> allPlayerCards;
    private boolean[] ready;


    @Override
    public Graphics startGame() {
        graphics = new Graphics();
        //chooseHostOrJoin();
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
        System.out.println("Host (1) or join (2) a game?: ");

        String choice = HostOrJoin.nextLine();
        System.out.println("You choose " + choice);

        if(choice.equals("1")){
            hostNewGame("RiskyExchange");
        }
        else if(choice.equals("2")){
            InetAddress hostIp = null;
            Scanner askForIpAddress = new Scanner(System.in);
            System.out.println("Please enter the server IP to join: ");

            String ChoosenIP = askForIpAddress.nextLine();
            try {
                hostIp = InetAddress.getByName(ChoosenIP);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            System.out.println(hostIp.getHostAddress());
            joinNewGame(hostIp);
        }
        else{
            System.out.println("Please enter 1 or 2 when asked to");
        }
    }

    


    @Override
    public void setUpGame() {

        // ask number of players
        // await num of player selection

        // present board choices
        // await board selection

    }


    // 1. Setup kjøres, spillere og brett initialiseres
    // 2. så lenge spill ikke er over playRound()
    // => vi kan anta at brett og spillere finnes når vi kaller playRound
    // => Anta også at klasse for å deale kort er initialisert

    @Override
    public void executeMoves() {
        // Assume all players have chosen their moves
        for (int moveNumber = 0; moveNumber < 5; moveNumber++){
            ArrayList<Card> roundMoves = new ArrayList<Card>();
            for (Player p: players) {
                roundMoves.add(p.chosenCards.get(moveNumber));
            }
            // Sort moves by priority
            Collections.sort(roundMoves);

            // Execute moves: for each move
            for (Card move: roundMoves) {
                // Do the move on the correct player

                for (Player player: players) {
                    if (player.chosenCards.get(moveNumber).equals(move)) {
                        // TODO this cast will throw an exception if the player isn't human
                        // TODO move the method from HumanPlayer to game or
                        //  at least to Player and make the signature need a plary
                        //  then remove try catch
                        try {
                            player.updatePlayerLocation(move);
                        } catch (Exception e) {

                        }
                    }
                }
            }
        }
    }

    void doMoveOnPlayer(Player player, Card move) {

    }

    /**
     *
     *
     */
    public void isReady(Packets.CardsPacket p) {
        for (Packets.CardsPacket pc : allPlayerCards) {
            if (pc.playerId == p.playerId) {
                return;
            }
            allPlayerCards.add(p);
        }

        if (allPlayerCards.size() == numberOfPlayers) {
            boolean contains = false;
            for (int i = 1; i < numberOfPlayers; i++) {
                if (p.playerId == i) {
                    contains = true;
                    break;
                }
            }
        }
        // når alle har sendt inn kort så starter vi runden.
        executeMoves();
    }

    public void getAllReady(boolean[] ready) {
        this.ready = ready;
    }



    public void setNumberOfPlayers(int i) {
        numberOfPlayers = i;
    }

    public int getNumberOfPlayers(int i) {
        return numberOfPlayers;
    }


    @Override
    public boolean isGameOver() {
        return false;
    }

    public void setupPlayer() {

    }


    @Override
    public ArrayList<Player> createPlayers() {
        ArrayList <Player> playerList = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            playerList.add(new HumanPlayer(Direction.NORTH, "Vilde", "erlend"));
        }
        this.players = playerList;
        return playerList;
    }


    @Override
    public boolean keyDown(int i) {
        if (i == Input.Keys.NUM_1) {
            numberOfPlayers = 1;
        } else if (i == Input.Keys.NUM_2) {
            numberOfPlayers = 2;
        } else if (i == Input.Keys.NUM_3) {
            numberOfPlayers = 3;
        } else if (i == Input.Keys.NUM_4) {
            numberOfPlayers = 4;
        } else if (i == Input.Keys.NUM_5) {
            numberOfPlayers = 5;
        } else if (i == Input.Keys.NUM_6) {
            numberOfPlayers = 6;
        } else if (i == Input.Keys.NUM_7) {
            numberOfPlayers = 7;
        } else if (i == Input.Keys.NUM_8) {
            numberOfPlayers = 8;
        }
        return true;
    }

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