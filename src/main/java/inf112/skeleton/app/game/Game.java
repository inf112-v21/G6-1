package inf112.skeleton.app.game;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.cards.Card;
import inf112.skeleton.app.cards.CardDeck;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;
import java.util.ArrayList;
import java.util.Collections;

public class Game implements IGame, InputProcessor {

    private Graphics graphics;
    /** The number of players in this game */
    int numberOfPlayers;
    /** The current players in this game */
    public ArrayList<Player> players;
    /** The card handler */
    CardDeck cardDeck;

    @Override
    public Graphics startGame() {
        graphics = new Graphics();
        return graphics;
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
                            player.updatePlayerLocation((HumanPlayer) player, move);
                        } catch (Exception e) {

                        }
                    }
                }
            }
        }
    }

    void doMoveOnPlayer(Player player, Card move) {

    }


    @Override
    public boolean isGameOver() {
        return false;
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