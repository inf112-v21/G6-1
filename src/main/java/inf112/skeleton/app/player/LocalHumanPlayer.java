package inf112.skeleton.app.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;


public class LocalHumanPlayer extends Player implements InputProcessor {
    private float updateX = 0;
    private float updateY = 0;

    public LocalHumanPlayer(Sprite sprite, TiledMapTileLayer flagLayer) {
        super(sprite, flagLayer);
    }
    /**
     * Update and draw Player SpriteBatch
     *
     * @param batch : Player
     */
    @Override
    public void draw(Batch batch) {
        updatePlayerLocation(updateX, updateY);
        //if(isPlayerOnFlag()) System.out.println("VICTORY!");
        super.draw(batch);
    }

     public void updatePlayerLocation(float updateX, float updateY) {
        if (canPlayerMove(updateX, updateY)){
            this.setPosition(updateX, updateY);
        }
        this.setPosition(getX(), getY());
    }
    public void setPlayerSize(float width, float height) {
        setSize(width, height);
    }


    public boolean isPlayerOnFlag(TiledMapTileLayer flagLayer) {
        TiledMapTileLayer.Cell cell = flagLayer.getCell(normalizedCoordinates(getX()), normalizedCoordinates(getY()));
        return cell!= null;
    }

    public boolean canPlayerMove(float xDirection, float yDirection){
        return !(xDirection < 0 || xDirection > 3300 || yDirection < 0 || yDirection > 3900);
    }

    public int normalizedCoordinates(float unNormalizedValue) {

        return (int) unNormalizedValue/300;
    }

    public boolean isGameOver(TiledMapTileLayer flagLayer) {
        if (isPlayerOnFlag(flagLayer)) {
            return true;
        } else {
            return false;
        }
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
