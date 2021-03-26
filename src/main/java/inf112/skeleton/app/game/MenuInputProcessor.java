package inf112.skeleton.app.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import inf112.skeleton.app.graphics.Graphics;

public class MenuInputProcessor implements InputProcessor {
    private final Graphics graphics;

    public MenuInputProcessor(Graphics graphics) {
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
        float x = Gdx.input.getX();
        float y = Gdx.input.getY();

        System.out.println("Touch in menu input " + x + ", " + y);
        if (x >= 600 && x <= 725) {
            if(y >= 200 && y <= 255) {
                System.out.println("Single player click");
                graphics.game.typeOfGameStarted = GameType.SINGLE_PLAYER;
                graphics.game.currentScreen = GameScreen.GAME;

            } else if (y >= 300 && y <= 355) {
                System.out.println("Host click");
                graphics.game.typeOfGameStarted = GameType.NETWORK_HOST;
                graphics.game.currentScreen = GameScreen.GAME;
                graphics.game.hostNewGame("RiskyExchange.tmx");

            } else if(y >= 400 && y <= 435) {
                System.out.println("Join click");
                // TODO get ip from user then join game
                //graphics.game.typeOfGameStarted = GameType.NETWORK_JOIN;
                //graphics.game.joinNewGame(ip)
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
