package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
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
     * @param walls - tilemapLayer wall
     * @param xPosition - to check
     * @param yPosition - to check
     * @return Type of wall else null
     */
    public ArrayList<Direction> isThereAWallInThisLocation(TiledMapTileLayer walls, int  xPosition, int yPosition){
        TiledMapTileLayer.Cell wall;
        wall = walls.getCell(xPosition,yPosition);
        if(wall != null){
            return wallType.get(wall.getTile().getId());
        }
        return null;
    }


    public int hasCollidedTest(TiledMapTileLayer walls, Player player, int  xPosition, int yPosition){
        ArrayList<Direction> typeOfWall = isThereAWallInThisLocation(walls, xPosition, yPosition);

        if(typeOfWall != null){
            for(Direction wallDirection: typeOfWall){
                if(getPlayerXYDirection(player) == wallDirection.getXyDirection()){
                    return canMove(wallDirection,player, xPosition, yPosition);
                }
            }
        }
        return 2;
    }


    public boolean hasPlayerAndWallSameLocation( Player player, float xPosition, float yPosition){
        return player.getPlayerXPosition()/300 == xPosition && player.getPlayerYPosition()/300 == yPosition;
    }



    public Integer canMove(Direction wall, Player player,float xPosition, float yPosition ){
            if(player.direction == wall && !hasPlayerAndWallSameLocation(player,xPosition,yPosition)){
                return 0;
            }else if (player.direction == wall && hasPlayerAndWallSameLocation(player,xPosition,yPosition)) {
                return 1;
            }
            else if((isOpposite(player,wall) && !hasPlayerAndWallSameLocation(player,xPosition, yPosition))){
                return 1;
            }
        return 2;
    }
    

    public boolean isOpposite(Player player, Direction wall){
        if (getPlayerXYDirection(player) == wall.getXyDirection()){
            return player.direction != wall;
        }
        return false;
    }


    public char getPlayerXYDirection(Player player){
        if(player.direction == Direction.WEST || player.direction == Direction.EAST){
            return Direction.WEST.getXyDirection();
        }else if(player.direction == Direction.NORTH || player.direction == Direction.SOUTH) {
            return Direction.NORTH.getXyDirection();
        }
        return '0';
    }


}
