package inf112.skeleton.app.player;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Player  {

//TODO color final
    public final int id;
    public int damageTaken;
    public int healthToken;
    public Color color;
    public Direction direction;
    public ArrayList<Card> chosenCards;
    public ArrayList<Card> playerDeck;
    public float playerCurrentXPosition;
    public float playerCurrentYPosition;
    public ArrayList<Float> cardCoordinates;
    public ArrayList<Integer> movedCards;

    /**
     * @param direction The direction the player is facing. Needs to be set only when a player is created
     * Then it will be updated automatically during the game
     * @param id The name of the player
     * @param color Color of the player
     */



    public Player(Direction direction, int id, Color color) {
        this.id = id;
        this.color = color;
        this.healthToken = 3;
        this.damageTaken = 0;
        this.direction = direction;
        this.playerCurrentXPosition = 0;
        this.playerCurrentYPosition = 0;
        this.movedCards = new ArrayList<>();
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
     * @param playerStartXPosition The desired start position X for player
     * @return playerStartXPosition, to set player sprite
     */
    public abstract float setPlayerStartXPosition(float playerStartXPosition);

    /**
     * Needed to mach Sprite positions with the player when starting a game.
     * Pass this method in as Y parameter in set player position sprite-built-in method.
     *
     * @param playerStartYPosition the desired start position Y for player
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
     * Check if the player is on a flag. by comparing player position
     * with the flag layer content in that position
     * @param flagLayer The TiledMapTileLayer that contains flags
     * @return true if player location mach flag location else false
     */
    public abstract boolean isPlayerOnFlag(TiledMapTileLayer flagLayer);

    /**
     * Check if the player can move to the given X and Y position,
     * without going outside the board
     * @param xDirection to check
     * @param yDirection to check
     * @return boolean
     */
    public abstract boolean keepPlayerOnBoard(float xDirection, float yDirection);

    /**
     * Calculate normalized coordinates. The tiled-map operates with
     * tile-size of 300 by 300. While the layers operates with tile-size of 1 by 1.
     * @param unNormalizedValue tiled- map value to normalise
     * @return normalized coordinates
     */
    public abstract int normalizedCoordinates(float unNormalizedValue);

    /**
     * Set new direction of the player related to the given card
     * @param card given card
     */
    public abstract void setPlayerDirection(Card card);

    /**
     * If the players card wil move the player outside the game board
     * this function will move the player to the edge of the board instead
     * @param position position to move to
     * @return new position always inside the board
     */
    public abstract float movePlayerAsFarAsPossible(float position);


    /**
     * Update player location or direction from given card
     * @param card  given card
     */
    public abstract void updatePlayerLocation(Card card);

    /**
     * @return This method returns the players health token
     */
    public abstract int getPlayerHealth();

    /**
     * @return The amount of damage the player has
     */
    public abstract int getPlayerDamageTaken();

    /**
     * Set the players health Token to full (3)
     * Set the players damageTaken to zero
     */
    public abstract void restorePlayerHealthAndDamage();

    /**
     * Increase players damageTaken by one.
     * If damageTaken is 10, players healthToken is reduced by one
     * and damageTaken is set to zero
     */
    public abstract void dealDamageToPlayer();

    /**
     * If the players healthToken is more than zero the player is alive
     * and the function will return true.
     * If the players healtToken in zero the player is dead
     * and the function will return false.
     * @return boolean
     */
    public abstract boolean isPlayerAlive();


    /**
     * runs a simple single player round
     */
    public abstract void singlePlayerRound();


    public  abstract void setMouseClickCoordinates(OrthographicCamera camera);


}
