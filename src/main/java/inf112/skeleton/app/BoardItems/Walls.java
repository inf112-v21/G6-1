package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Walls {

    /**
     * Create a Hashmap where the key (Integer) is the id of the different walls in the wall layers.
     * The value is the direction the walls are facing in a tile.
     * The value is an arrayList because there are some tiles that contain "multiple" walls that is
     * the walls that are facing in more than one direction.
     */
    HashMap<Integer, ArrayList<Direction>> wallType = new HashMap<>() {{
        put(8, new ArrayList<>(Arrays.asList(Direction.SOUTH, Direction.EAST)));
        put(16, new ArrayList<>(Arrays.asList( Direction.NORTH, Direction.EAST)));
        put(23, new ArrayList<>(Collections.singletonList(Direction.EAST)));
        put(24, new ArrayList<>(Arrays.asList(Direction.NORTH, Direction.WEST)));
        put(29, new ArrayList<>(Collections.singletonList(Direction.SOUTH)));
        put(30, new ArrayList<>(Collections.singletonList(Direction.WEST)));
        put(31, new ArrayList<>(Collections.singletonList(Direction.NORTH)));
        put(32, new ArrayList<>(Arrays.asList(Direction.SOUTH, Direction.WEST)));
    }};


    /**
     * This is the method to be used outside this class to check if a player has collided with a wall
     * There are three possible return values 0 , 1 , 2.
     * return 0 --> The player will hit a wall in the next tile. Move player to that tile but not longer
     * return 1 --> The player has hit a wall and can't move from its position
     * return 2 --> The player hasn't hit a wall and can keep on moving
     * @param wall TiledMapTileLayer
     * @param player the current player
     * @param checkThisXPos X position to check for wall
     * @param checkThisYPos Y position to check for wall
     * @return Integer 0 ,1 or 2
     */
    public int hasPlayerCollidedWithWall(TiledMapTileLayer wall, Player player, int  checkThisXPos, int checkThisYPos, Card card){
        ArrayList<Direction> typeOfWall = isThereAWallInThisLocation(wall, checkThisXPos, checkThisYPos);
        if(typeOfWall != null){
            for(Direction wallDirection: typeOfWall){
                if(getPlayerXYDirection(player) == wallDirection.getXyDirection()){
                    return canMove(wallDirection,player, checkThisXPos, checkThisYPos, card);
                }
            }
        }
        return 2;
    }


    /**
     * This method checks if the given position contains a wall. If so, it returns the correct arrayList
     * containing the wall(s) which it gets from the id of the wall in this position.
     * @param walls TiledMapTileLayer
     * @param xPosition to check
     * @param yPosition to check
     * @return  If wall --> ArrayList<Direction> wall else --> null
     */
    public ArrayList<Direction> isThereAWallInThisLocation(TiledMapTileLayer walls, int  xPosition, int yPosition){
        TiledMapTileLayer.Cell wall;
        wall = walls.getCell(xPosition, yPosition);
        if(wall != null){
            return wallType.get(wall.getTile().getId());
        }
        return null;
    }

    /**
     * This method check if a wall and a player has the same location
     * @param player the current player
     * @param xPosition to check
     * @param yPosition to check
     * @return boolean
     */
    public boolean hasPlayerAndWallSameLocation( Player player, float xPosition, float yPosition){
        return player.getPlayerXPosition()/300 == xPosition && player.getPlayerYPosition()/300 == yPosition;
    }

    /**
     * Help method for hasPlayerCollidedWithWall.
     * @param wall Direction
     * @param player current player
     * @param wallXPosition wall position
     * @param wallYPosition wall position
     * @return same as hasPlayerCollidedWithWall
     */
    public Integer canMove(Direction wall, Player player, float wallXPosition, float wallYPosition, Card card ){
            if(player.direction == wall && !hasPlayerAndWallSameLocation(player,wallXPosition,wallYPosition)){
                if(card.action == Action.BACK_UP){
                    return 2;
                }else{
                    return 0;
                }

            }else if (player.direction == wall && hasPlayerAndWallSameLocation(player,wallXPosition,wallYPosition)) {
                if(card.action == Action.BACK_UP){
                    return 2;
                }else {
                    return 1;
                }

            }else if((hasOppositeDirection(player,wall) && !hasPlayerAndWallSameLocation(player,wallXPosition, wallYPosition))){
                return 1;
            }else if((hasOppositeDirection(player,wall) && !hasPlayerAndWallSameLocation(player,wallXPosition, wallYPosition) && card.action == Action.BACK_UP)){
                return 1;
            }
        return 2;
    }


    /**
     * This method checks if a player has the opposite direction than a wall
     * @param player the current player
     * @param wall to check
     * @return boolean
     */
    public boolean hasOppositeDirection(Player player, Direction wall){
        if (getPlayerXYDirection(player) == wall.getXyDirection()){
            return player.direction != wall;
        }
        return false;
    }


    /**
     * This method gets the XY direction of a player
     * @param player current player
     * @return char x or y
     */
    public char getPlayerXYDirection(Player player){
        if(player.direction == Direction.WEST || player.direction == Direction.EAST){
            return Direction.WEST.getXyDirection();
        }else if(player.direction == Direction.NORTH || player.direction == Direction.SOUTH) {
            return Direction.NORTH.getXyDirection();
        }
        return '0';
    }
}
