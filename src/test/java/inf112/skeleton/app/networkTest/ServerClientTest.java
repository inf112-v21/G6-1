package inf112.skeleton.app.networkTest;

/*
public class ServerClientTest {

    Game game = new Game();

    GameServer testServer = new GameServer("RiskyExchange.tmx");
    GameClient testClient = new GameClient(InetAddress.getLocalHost(), game);

    ServerListener serverListener = new ServerListener(testServer.getServer());
    ClientListener clientListener = new ClientListener();

    private final Packets.TestPack testPack = new Packets.TestPack();

    public ServerClientTest() throws IOException {

    }

    @Before
    public void setUp() {
        testPack.packet = "Test Pack!";
    }

    @After
    public void afterTest() {
        testServer.getServer().close();
        testClient.getClient().close();
    }

    @Test
    public void ServerSendsPacketAndClientReceivesTest() throws InterruptedException {
        testServer.getServer().sendToAllTCP(testPack);
        System.out.println("testPack is sent!");

        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(clientListener.gotPackage);
    }

    @Test
    public void clientSendsPacketServerReceivesTest() throws InterruptedException {
        testClient.getClient().sendTCP(testPack);
        System.out.println("Test pack is sent!");

        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(serverListener.gotPackage);
    }

    @Test
    public void clientSendsPacketServerReceivesAndSendsBack() throws InterruptedException {
        testClient.getClient().sendTCP(testPack);
        System.out.println("Test packet is sent!");

        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(clientListener.gotPackage);
    }

}


*/

