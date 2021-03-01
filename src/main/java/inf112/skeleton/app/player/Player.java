package inf112.skeleton.app.player;


import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.cards.Card;
import inf112.skeleton.app.shared.Direction;

public abstract class Player  {


    public final String name;;
    public Direction direction;


    public Player(Direction direction, String name) {
        this.name = name;
        this.direction = direction;

    }

    public abstract void updatePlayerLocation(HumanPlayer player, Card card) throws InterruptedException;

    public abstract boolean isPlayerOnFlag(TiledMapTileLayer flagLayer);

    public abstract boolean canPlayerMove(float xDirection, float yDirection);

    public abstract int normalizedCoordinates(float unNormalizedValue);

    public abstract boolean isGameOver(TiledMapTileLayer flagLayer);

}
