package inf112.skeleton.app.player;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

abstract class Player extends Sprite {

    public TiledMapTileLayer flagLayer;

    public Player(Sprite sprite, TiledMapTileLayer flagLayer) {
        super(sprite);
        this.flagLayer = flagLayer;
    }

    public abstract void updatePlayerLocation(float updateX, float updateY);

    public abstract void setPlayerSize(float width, float height);

    public abstract boolean isPlayerOnFlag(TiledMapTileLayer flagLayer);

    public abstract boolean canPlayerMove(float xDirection, float yDirection);

    public abstract int normalizedCoordinates(float unNormalizedValue);

    public boolean isGameOver() {
        if (isPlayerOnFlag()) {
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
