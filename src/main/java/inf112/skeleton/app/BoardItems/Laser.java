package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;

public class Laser {

    /**
     * This method iterates over the game board and finds tiles containing lasers.
     * Then calls damagePlayerInHarmsWay to find and deal damage to players standing harms way
     * @param players list of all the players in the game
     * @param laserLayer tiledMapTileLayer containing lasers
     */
    public void fireAllLasers(ArrayList<Player> players, TiledMapTileLayer laserLayer){
      int xStart = Direction.WEST.getBoundaryCoordinate();
      int xEnd = Direction.EAST.getBoundaryCoordinate();
      int yStart = Direction.SOUTH.getBoundaryCoordinate();
      int yEnd = Direction.NORTH.getBoundaryCoordinate();
      TiledMapTileLayer.Cell laserTile;

        for(int tileXPositions = xStart; tileXPositions <= xEnd; tileXPositions +=300) {
            for (int tileYPositions = yStart; tileYPositions <= yEnd; tileYPositions += 300) {
                laserTile = laserLayer.getCell(tileXPositions / 300, tileYPositions / 300);
                if (laserTile != null) {
                    damagePlayerInHarmsWay(players, (float) tileXPositions,(float) tileYPositions);
                }
            }
        }
    }

    /**
     * Compare players position against the lasers positions
     * Deals damage to players whose positions mach the laser.
     * @param players list of player
     * @param tileXPosition x position of tile the laser is located in
     * @param tileYPosition y position of tile the laser is located in
     */
    public void damagePlayerInHarmsWay(ArrayList<Player> players, float tileXPosition, float tileYPosition){
        for(Player player: players){
            if(player.getPlayerXPosition() == tileXPosition && player.getPlayerYPosition() == tileYPosition){
                int damageBeforeLaser  = player.damageTaken;
                player.dealDamageToPlayer();
                System.out.println("Player " + player.color + " was hit by a laser! Player Damage was " +
                        damageBeforeLaser + " and is now " + player.damageTaken);
                System.out.println(" ");
            }
        }
    }

}
