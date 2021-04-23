package inf112.skeleton.app.graphics;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class TileLayers {
    public final TiledMapTileLayer laser;
    public final TiledMapTileLayer blueConveyor;
    public final TiledMapTileLayer yellowConveyor;
    public final TiledMapTileLayer redGear;
    public final TiledMapTileLayer greenGear;
    public final TiledMapTileLayer hole;
    public final TiledMapTileLayer wall;
    public final TiledMapTileLayer checkpoint;
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
