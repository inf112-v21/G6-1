package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import inf112.skeleton.app.game.GameScreen;
import inf112.skeleton.app.game.GameType;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.shared.Color;

public class EndScreenProcessor extends BaseInputProcessor {
    final Graphics graphics;

    public EndScreenProcessor(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        float x = mouseClickXCoordinate;
        float y = mouseClickYCoordinate;

        if (x >= 3227 && x <= 4088) {
            if(y <= 2030 && y >= 1680) {
                graphics.game.currentScreen = GameScreen.MENU;
                graphics.game.typeOfGameStarted = GameType.NONE;
                graphics.singlePlayer.newHumanPlayer(0, Color.GREEN, 0,0);

            } else if (y <= 1330 && y >= 980) {
                Gdx.app.exit();
            }
        }


        return false;
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
