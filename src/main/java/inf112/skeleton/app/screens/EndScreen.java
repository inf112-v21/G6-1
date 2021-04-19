package inf112.skeleton.app.screens;

import inf112.skeleton.app.game.BaseInputProcessor;
import inf112.skeleton.app.game.GameScreen;
import inf112.skeleton.app.game.MenuInputProcessor;
import inf112.skeleton.app.graphics.Graphics;

public class EndScreen extends BaseInputProcessor {
    Graphics graphics;

    public EndScreen(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        float x = mouseClickXCoordinate;
        float y = mouseClickYCoordinate;

        if (x >= 3227 && x <= 4088) {
            if(y <= 2030 && y >= 1680) {
                System.out.println("Play again button click");
                graphics.game.currentScreen = GameScreen.MENU;

            } else if (y <= 1330 && y >= 980) {
                System.out.println("Exit button click");
                System.exit(1);
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
