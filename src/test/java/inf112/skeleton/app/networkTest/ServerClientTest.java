package inf112.skeleton.app.networkTest;

import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.networking.GameClient;
import inf112.skeleton.app.networking.GameServer;

import inf112.skeleton.app.networking.listeners.ClientListener;
import inf112.skeleton.app.networking.listeners.ServerListener;
import inf112.skeleton.app.networking.packets.Packets;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;


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


