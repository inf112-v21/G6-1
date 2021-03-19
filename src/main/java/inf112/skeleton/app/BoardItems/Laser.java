package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;

public class Laser {

    /**
     * This method iterates over the game board and findes tiles containing lasers.
     * Then calls the shoot player to deal damage to players standing harms way
     * @param players list of all the players in the game
     * @param laserLayer tiledMapTileLayer containing lasers
     */
    public void findLasersAndFire(ArrayList<Player> players, TiledMapTileLayer laserLayer){
        for(int xDirectionTiles = Direction.WEST.getBoundaryCoordinate(); xDirectionTiles <= Direction.NORTH.getBoundaryCoordinate(); xDirectionTiles+=300) {
            for (int yDirectionTiles = Direction.SOUTH.getBoundaryCoordinate(); yDirectionTiles <= Direction.EAST.getBoundaryCoordinate(); yDirectionTiles += 300) {
                TiledMapTileLayer.Cell laserTile = laserLayer.getCell(xDirectionTiles / 300, yDirectionTiles / 300);
                if (laserTile != null) {
                    shootPlayers(players,xDirectionTiles,yDirectionTiles);
                }
            }
        }
    }

    /**
     * Compare players position agains the lasers positions
     * and deals damage to players whose positions mach the laser.
     * @param players list of player
     * @param xTile x position of tile the laser is located in
     * @param yTile y position of tile the laser is located in
     */
    public void shootPlayers(ArrayList<Player> players, int xTile, int yTile){
        for(Player player: players){
            if(player.getPlayerXPosition() == (float) xTile && player.getPlayerYPosition() == (float) yTile){
                player.dealDamageToPlayer();
            }
        }
    }
}
