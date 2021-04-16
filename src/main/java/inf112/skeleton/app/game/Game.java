package inf112.skeleton.app.game;


import com.badlogic.gdx.InputProcessor;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.card.CardMoveLogic;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Game implements IGame, InputProcessor {


    public ArrayList<Player> players = new ArrayList<>();
    GameServer server;
    GameClient client;
    private boolean host;
    public GameType typeOfGameStarted = GameType.NONE;
    final CardMoveLogic cardMoveLogic = new CardMoveLogic();
    public GameScreen currentScreen = GameScreen.MENU;


    @Override
    public Graphics startGame() {
        return new Graphics(this);
    }

    /**
     * InetAddress object created by the host. This is the only place server.run() should be used,
     * to avoid creating more than on server.
     *
     * @param map - What map to be used in the hosted game.
     */
    public InetAddress hostNewGame(String map) {
        server = new GameServer(map);
        server.run();
        client = new GameClient(server.getAddress(),this);
        host = true;
        return server.getAddress();
    }

    /**
     * Creates client object for user. Might need more in this method.
     *
     * @param ip InetAddress object, is being called properly in @chooseHostOrJoin()
     */
    public void joinNewGame(InetAddress ip) {
        client = new GameClient(ip, this);
        client.connect(ip.getHostAddress());
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

    public HumanPlayer createPlayer(int playerNumber) {
        System.out.println("Creating player");
        float startPositionX = playerNumber*300;
        Color playerColor = Color.getPlayerColor(playerNumber);
        HumanPlayer humanPlayer = new HumanPlayer(Direction.NORTH, playerNumber, playerColor);
        humanPlayer.playerDeck = cardMoveLogic.playerDeck();
        humanPlayer.id = playerNumber;
        humanPlayer.setPlayerStartXPosition(startPositionX);
        return humanPlayer;

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