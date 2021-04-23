package inf112.skeleton.app.networkTest;

/**
public class LanSetUpTest {
    private static Game game1, game2;

    @BeforeClass
    public static void setUp(){

        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new EmptyApplicationListener(), conf);
        game1 = new Game();
        game2 = new Game();

        InetAddress ip = game1.hostNewGame();
        game2.joinNewGame(ip);
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
        assertEquals(game1.players.size(), 2);
    }


}
*/


