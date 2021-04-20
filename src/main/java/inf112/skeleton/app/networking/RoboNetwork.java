Skip to content
        Search or jump to…

        Pull requests
        Issues
        Marketplace
        Explore

@thomasRimmereide 
inf112-v21
        /
        GameStoppers
        generated from inf112-v21/MavenTemplate
        0
        00
        Code
        Issues
        14
        Pull requests
        Actions
        Projects
        4
        Wiki
        Security
        Insights
        Settings
        GameStoppers/src/main/java/inf112/skeleton/app/networking/Network.java /
@thomasRimmereide
thomasRimmereide Refactoring nr1 done in networking
        Latest commit 2a2c8d3 3 days ago
        History
        2 contributors
@sharifi98@thomasRimmereide
41 lines (32 sloc)  1.01 KB

        package inf112.skeleton.app.networking;

        import com.esotericsoftware.kryo.Kryo;
        import com.esotericsoftware.kryonet.EndPoint;
        import inf112.skeleton.app.card.Card;
        import inf112.skeleton.app.player.Player;

        import java.util.ArrayList;


public class Network {

    static public final int TCPport = 54555;
    static public final int UDPport = 54777;

    /**
     * Method used to register all needed classes for the server and client.
     * This must be identical on both server and client.
     *
     * @param
     */
    static public void register (EndPoint endpoint) {
        Kryo kyro = endpoint.getKryo();
        kyro.register(playerInfo.class);
        kyro.register(addNewPlayer.class);
        kyro.register(playerHasChosenCards.class);
    }

    static public class playerInfo {
        public ArrayList<Player> list;
        public Player player;
    }

    static public class addNewPlayer {
        public boolean bool;
    }

    static public class playerHasChosenCards {
        public boolean bool;
    }
}
© 2021 GitHub, Inc.
        Terms
        Privacy
        Security
        Status
        Docs
        Contact GitHub
        Pricing
        API
        Training
        Blog
        About
        Loading complete