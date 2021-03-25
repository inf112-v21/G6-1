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

    HumanPlayer humanPlayer;
    Laser laser;
    ArrayList<Player> testPlayers = new ArrayList<>();
    int tileSize = 300;


    @Before
    public void setup() {
        humanPlayer = new HumanPlayer(Direction.NORTH, 1, Color.GREEN);
        laser = new Laser();
        testPlayers.add(humanPlayer);
        humanPlayer.setPlayerStartXPosition(9*tileSize);
        humanPlayer.setPlayerStartYPosition(3*tileSize);
    }

    @Test
    public void doesPlayerTakeOneDamageTokenWhenInFrontOfLaser(){
        laser.damagePlayerInHarmsWay(testPlayers, 9*tileSize, 3*tileSize);

        Assertions.assertEquals(1, humanPlayer.getPlayerDamageTaken());
    }

    @Test
    public void doesPlayerTakeOneHealthWhenHitTenTimes(){
        for(int i=1; i<=10; i++) {
            laser.damagePlayerInHarmsWay(testPlayers, 9 * tileSize, 3 * tileSize);
        }
        Assertions.assertEquals(2, humanPlayer.getPlayerHealth());
        Assertions.assertEquals(0, humanPlayer.getPlayerDamageTaken());
    }

    @Test
    public void doesPlayerGoBackToStartWhenOneHealthIsLost(){
        for(int i=1; i<=10; i++) {
            laser.damagePlayerInHarmsWay(testPlayers, 9 * tileSize, 3 * tileSize);
        }

        Assertions.assertEquals(0, humanPlayer.getPlayerXPosition());
        Assertions.assertEquals(0, humanPlayer.getPlayerYPosition());
    }
}
