package inf112.skeleton.app.GameLogic;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;


public class GameLogic extends Sprite implements InputProcessor, IGameLogic {

    private float updateX = 0;
    private float updateY = 0;
    private TiledMap gameCourse;
    private TiledMapTileLayer flagLayer;


    public GameLogic(Sprite sprite, TiledMapTileLayer flagLayer) {
        super(sprite);
        this.flagLayer = flagLayer;
    }

    /**
     * Update and draw Player SpriteBatch
     *
     * @param batch : Player
     */
    @Override
    public void draw(Batch batch) {
        updatePlayerLocation(updateX, updateY);
        if(isPlayerOnFlag()) System.out.println("VICTORY!");
        super.draw(batch);
    }

    /**
     * Update the location of the player on the board if player can move
     * If player can't move, player stays at its old location
     * @param updateX : Float
     * @param updateY : Float
     */
    public void updatePlayerLocation(float updateX, float updateY) {
        if (canPlayerMove(updateX, updateY)){
            this.setPosition(updateX, updateY);
        }
        this.setPosition(getX(), getY());
    }

    //TODO this funktion dosent do anything because of the class-variables which is set to zero when starting the game. use in update player?
    public void setPlayerStartPosition(float startPosX, float startPosY) {
        this.setPosition(startPosX, startPosY);
    }

    /**
     * Sets the pixel-size of the player image
     *
     * @param width : Float
     * @param height :Float
     */
    public void setPlayerSize(float width, float height) {
        setSize(width, height);
    }

    /**
     * Check if player is on a flag.
     *
     * @Return boolean
     */
    public boolean isPlayerOnFlag() {
        TiledMapTileLayer.Cell cell = flagLayer.getCell(normalizedCoordinates(getX()), normalizedCoordinates(getY()));
        return cell!= null;
    }

    /**
     *  Check if its possible to move in the given x- and yDirection
     *
     * @param xDirection : Float
     * @param yDirection : Float
     * @return boolean
     */
    public boolean canPlayerMove(float xDirection, float yDirection){
        return !(xDirection < 0 || xDirection > 3300 || yDirection < 0 || yDirection > 3900);
    }


    /**
     * Normalize pixel-coordinates to integer
     *
     * @param unNormalizedValue : Float
     * @return Normalized Integer
     */
    private int normalizedCoordinates(float unNormalizedValue) {
        return (int) unNormalizedValue/300;
        }

    /**
     * keyDown registers what happens when the key is pressed down.
     * Player move one tile upon down-press
     *
     * @param keyPressed
     * @return true : boolean
     */
    @Override
    public boolean keyDown(int keyPressed) {
        int tileDirection = 300;
        if(keyPressed == Input.Keys.LEFT){
            updateX = getX() - tileDirection;
        }
        else if(keyPressed == Input.Keys.RIGHT){
            updateX = getX() + tileDirection;
        }
        else if(keyPressed == Input.Keys.UP){
            updateY = getY() + tileDirection;
        }
        else if(keyPressed == Input.Keys.DOWN){
            updateY = getY() - tileDirection;
        }
        return true;
    }

    /**
     * keyUp registers what happens when the key is released.
     * Player to stop moving when button is released
     *
     * @param keyPressed
     * @return true : boolean
     */
    @Override
    public boolean keyUp(int keyPressed) {
        if(keyPressed == Input.Keys.LEFT || keyPressed == Input.Keys.RIGHT) {
            updateX = getX();
        }
        else if(keyPressed == Input.Keys.UP || keyPressed == Input.Keys.DOWN) {
            updateY = getY();
        }
        return true;
    }

    /**
     * In first assignment, game is over when player visits a flag.
     * Checks if game is over
     *
     * @return boolean
     */
    public boolean isGameOver() {
        if (isPlayerOnFlag()) {
            return true;
        } else {
            return false;
        }
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
