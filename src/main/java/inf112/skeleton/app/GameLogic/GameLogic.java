package inf112.skeleton.app.GameLogic;

import inf112.skeleton.app.location.Location;

public class GameLogic extends Sprite implements InputProcessor {
    private float moveX;
    private float moveY;
    //testing pull and push

    // temporary player while waiting on Player class constructor
    public GameLogic(Sprite sprite){
        super(sprite);
    }
    GameLogic player = new GameLogic(new Sprite(new Texture("player.png")));

    @Override
    public void draw(Batch batch) {
        updatePlayer(moveX, moveY);
        super.draw(batch);
    }

    private void updatePlayer(float moveX, float moveY) {
        setPosition(moveX, moveY);
    }

    /**
     * keyDown registers what happens when the key is pressed down. We want the player to move upon down-press
     *
     * @param keyPressed
     * @return true : boolean
     */

    @Override
    public boolean keyDown(int keyPressed) {
        if(keyPressed == Input.Keys.LEFT){
            moveX = getX() - 32;
        }
        else if(keyPressed == Input.Keys.RIGHT){
            moveX = getX() + 32;
        }
        else if(keyPressed == Input.Keys.UP){
            moveX = getY() + 32;
        }
        else if(keyPressed == Input.Keys.DOWN){
            moveX = getY() - 32;
        }
        return true;
    }

    /**
     * keyUp registers what happens when the key is released. We want the player to stop moving when button is released
     *
     * @param keyPressed
     * @return true : boolean
     */

    @Override
    public boolean keyUp(int keyPressed) {
        if(keyPressed == Input.Keys.LEFT) {
            moveX = getX();
        }
        else if(keyPressed == Input.Keys.RIGHT){
                moveX = getX();
        }
        else if(keyPressed == Input.Keys.UP){
            moveX = getY();
        }
        else if(keyPressed == Input.Keys.DOWN){
            moveX = getY();
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
