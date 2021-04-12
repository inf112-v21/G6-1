package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Walls {

    /**
     * Adds wall tiled id and wall direction to hashmap
     */
    HashMap<Integer, ArrayList<Direction>> wallType = new HashMap<>() {{
        put(8, new ArrayList(Arrays.asList(Direction.SOUTH, Direction.EAST)));
        put(16, new ArrayList(Arrays.asList( Direction.NORTH, Direction.EAST)));
        put(23, new ArrayList(Arrays.asList(Direction.EAST)));
        put(24, new ArrayList(Arrays.asList(Direction.NORTH, Direction.WEST)));
        put(29, new ArrayList(Arrays.asList(Direction.SOUTH)));
        put(30, new ArrayList(Arrays.asList(Direction.WEST)));
        put(31, new ArrayList(Arrays.asList( Direction.NORTH)));
        put(32, new ArrayList(Arrays.asList(Direction.SOUTH, Direction.WEST)));
    }};

    /**
     * find if its a wall in a location
     * @param walls tilemapLayer wall
     * @param xPosition to check
     * @param yPosition to check
     * @return Type of wall else null
     */
    public ArrayList<Direction> findWall(TiledMapTileLayer walls, int  xPosition, int yPosition){
        TiledMapTileLayer.Cell wall;
        wall = walls.getCell(xPosition,yPosition);
        if(wall != null){
            return wallType.get(wall.getTile().getId());
        }
        return null;
    }

    /**
     * Check if a player collide with a wall in a position
     * @param walls tilemapLayer wall
     * @param player to check
     * @param xPosition to check
     * @param yPosition to check
     * @return true if player collides with a wall else false 
     */
    public boolean hasCollidedWithWall(TiledMapTileLayer walls, Player player, int  xPosition, int yPosition){
        ArrayList<Direction> typeOfWall = findWall(walls, xPosition, yPosition);
        if(typeOfWall != null){
            for(Direction wallDirection: typeOfWall){
                if (player.direction == wallDirection){
                    return true;
                }
            }
        }
        return false;
    }
}
