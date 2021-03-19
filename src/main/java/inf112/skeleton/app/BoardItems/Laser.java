package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;

public class Laser {

    private final Direction direction;
    private final float position;


    public Laser(Direction direction, float position){
        this.direction = direction;
        this.position = position;
    }



//TODO Lasers are to be indexed 0,90,180,270

    public void findLasersAndFire(ArrayList<Player> players, TiledMapTileLayer laserLayer){
        for(int xDirectionTiles = 0; xDirectionTiles<=3300; xDirectionTiles+=300){
            for(int yDirectionTiles = 0; yDirectionTiles <= 3900; yDirectionTiles += 300){
                TiledMapTileLayer.Cell laserTile = laserLayer.getCell(xDirectionTiles/300,yDirectionTiles/300);
                if (laserTile != null ||laserTile.getTile().getId() == Direction.SOUTH.getDirection()){
                    getTilesHitByLaser(players, Direction.SOUTH.getBoundaryCoordinate(), yDirectionTiles-300, Direction.SOUTH);
                }else if(laserTile != null && laserTile.getTile().getId() == Direction.NORTH.getDirection()){
                    getTilesHitByLaser(players, yDirectionTiles + 300, Direction.NORTH.getBoundaryCoordinate(), Direction.NORTH);
                }else if(laserTile != null && laserTile.getTile().getId() == 46){
                    getTilesHitByLaser(players, xDirectionTiles +300, Direction.EAST.getBoundaryCoordinate(), Direction.EAST);
                }else if(laserTile != null && laserTile.getTile().getId() == 38){
                    getTilesHitByLaser(players, Direction.WEST.getBoundaryCoordinate(), xDirectionTiles -300, Direction.WEST);
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
