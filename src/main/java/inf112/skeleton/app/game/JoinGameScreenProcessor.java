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
