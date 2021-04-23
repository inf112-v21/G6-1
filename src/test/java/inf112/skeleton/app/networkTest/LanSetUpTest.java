package inf112.skeleton.app.networkTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.EmptyApplicationListener;
import inf112.skeleton.app.game.Game;

import org.junit.BeforeClass;
import org.junit.Test;


import java.net.InetAddress;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class LanSetUpTest {
    private static Game game1, game2;

    @BeforeClass
    public static void setUp(){

        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new EmptyApplicationListener(), conf);
        game1 = new Game();
        game2 = new Game();

        //InetAddress ip = game1.hostNewGame("RiskyExchange.tmx");
        //game2.joinNewGame(ip.getHostAddress());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void hostConnectionTest(){
        assertTrue(game1.getConnection());
    }

    @Test
    public void joinedConnectionTest() {
        assertTrue(game2.getConnection());
    }

    @Test
    public void numberOfPlayersTest(){
        assertEquals(game1.players.size(), game2.players.size());
    }


}



