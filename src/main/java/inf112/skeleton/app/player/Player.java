package inf112.skeleton.app.player;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.screens.BaseInputProcessor;
import inf112.skeleton.app.graphics.TileLayers;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Player extends BaseInputProcessor {



    public int id;
    public  Color color;
    public boolean ready;
    public int damageTaken;
    public int healthToken;
    public Direction direction;
    public ArrayList<Card> playerDeck;
    public ArrayList<Card> chosenCards;
    public float playerCurrentXPosition;
    public float playerCurrentYPosition;
    public ArrayList<Integer> movedCards;
    public float playerCheckpointPositionX;
    public float playerCheckpointPositionY;
    public  ArrayList<Integer> flagsToVisit;
    public ArrayList<Float> cardCoordinates;

    /**
     * Prints player deck
     */
    public abstract void printPlayerCard();


    /**
     * @param direction The direction the player is facing. Needs to be set only when a player is created
     * Then it will be updated automatically during the game
     * @param id The name of the player
     * @param color Color of the player
     */
    public Player(Direction direction, int id, Color color) {
        this.id = id;
        this.color = color;
        this.ready = false;
        this.healthToken = 3;
        this.damageTaken = 0;
        this.direction = direction;
        this.playerCurrentXPosition = 0;
        this.playerCurrentYPosition = 0;
        this.playerCheckpointPositionX = 0;
        this.playerCheckpointPositionY = 0;
        this.movedCards = new ArrayList<>();
        this.chosenCards  =  new ArrayList<>();
        this.playerDeck = new ArrayList<>();
        this.flagsToVisit = new ArrayList<>(Arrays.asList(55,63,71));
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
     * This method is used to reset human player for a new game
     * @param id player id
     * @param color player color
     * @param playerStartXPosition start position X
     * @param playerStartYPosition start position Y
     */
    public abstract void newHumanPlayer (int id, Color color, float playerStartXPosition,float playerStartYPosition);


    /**
     * Handles player movement when colliding with a wall, handles damage for player
     * @param afterCollisionX player x position after collision with wall
     * @param afterCollisionY player x position after collision with wall
     */
    public abstract void wallCollisionHandler(float afterCollisionX, float afterCollisionY);


    /**
     * This method sets a new player location
     * @param xPosition of new location
     * @param yPosition of new location
     */
    public abstract void setPlayerNewLocation(float xPosition, float yPosition);


    /**
     * This method handles player movement by card
     * @param card this players round card
     * @param wall TiledMapTileLayer
     */
    public abstract void playerMoveHandler(Card card, TiledMapTileLayer wall);


    /**
     * This method set player checkpoint location
     * @param xPosition of the new checkpoint
     * @param yPosition of the new checkpoint
     */
    public abstract void setNewPlayerCheckpointLocation(float xPosition, float yPosition);


    /**
     * This method is call when you want to do a move by card
     * @param card to play
     * @param tileLayers all TiledLayers
     */
    public abstract void doPlayerMove(Card card, TileLayers tileLayers);


    /**
     * Help method for playerMoveHandler
     * This method finds the next location to check for walls, and board limits.
     * It takes the amount of movement (0 or 300), finds the XY direction of the player,
     * multiples it by playerDirection.getMoveDirection which give the correct direction to move NSEW.
     * Then alters either X or Y direction and adds it to the list to be returned
     * @param amountOfMovement 0 or 300
     * @return  ArrayList<Float> coordinates 0 = X 1 = Y
     */
    public abstract ArrayList<Float> getCoordinatesToCheck(float amountOfMovement, Card card);


    /**
     * This reset everything that needs to be reset for a player to play a new round
     * @param player reset
     */
    public abstract void resetPlayerForNewRound(Player player);


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
     * The player needs to visit the flags in the correct order, to win.
     * This method checks if the player is on the correct flag by comparing
     * player position with the flag id on the flag in that position.
     * If its the correct flag the flag is removed from players flagsToVisit.
     *
     * @param flagLayer The TiledMapTileLayer that contains flags
     * @return true player has visited all flags in the correct order.
     */
    public abstract boolean hasPlayerVisitedAllFlags(TiledMapTileLayer flagLayer);


    /**
     * Check if the player can move to the given X and Y position,
     * without going outside the board
     * @param xDirection to check
     * @param yDirection to check
     * @return boolean
     */
    public abstract boolean isPlayerOnBoard(float xDirection, float yDirection);


    /**
     * Calculate normalized coordinates. The tiled-map operates with
     * tile-size of 300 by 300. While the layers operates with tile-size of 1 by 1.
     * @param unNormalizedValue tiled- map value to normalise
     * @return normalized coordinates
     */
    public abstract int normalizedCoordinates(float unNormalizedValue);


    /**
     * Set new direction of the player related to the given move degree
     * @param moveDegree amount of movement
     */
    public abstract void setPlayerDirection(int moveDegree);


    /**
     * @return This method returns the players health token
     */
    public abstract int getPlayerHealth();


    /**
     * @return The amount of damage the player has
     */
    public abstract int getPlayerDamageTaken();


    /**
     * Increase players damageTaken by one.
     * If damageTaken is 10, players healthToken is reduced by one
     * and damageTaken is set to zero
     */
    public abstract void dealDamageToPlayer();


    /**
     * If the players healthToken is more than zero the player is alive
     * and the function will return true.
     * If the players healthToken in zero the player is dead
     * and the function will return false.
     * @return boolean
     */
    public abstract boolean isPlayerAlive();


    /**
     * Takes a life from a player
     */
    public abstract void takePlayerLife();


}
