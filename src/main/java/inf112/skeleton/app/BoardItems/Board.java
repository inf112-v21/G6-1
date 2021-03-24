package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Board {
    private TiledMap tiledMap;

    public Board(TiledMap tiledMap) {
        this.tiledMap = tiledMap;
    }

    public TiledMapTileLayer getLaserLayer() {
        return (TiledMapTileLayer) tiledMap.getLayers().get("Laser");
    }


}
