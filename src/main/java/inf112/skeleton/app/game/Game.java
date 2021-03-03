package inf112.skeleton.app.game;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.networking.GameClient;
import inf112.skeleton.app.networking.GameServer;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;


// Game is the centerpiece, it sends information to the
// GameClient which in turn send information to the GameServer

public class Game implements IGame, InputProcessor {

    private Graphics graphics;
    int numberOfPlayers;
    GameServer server;
    GameClient client;




    @Override
    public Graphics startGame() {
        graphics = new Graphics();
        chooseHostOrJoin();
        return graphics;
    }

    //TODO Launche serveren i Game; forslag til metode til det:
    public InetAddress hostNewGame(String map) {
        server = new GameServer(map);
        server.run();
        client = new GameClient(server.getAddress(),this);

        return server.getAddress();
    }

    public void joinNewGame(InetAddress ip) {
        client = new GameClient(ip,this);

        host = false;
    }
    public void chooseHostOrJoin () {
        Scanner HostOrJoin = new Scanner(System.in);
        System.out.println("Host (1) or join (2) a game?");

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
    public void playGame() {
        startGame();
        //this.numberOfPlayers = 8; //TODO make this function

        // do rounds
        // cardsToBePlayed ();
        // stop condition pga render
    }


    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public void setUpGame() {
        // ask number of players
        // await num of player selection

        // present board choices
        // await board selection

    }

    @Override
    public ArrayList<Player> createPlayers(int numberOfPlayers) {
        ArrayList <Player> playerList = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            playerList.add(new HumanPlayer(Direction.NORTH, "Vilde", "erlend"));
        }
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