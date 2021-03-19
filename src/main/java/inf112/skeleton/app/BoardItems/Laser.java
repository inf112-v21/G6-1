package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;

public class Laser {


//TODO Lasers are to be indexed 0,90,180,270
// Make normalizedCoordinates in Player/HumanPlayer static instead of abstract
// Most of this i common for all board entities split into classes and rename methods 

    public void findLasersAndFire(ArrayList<Player> players, TiledMapTileLayer laserLayer){
        for(int xDirectionTiles = Direction.WEST.getBoundaryCoordinate(); xDirectionTiles <= Direction.EAST.getDirectionDegree(); xDirectionTiles+=300){
            for(int yDirectionTiles = Direction.SOUTH.getBoundaryCoordinate(); yDirectionTiles <= Direction.NORTH.getDirectionDegree(); yDirectionTiles += 300) {
                TiledMapTileLayer.Cell laserTile = laserLayer.getCell(xDirectionTiles / 300, yDirectionTiles / 300);
                if (laserTile != null) {
                    if (laserTile.getTile().getId() == Direction.SOUTH.getDirectionDegree()) {
                        getTilesHitByLaser(players, Direction.SOUTH.getBoundaryCoordinate(), yDirectionTiles - 300, Direction.SOUTH);
                    } else if (laserTile.getTile().getId() == Direction.NORTH.getDirectionDegree()) {
                        getTilesHitByLaser(players, yDirectionTiles + 300, Direction.NORTH.getBoundaryCoordinate(), Direction.NORTH);
                    } else if (laserTile.getTile().getId() == 46) {
                        getTilesHitByLaser(players, xDirectionTiles + 300, Direction.EAST.getBoundaryCoordinate(), Direction.EAST);
                    } else if (laserTile.getTile().getId() == 38) {
                        getTilesHitByLaser(players, Direction.WEST.getBoundaryCoordinate(), xDirectionTiles - 300, Direction.WEST);
                    }
                }
            }
        }
    }

    /**
     * This method finds the tiles and direction to where the the laser is to fire.
     * Then "fire" the laser.
     * @param players the list of players
     */
    public void getTilesHitByLaser(ArrayList<Player> players , int checkTileFrom, int checkTileTo, Direction laserDirection){
        for(int tile = checkTileFrom; tile <= checkTileTo; tile+=300){
            if( laserDirection == Direction.WEST || laserDirection == Direction.EAST){
                shootPlayersXDirection(players,tile);
            }else if(laserDirection == Direction.NORTH || laserDirection == Direction.SOUTH){
                shootPlayersYDirection(players,tile);
            }
        }
    }

    /**
     * Deal damage to the players in x direction if player position mache the tile
     * the laser shoots at.
     * @param players list of players on the board
     * @param tile tile the laser shoots at
     */
    public void shootPlayersXDirection(ArrayList<Player> players, int tile){
        for(Player player: players){
            if(player.getPlayerXPosition() == (float) tile) player.dealDamageToPlayer();
        }
    }

    /**
     * Deal damage to the players in Y direction if player position mache the tile
     * the laser shoots at.
     * @param players list of players on the board
     * @param tile tile the laser shoots at
     */
    public void shootPlayersYDirection(ArrayList<Player> players, int tile){
        for(Player player: players){
            if(player.getPlayerYPosition() == (float) tile) player.dealDamageToPlayer();
        }
    }
}
