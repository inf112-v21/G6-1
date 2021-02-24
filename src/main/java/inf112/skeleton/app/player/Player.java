package inf112.skeleton.app.player;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.shared.Direction;

abstract class Player extends Sprite {


    public Direction direction;
    public TiledMapTileLayer flagLayer;

    public Player(Sprite sprite, TiledMapTileLayer flagLayer, Direction direction) {
        super(sprite);
        this.flagLayer = flagLayer;
        this.direction = direction;

    }

    public abstract void updatePlayerLocation(float updateX, float updateY);

    public abstract void setPlayerSize(float width, float height);

    public abstract boolean isPlayerOnFlag(TiledMapTileLayer flagLayer);

    public abstract boolean canPlayerMove(float xDirection, float yDirection);

    public abstract int normalizedCoordinates(float unNormalizedValue);

    public abstract boolean isGameOver(TiledMapTileLayer flagLayer);



}
