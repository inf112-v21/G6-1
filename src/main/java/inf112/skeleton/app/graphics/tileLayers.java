package inf112.skeleton.app.graphics;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class tileLayers {
    TiledMapTileLayer laser;
    TiledMapTileLayer blueConveyor;
    TiledMapTileLayer yellowConveyor;
    TiledMapTileLayer redGear;
    TiledMapTileLayer greenGear;
    TiledMapTileLayer hole;
    TiledMapTileLayer wall;
    void setTileLayers(TiledMapTileLayer laserLayer, TiledMapTileLayer blueConveyorLayer,
                   TiledMapTileLayer yellowConveyorLayer, TiledMapTileLayer redGearLayer,
                   TiledMapTileLayer greenGearLayer,TiledMapTileLayer holeLayer,TiledMapTileLayer wallLayer){
        laser = laserLayer;
        blueConveyor = blueConveyorLayer;
        yellowConveyor = yellowConveyorLayer;
        redGear = redGearLayer;
        greenGear = greenGearLayer;
        hole = holeLayer;
        wall = wallLayer;
    }


    public TiledMapTileLayer getLaser(){
        return laser;
    }


    public TiledMapTileLayer getBlueConveyor(){
        return blueConveyor;
    }


    public TiledMapTileLayer getYellowConveyor(){
        return yellowConveyor;
    }


    public TiledMapTileLayer getRedGear(){
        return redGear;
    }


    public TiledMapTileLayer getGreenGear(){
        return greenGear;
    }


    public TiledMapTileLayer getHole(){
        return hole;
    }


    public TiledMapTileLayer getWall(){
        return wall;
    }

}
