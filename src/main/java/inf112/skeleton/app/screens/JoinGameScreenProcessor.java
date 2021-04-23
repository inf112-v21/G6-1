package inf112.skeleton.app.screens;

import com.badlogic.gdx.Input;
import inf112.skeleton.app.game.GameScreen;
import inf112.skeleton.app.game.GameType;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.screens.BaseInputProcessor;

public class JoinGameScreenProcessor extends BaseInputProcessor implements Input.TextInputListener {
    final Graphics graphics;
    String text;

    public JoinGameScreenProcessor(Graphics graphics){
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
        // add text to ip
        if (Character.isDigit(c) || c == '.') {
            graphics.enteredIp += c;
        }
        // remove last character of ip on backspace click
        if (c == '\b' && graphics.enteredIp.length() > 0) {
            graphics.enteredIp = graphics.enteredIp.substring(0, graphics.enteredIp.length() - 1);
        }

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
            if (y <= 1267 && y >= 1029) {
                graphics.game.currentScreen = GameScreen.MENU;
            }
            if (y <= 1960 && y >= 1722){
                graphics.game.joinNewGame(graphics.enteredIp);
                // Once connected show the host screen
                graphics.game.typeOfGameStarted = GameType.NETWORK_JOIN;
                graphics.game.currentScreen = GameScreen.GAME;
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

    @Override
    public void input(String s) {
    }

    @Override
    public void canceled() {

    }
}
