package inf112.skeleton.app.player;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.GameLogic.GameLogic;
import inf112.skeleton.app.location.Location;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.robot.Robot;

public class Player extends Sprite implements IPlayer, InputProcessor {


    private Texture img;

/*
    private final Robot robot; // private final Piece piece;
    private int lifeTokens;
    private int damageTokens;
    private final int id;
    private Location location;
    private Location checkPoint;
*/
public Player(Sprite sprite) {
    super(sprite);

        /* This is not needed yet
        this.id = id;
        this.robot = robot;
        this.lifeTokens = 3;
        this.damageTokens = 0;
         */
    }

    /*
    @Override
    public Action[] getActions(GameLogic board) {
        return new Action[0];
    }


    private void loseLifeToken() {
        this.lifeTokens -= 1;
    }

    public int getLifeTokens() {
        return lifeTokens;
    }
/*
    /**
     * Return the players location
     * @return the current location;
     */


    @Override
    public Action[] getActions(GameLogic board) {
        return new Action[0];
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
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
