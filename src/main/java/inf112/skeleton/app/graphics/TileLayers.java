package inf112.skeleton.app.graphics;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class TileLayers {
    public TiledMapTileLayer laser;
    public TiledMapTileLayer blueConveyor;
    public TiledMapTileLayer yellowConveyor;
    public TiledMapTileLayer redGear;
    public TiledMapTileLayer greenGear;
    public TiledMapTileLayer hole;
    public TiledMapTileLayer wall;
    public TiledMapTileLayer checkpoint;
    public TileLayers(TiledMapTileLayer laser, TiledMapTileLayer blueConveyor,
                      TiledMapTileLayer yellowConveyor, TiledMapTileLayer redGear, TiledMapTileLayer greenGear,
                      TiledMapTileLayer hole,TiledMapTileLayer wall, TiledMapTileLayer checkpoint){
        this.laser = laser;
        this.blueConveyor = blueConveyor;
        this.yellowConveyor = yellowConveyor;
        this.redGear = redGear;
        this.greenGear = greenGear;
        this.hole = hole;
        this.wall = wall;
        this.checkpoint = checkpoint;
    }
}
