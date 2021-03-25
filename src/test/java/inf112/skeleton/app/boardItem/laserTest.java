package inf112.skeleton.app.boardItem;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.BoardItems.Board;
import inf112.skeleton.app.BoardItems.Gear;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import inf112.skeleton.app.BoardItems.Laser;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class laserTest {

    Lwjgl3ApplicationConfiguration cfg;
    Graphics graphics;
    HumanPlayer humanPlayer;
    Laser laser;
    ArrayList<Player> testPlayers = new ArrayList<>();


    //Usikker på hvor mye at dette man trenger, tar refactoring etterpå
    @Before
    public void setup() {
        graphics = new Graphics(new Game());
        cfg = new Lwjgl3ApplicationConfiguration();
        humanPlayer = new HumanPlayer(Direction.NORTH, 1, Color.GREEN);
        laser = new Laser();
        testPlayers.add(humanPlayer);
        humanPlayer.setPlayerStartXPosition(9*300);
        humanPlayer.setPlayerStartYPosition(3*300);
        new Lwjgl3Application(graphics, cfg);
    }

    @Test
    public void doesPlayerTakeOneDamageTokenWhenInFrontOfLaser(){
        laser.damagePlayerInHarmsWay(testPlayers, 9*300, 3*300);

        Assertions.assertEquals(1, humanPlayer.getPlayerDamageTaken());
    }

    @Test
    public void doesPlayerTakeOneHealthWhenHitTenTimes(){
        for(int i=1; i<=10; i++) {
            laser.damagePlayerInHarmsWay(testPlayers, 9 * 300, 3 * 300);
        }
        Assertions.assertEquals(2, humanPlayer.getPlayerHealth());
        Assertions.assertEquals(0, humanPlayer.getPlayerDamageTaken());
    }

    @Test
    public void doesPlayerGoBackToStartWhenOneHealthIsLost(){
        for(int i=1; i<=10; i++) {
            laser.damagePlayerInHarmsWay(testPlayers, 9 * 300, 3 * 300);
        }

        Assertions.assertEquals(0, humanPlayer.getPlayerXPosition());
        Assertions.assertEquals(0, humanPlayer.getPlayerYPosition());
    }
}
