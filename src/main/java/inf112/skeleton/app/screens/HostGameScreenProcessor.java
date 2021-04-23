package inf112.skeleton.app.screens;

import inf112.skeleton.app.game.GameScreen;
import inf112.skeleton.app.game.GameType;
import inf112.skeleton.app.graphics.Graphics;


public class HostGameScreenProcessor extends BaseInputProcessor {
    final Graphics graphics;
    public boolean startGame = false;

    public HostGameScreenProcessor(Graphics graphics){
        super();
        this.graphics = graphics;
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
        float x = mouseClickXCoordinate;
        float y = mouseClickYCoordinate;

        if (x >= 3260 && x <= 3960) {
            if (y <= 1950 && y >= 1722) {
                graphics.game.typeOfGameStarted = GameType.NETWORK_HOST;
                graphics.game.currentScreen = GameScreen.GAME;

                startGame = true;
            }
            if(y <= 1267 && y >= 1029) {
                graphics.game.currentScreen = GameScreen.MENU;
                graphics.game.server.stop();

            }

        }

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
