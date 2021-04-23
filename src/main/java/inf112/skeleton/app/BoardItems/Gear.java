package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;
import java.util.ArrayList;


public class Gear {

    /**
     * This method iterates over the game board and finds tiles containing gears.
     * Than call locatePlayersOnGears to find and rotate players standing on gears.
     * @param players list of players in the game
     * @param redGear TiledMapTileLayer
     * @param greenGear TiledMapTileLayer
     */
   public void runGears(ArrayList<Player> players, TiledMapTileLayer redGear, TiledMapTileLayer greenGear){
       int xStart = Direction.WEST.getBoundaryCoordinate();
       int xEnd = Direction.EAST.getBoundaryCoordinate();
       int yStart = Direction.SOUTH.getBoundaryCoordinate();
       int yEnd = Direction.NORTH.getBoundaryCoordinate();
       TiledMapTileLayer.Cell redGearTile;
       TiledMapTileLayer.Cell greenGearTile;

        for(int tileXPosition = xStart; tileXPosition <= xEnd; tileXPosition += 300) {
            for (int tileYPosition = yStart; tileYPosition <= yEnd; tileYPosition += 300) {
                redGearTile = redGear.getCell(tileXPosition / 300, tileYPosition / 300);
                greenGearTile = greenGear.getCell(tileXPosition / 300, tileYPosition / 300);
                if (redGearTile != null) {
                    locateAndRotatePlayersOnGear(players, tileXPosition,
                            tileYPosition, (int)Action.ROTATE_LEFT.getAction());
                }
                if(greenGearTile != null) {
                    locateAndRotatePlayersOnGear(players, tileXPosition,
                            tileYPosition, (int)Action.ROTATE_RIGHT.getAction());
                }
            }
        }
    }

    /**
     * This method finds players standing on a gear and rotates them according to the direction of the gear
     * @param players list of players in the game
     * @param xTile the x position og the tile where a gear is located
     * @param yTile the y position og the tile where a gear is located
     * @param rotationDirection gears rotating direction
     */
    public void locateAndRotatePlayersOnGear(ArrayList<Player> players, int xTile, int yTile, int rotationDirection){
        for(Player player: players){
            if(player.getPlayerXPosition() == (float) xTile && player.getPlayerYPosition() == (float) yTile){
                player.setPlayerDirection(rotationDirection);

            }
        }
    }
}
