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

    /**
     * Check if a player collide with a wall in a position
     * @param walls tilemapLayer wall
     * @param player to check
     * @param xPosition to check
     * @param yPosition to check
     * @return true if player collides with a wall else false
     */
   /**
    public String hasCollidedWithWall(TiledMapTileLayer walls, Player player, int  xPosition, int yPosition){
        ArrayList<Direction> typeOfWall = isThereAWallInThisLocation(walls, xPosition, yPosition);
        char playerXYDirection = getPlayerXYDirection(player);
        if(typeOfWall != null){

            System.out.println("WAAALLLLL");
            System.out.println(typeOfWall + " type of wall");
            for(Direction wallDirection: typeOfWall){
                System.out.println(wallDirection + " wall direction");
                if (playerXYDirection == wallDirection.getXyDirection()){
                    System.out.println("wall is in same xy as player");
                    if(shouldPlayerMoveToWallTile(player, wallDirection)){
                        System.out.println("Player shoud move to tile ");
                        return "move";
                    }else {
                        System.out.println("Player sholud stand still");
                        return "stop";
                    }
                }
            }
        }
        return "NO";
    }
*/
    public int hasCollidedTest(TiledMapTileLayer walls, Player player, int  xPosition, int yPosition){
        ArrayList<Direction> typeOfWall = isThereAWallInThisLocation(walls, xPosition, yPosition);
        char playerXYDirection = getPlayerXYDirection(player);
        int moveInstruction = 0;

        if(typeOfWall != null){
            System.out.println("er vi her???");
            for(Direction wallDirection: typeOfWall){
                if(getPlayerXYDirection(player) == wallDirection.getXyDirection())
                    System.out.println();
                        return canMove(wallDirection,player,xPosition,yPosition);

            }
        }
        return 2;
    }
    public boolean hasPlayerAndWallSameLocation(Player player, float xPosition, float yPosition){
        //System.out.println("Player y position " + player.getPlayerYPosition()/300 + " yPosition " + yPosition);
        //System.out.println("Player x position " + player.getPlayerXPosition()/300 + " xPosition " + xPosition);
        return player.getPlayerYPosition()/300 == yPosition && player.getPlayerXPosition()/300 == xPosition;
    }
// Printe dir lokasjon og alt inni her og se hvorfor den bare returnerer 2
    public Integer canMove(Direction wall, Player player, int  xPosition, int yPosition){
        System.out.println("WAAALLLL");
        //System.out.println("Is oppisite " + isOpposite(player,wall));
        //System.out.println("has same location " + hasPlayerAndWallSameLocation(player,xPosition, yPosition));
        System.out.println("same dir " + !isOpposite(player,wall));
            if(player.direction == wall && hasPlayerAndWallSameLocation(player,xPosition,yPosition)){
               // System.out.println("Player " + player.direction + "Wall dir " + wall);
                System.out.println("Return 0");
                return 0;

          //  }else if(player.direction == wall && !hasPlayerAndWallSameLocation(player,xPosition,yPosition)){
            //    return 1;
            }
            else if((isOpposite(player,wall) && !hasPlayerAndWallSameLocation(player,xPosition, yPosition))){
                System.out.println("return 1 ");
                return 1;
            }
        System.out.println("return 2");
        System.out.println("");
        return 2;
    }
    public boolean isOpposite(Player player, Direction wall){
        if (getPlayerXYDirection(player) == wall.getXyDirection()){
            //System.out.println("player direction " + player.direction + " wall direction  " + wall);
            return player.direction != wall;
        }
        return false;
    }
    public boolean shouldPlayerMoveToWallTile(Player player, Direction wallDirection){
        return player.direction == wallDirection;

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
