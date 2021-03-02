package inf112.skeleton.app.player;


import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.cards.Card;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Player  {


    public final String name;;
    public String piece;
    public Direction direction;
    public ArrayList<Card> chosenCards;
    public ArrayList<Card> playerDeck;
    public float playerCurrentXPosition;
    public float playerCurrentYPosition;
    public ArrayList<Float> cardCoordinates;
    int dummyPlayerDeck;

    public Player(Direction direction, String name, String piece) {
        this.piece = piece;
        this.name = name;
        this.direction = direction;

        this.playerCurrentXPosition = 0;
        this.playerCurrentYPosition = 0;

        this.chosenCards  =  new ArrayList<>();
        this.playerDeck = new ArrayList<>();
        // Coordinates for cards. even = x odd = y
        this.cardCoordinates = new ArrayList<Float>(
                Arrays.asList(5555f,3090f,
                        6080f,3090f,
                        6605f,3090f,
                        5555f,2370f,
                        6080f,2370f,
                        6605f,2370f,
                        5555f,1640f,
                        6080f,1640f,
                        6605f,1640f));
        this.dummyPlayerDeck = 9;
    }

    public abstract void updatePlayerLocation(HumanPlayer player, Card card) throws InterruptedException;

    public abstract boolean isPlayerOnFlag(TiledMapTileLayer flagLayer);

    public abstract boolean canPlayerMove(float xDirection, float yDirection);

    public abstract int normalizedCoordinates(float unNormalizedValue);

    public abstract boolean isGameOver(TiledMapTileLayer flagLayer);

    public abstract float setPlayerStartXPosition(float playerStartXPosition);

    public abstract float setPlayerStartYPosition(float playerStartYPosition);

}
