package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;


public class Gear {
    /**
     * This method iterates over the game board and finds tiles containing lasers.
     * Then calls damagePlayerInHarmsWay to find and deal damage to players standing harms way
     * @param players list of all the players in the game
     * @param laserLayer tiledMapTileLayer containing lasers
     */
    /**
     * This method iterates over the game board and finds tiles containing gears.
     * Than call locatePlayersOnGears to find and rotate players standing on gears.
     * @param players
     * @param redGear
     * @param greenGear
     */
   public void findAndRunGear(ArrayList<Player> players, TiledMapTileLayer redGear, TiledMapTileLayer greenGear){
       int xStart = Direction.WEST.getBoundaryCoordinate();
       int xEnd = Direction.EAST.getBoundaryCoordinate();
       int yStart = Direction.SOUTH.getBoundaryCoordinate();
       int yEnd = Direction.NORTH.getBoundaryCoordinate();
       TiledMapTileLayer.Cell redGearTile;
       TiledMapTileLayer.Cell greenGearTile;

        for(int tileXPosition = xStart; tileXPosition <= xEnd; tileXPosition+=300) {
            for (int tileYPosition = yStart; tileYPosition <= yEnd; tileYPosition += 300) {
                redGearTile = redGear.getCell(tileXPosition / 300, tileYPosition / 300);
                greenGearTile = greenGear.getCell(tileXPosition / 300, tileYPosition / 300);
                if (redGearTile != null) {
                    locatePlayersOnGear(players, tileXPosition,
                            tileYPosition, (int)Action.ROTATE_LEFT.getAction());
                }
                if(greenGearTile != null) {
                    locatePlayersOnGear(players, tileXPosition,
                            tileYPosition, (int)Action.ROTATE_RIGHT.getAction());
                }
            }
        }
    }

    public void locatePlayersOnGear(ArrayList<Player> players, int xTile, int yTile, int rotationDirection){
        for(Player player: players){
            if(player.getPlayerXPosition() == (float) xTile && player.getPlayerYPosition() == (float) yTile){
                Direction oldPlayerDirection = player.direction;
                player.setPlayerDirection(rotationDirection);
                System.out.println("Player " + player.color + " was standing on a Gear, and was rotated fra direction "
                        + oldPlayerDirection + " to direction " + player.direction);
                System.out.println(" ");
            }
        }
    }
}
