package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.location.Location;
import inf112.skeleton.app.player.Player;

public class GameLogic extends Sprite implements InputProcessor, IGameLogic {

    private float updateX;
    private float updateY;
    private TiledMap gameCourse;
    private TiledMapTileLayer flagLayer;

    public GameLogic(Sprite sprite, TiledMapTileLayer flagLayer) {
        super(sprite);
        this.flagLayer = flagLayer;
    }

    /**
     * Takes Player SpriteBatch, sends updated players location to super draw
     * @param batch : Player
     */
    @Override
    public void draw(Batch batch) {
        setPosition(updateX,updateY);
        if(isPlayerOnFlag()) System.out.println("WON!!!!!!");
        //updatePlayerLocation(updateX, updateY);
        super.draw(batch);
    }

    /**
     * Update the location of the player on the board
     * @param updateX :Float
     * @param updateY : Float
     */
    // TODO finne ut om Game kan bruke denne istedenfor playerMoveAction
    public void updatePlayerLocation(float updateX, float updateY) {
        this.setPosition(updateX,updateY);

    }

    /**
     * Sets the start position of the player
     * @param startPosX : Float
     * @param startPosY :Float
     */
    public void setPlayerStartPosition(float startPosX, float startPosY) {
        this.setPosition(startPosX, startPosY);
    }

    /**
     * Sets the size of the player image
     * @param width : Float
     * @param height :Float
     */
    public void setPlayerSize(float width, float height) {
        setSize(width, height);
    }

    /**
     * Check if player is on a flag.
     * In first assignment player wins when visiting one flag.
     * @Return true/false :boolean
     */
    public boolean isPlayerOnFlag() {
        TiledMapTileLayer.Cell cell = flagLayer.getCell(normalizedCoordinates(getX()), normalizedCoordinates(getY()));
        if(cell!= null){
            return true;
        }
        else return false;
    }

    /**
     * Chose game course.
     * for first assignment not used
     */
    public TiledMap chooseGameCourse() {
        /*
        // TODO refactor so we only need to type the name of the map instead of the path
        if(mapName == "default")
            gameCourse = new TmxMapLoader().load("Maps/Checkmate.tmx");
        else
            gameCourse = new TmxMapLoader().load(mapName);

         */
        return new TmxMapLoader().load("Maps/RiskyExchange.tmx");
    }

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
        if(keyPressed == Input.Keys.LEFT){
            updateX = getX() - 300;
        }
        else if(keyPressed == Input.Keys.RIGHT){
            updateX = getX() + 300;
        }
        else if(keyPressed == Input.Keys.UP){
            updateY = getY() + 300;
        }
        else if(keyPressed == Input.Keys.DOWN){
            updateY = getY() - 300;
        }
        return true;
    }

    /**
     * keyUp registers what happens when the key is released.
     * Player to stop moving when button is released
     * @param keyPressed
     * @return true : boolean
     */

    @Override
    public boolean keyUp(int keyPressed) {
        if(keyPressed == Input.Keys.LEFT) {
            updateX = getX();

        }
        else if(keyPressed == Input.Keys.RIGHT){
            updateX = getX();
        }
        else if(keyPressed == Input.Keys.UP){
            updateY = getY();
        }
        else if(keyPressed == Input.Keys.DOWN){
            updateY = getY();
        }
        return true;
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

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public Location setNewLocation() {
        return null;
    }

    @Override
    public Location getXCoordinate() {
        return null;
    }

    @Override
    public Location getYCoordinate() {
        return null;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
