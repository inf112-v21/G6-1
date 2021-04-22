package inf112.skeleton.app.game;

import com.badlogic.gdx.Input;
import inf112.skeleton.app.graphics.Graphics;

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
        System.out.println("The character pressed is ");
        System.out.println(c);

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

        System.out.println("Touch in menu input " + x + ", " + y);
        if (x >= 3260 && x <= 3960) {
            if (y <= 1267 && y >= 1029) {
                graphics.game.currentScreen = GameScreen.MENU;
            }
            if (y <= 1960 && y >= 1722){
                // TODO figure out how to connect to the following IP: graphics.enteredIp

                // Once connected show the host screen
                graphics.game.currentScreen = GameScreen.HOST;
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
        this.text = text;
    }

    @Override
    public void canceled() {

    }
}
