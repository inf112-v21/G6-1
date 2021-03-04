package inf112.skeleton.app.player;


import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Player  {


    public final int id;
    public Color color;
    public Direction direction;
    public ArrayList<Card> chosenCards;
    public ArrayList<Card> playerDeck;
    public float playerCurrentXPosition;
    public float playerCurrentYPosition;
    public ArrayList<Float> cardCoordinates;
    public int dummyPlayerDeck;

    /**
     * @param direction The direction the player is facing. Needs to be set only when a player is created
     *                  Then it will be updated automatically during the game
     * @param id The name of the player
     * @param color
     */


    //TODO comment on the other things
    public Player(Direction direction, int id, Color color) {

        this.id = id;
        this.color = color;
        this.direction = direction;
        this.playerCurrentXPosition = 0;
        this.playerCurrentYPosition = 0;
        this.dummyPlayerDeck = 9;
        this.chosenCards  =  new ArrayList<>();
        this.playerDeck = new ArrayList<>();
        this.cardCoordinates = new ArrayList<>(
                Arrays.asList(5555f, 3090f,
                        6080f, 3090f,
                        6605f, 3090f,
                        5555f, 2370f,
                        6080f, 2370f,
                        6605f, 2370f,
                        5555f, 1640f,
                        6080f, 1640f,
                        6605f, 1640f));


    }

    /**
     * Needed to mach Sprite positions with the player when starting a game.
     * Pass this method in as X parameter in set player position sprite-built-in method.
     *
     * @param playerStartXPosition The wanted start position X for player
     * @return playerStartXPosition, to set player sprite
     */
    public abstract float setPlayerStartXPosition(float playerStartXPosition);

    /**
     * Needed to mach Sprite positions with the player when starting a game.
     * Pass this method in as Y parameter in set player position sprite-built-in method.
     *
     * @param playerStartYPosition the wanted start position Y for player
     * @return playerStartYPosition to set player sprite
     */
    public abstract float setPlayerStartYPosition(float playerStartYPosition);

    /**
     * Updates player X position, used in the render method of the
     * graphics class to keep Player position synchronised with player sprite.
     */
    public abstract void updatePlayerXPosition(float newXPosition);

    /**
     * Updates player Y position, used in the render method of the
     * graphics class to keep Player position synchronised with player sprite
     */
    public abstract void updatePlayerYPosition(float newYPosition);

    /**
     * @return X position of the player
     */
    public abstract float getPlayerXPosition();

    /**
     * @return Y position of player
     */
    public abstract float getPlayerYPosition();

    /**
     * Check if the player is on a flag.
     * The flag layer is common for all players.
     * @param flagLayer The TiledMapTileLayer that contains flags
     * @return true if player location mach flag location else false
     */
    public abstract boolean isPlayerOnFlag(TiledMapTileLayer flagLayer);

    /**
     * Check if the player can move to the given X and Y position.
     * For the time the player can move if the player keeps within the board.
     * @param xDirection to check
     * @param yDirection to check
     * @return boolean
     */

    public abstract boolean canPlayerMoveX(float xDirection, float yDirection);

    /**
     * Calculate normalized coordinates. The tiled-map operates with
     * tile-size of 300 by 300. While the layers operates with tile-size of 1 by 1.
     * @param unNormalizedValue tiled- map value to normalise
     * @return int
     */
    public abstract int normalizedCoordinates(float unNormalizedValue);

    /**
     * Set new direction of the player related to the given card
     * @param card given card
     */
    public abstract void setPlayerDirection(Card card);

    /**
     * Update player location or direction from given card
     * @param card  given card
     */
    public abstract void updatePlayerLocation(Card card);

    public abstract void round();
}
