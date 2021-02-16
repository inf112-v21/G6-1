package inf112.skeleton.app.player;



public interface IPlayerLogic {


    /**
     * Update the location of the player on the board if player can move
     * If player can't move, player stays at its old location
     * @param updateX : Float
     * @param updateY : Float
     */
    void updatePlayerLocation( float updateX, float updateY);


    /**
     * Sets the width and height of the player image
     *
     * @param width  : Float
     * @param height :Float
     */
    static void setPlayerSize( float width, float height) {

    }


    /**
     * Check if player is on a flag.
     *
     * @Return boolean
     */
    boolean isPlayerOnFlag( );


    /**
     * Check if player can move in given x- and yDirection
     *
     * @param xDirection
     * @param yDirection
     * @return boolean
     */
    boolean canPlayerMove(float xDirection, float yDirection);


    /**
     * Normalize pixel-coordinates to integer
     *
     * @param unNormalizedValue : Float
     * @return Normalized Integer
     */
    int normalizedCoordinates(float unNormalizedValue);


    /**
     * In first assignment, game is over when player visits a flag.
     * Checks if game is over
     *
     * @return boolean
     */
    static boolean isGameOver() {
        return false;
    }
}
