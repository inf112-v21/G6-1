package inf112.skeleton.app.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import inf112.skeleton.app.graphics.Graphics;

import java.net.InetAddress;

public class MenuInputProcessor implements InputProcessor {
    private final Graphics graphics;
    private String ip = "";

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
        System.out.println("Character typed: " + c);

        try {
            int i = Integer.parseInt(c + "");
            ip += i;
            System.out.println("Total ip so far is " + ip);
        } catch (Exception e) {
            if (c == '.') {
                ip += c;
            } else {
                ip = "";
            }
        }
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
            if(y >= 270 && y <= 316) {
                System.out.println("Single player click");
                graphics.game.typeOfGameStarted = GameType.SINGLE_PLAYER;
                graphics.game.currentScreen = GameScreen.GAME;

            } else if (y >= 371 && y <= 415) {
                System.out.println("Host click");
                graphics.game.chooseHostOrJoin();
                graphics.game.typeOfGameStarted = GameType.NETWORK_HOST;
                graphics.game.currentScreen = GameScreen.GAME;
                //graphics.game.hostNewGame("RiskyExchange.tmx");

            } else if(y >= 469 && y <= 514) {
                System.out.println("Join click, attempting to join ip" + ip);
                try {
                    InetAddress hostIp = InetAddress.getByName(ip);
                    graphics.game.typeOfGameStarted = GameType.NETWORK_JOIN;
                    graphics.game.chooseHostOrJoin();
                } catch (Exception e) {

                }
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
