package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;


public class Gear {


   public void findAndRunConveyor(ArrayList<Player> players, TiledMapTileLayer redGear, TiledMapTileLayer greenGear){

        for(int xDirectionTiles = Direction.WEST.getBoundaryCoordinate(); xDirectionTiles <= Direction.EAST.getBoundaryCoordinate(); xDirectionTiles+=300) {
            for (int yDirectionTiles = Direction.SOUTH.getBoundaryCoordinate(); yDirectionTiles <= Direction.NORTH.getBoundaryCoordinate(); yDirectionTiles += 300) {
                TiledMapTileLayer.Cell redGearTile = redGear.getCell(xDirectionTiles / 300, yDirectionTiles / 300);
                TiledMapTileLayer.Cell greenGearTile = greenGear.getCell(xDirectionTiles / 300, yDirectionTiles / 300);
                if (redGearTile != null) {
                    locatePlayersOnConveyor(players,xDirectionTiles,yDirectionTiles, (int) Action.ROTATE_LEFT.getAction());
                }
                if(greenGearTile != null) {
                    locatePlayersOnConveyor(players,xDirectionTiles,yDirectionTiles, (int) Action.ROTATE_RIGHT.getAction());
                }
            }
        }
    }

    public void locatePlayersOnConveyor(ArrayList<Player> players, int xTile, int yTile, int rotationDirection){
        for(Player player: players){
            if(player.getPlayerXPosition() == (float) xTile && player.getPlayerYPosition() == (float) yTile){
                player.setPlayerDirection(rotationDirection);
            }
        }
    }
}
