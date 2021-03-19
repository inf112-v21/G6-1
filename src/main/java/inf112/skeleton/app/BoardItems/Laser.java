package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;
import java.util.HashMap;

public class Laser {

    private final Direction direction;
    private final float position;


    public Laser(Direction direction, float position){
        this.direction = direction;
        this.position = position;
    }

    private HashMap<Direction,Integer> endPositions = getLocationEndPositions();

//TODO Lasers are to be indexed 0(360),90,180,270

    public HashMap<Direction,Integer> getLevelLasersDirectionAndPosition(ArrayList<Player> players,TiledMapTileLayer laserLayer){
        HashMap<Direction, Integer> laserDirectionAndPosition = new HashMap<>();
        for(int x = 0; x<=3300; x+=300){
            for(int y = 0; y <= 3900; y += 300){
                TiledMapTileLayer.Cell laserTile = laserLayer.getCell(x/300,y/300);
                if (laserTile != null ||laserTile.getTile().getId() == 180){
                    shootLaser(players, y, Direction.SOUTH);
                }else if(laserTile != null && laserTile.getTile().getId() == 360){
                    shootLaser(players, y, Direction.NORTH);
                }else if(laserTile != null && laserTile.getTile().getId() == 46){
                    shootLaser(players, y, Direction.EAST);
                }else if(laserTile != null && laserTile.getTile().getId() == 38){
                    shootLaser(players, y, Direction.WEST);
                }
            }
        }
        return laserDirectionAndPosition;
    }

    /**
     * Creates a Hashmap with directions as key and end coordinates for positions as value
     * @return HashMap<Direction, Integer>
     */
    public HashMap<Direction, Integer> getLocationEndPositions() {
        HashMap<Direction,Integer> locationEndPosition = new HashMap<>();
        locationEndPosition.put(Direction.NORTH, 3900);
        locationEndPosition.put(Direction.SOUTH, 0);
        locationEndPosition.put(Direction.WEST, 0);
        locationEndPosition.put(Direction.EAST, 3300);
        return locationEndPosition;
    }

    /**
     * This method finds the tiles and direction to where the the laser is to fire.
     * Then "fire" the laser.
     * @param players the list of players
     */
    public void shootLaser(ArrayList<Player> players ,int position, Direction laserDirection){
        int checkFromPosition = position + 300;
        int checkToPosition = endPositions.get(laserDirection);
        if(laserDirection == Direction.SOUTH || laserDirection == Direction.WEST){
            checkFromPosition = endPositions.get(laserDirection);
            checkToPosition = position -300;
        }
        for(int tile = checkFromPosition; tile <= checkToPosition; tile+=300){
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
