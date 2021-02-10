package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class GameLogic extends Sprite implements InputProcessor {
    private float updateX;
    private float updateY;
    private TiledMap gameCourse;

    //testing pull and push

    // temporary player while waiting on Player class constructor
    public GameLogic(Sprite sprite){
        super(sprite);
    }
    GameLogic player = new GameLogic(new Sprite(new Texture("flags.png")));

    @Override
    public void draw(Batch batch) {
        updatePlayerLocation(updateX, updateY);
        super.draw(batch);
    }

    public void updatePlayerLocation(float updateX, float updateY) {

        this.setPosition(updateX, updateY);
    }

    public void setPlayerStartPosition(float startPosX, float startPosY) {

        this.setPosition(startPosX, startPosY);
    }

    public void setPlayerSize(float width, float height) {
        player.setSize(width, height);

    }
    /*
    public isPlayerOnFlag(){
            // TODO check what Yasmin is calling the flag layers
        gameCourse.getProperties();
        Flaglayer flag = flagLayer.containsKey("flag");
        if (player.getX() == flag.getY() && player.getX() == flag.getY()){
            player.flag +=1;
        }

    }

     */
    public void chooseGameCourse(String mapName) {
        // TODO refactor so we only need to type the name of the map instead of the path
        if(mapName == "default")
            gameCourse = new TmxMapLoader().load("Maps/Checkmate.tmx");
        else
            gameCourse = new TmxMapLoader().load(mapName);
    }

    /**
     * keyDown registers what happens when the key is pressed down. We want the player to move upon down-press
     *
     * @param keyPressed
     * @return true : boolean
     */

    @Override
    public boolean keyDown(int keyPressed) {
        // TODO refactor when tested
        if(keyPressed == Input.Keys.LEFT){
            updateX = getX() - 32;
        }
        else if(keyPressed == Input.Keys.RIGHT){
            updateX = getX() + 32;
        }
        else if(keyPressed == Input.Keys.UP){
            updateY = getY() + 32;
        }
        else if(keyPressed == Input.Keys.DOWN){
            updateY = getY() - 32;
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
}
