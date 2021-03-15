package inf112.skeleton.app.BoardItems;

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
    public void shootLaser(ArrayList<Player> players){
        int checkFromPosition = (int) this.position +300;
        int checkToPosition = endPositions.get(this.direction);
        String laserDirection = "Y";
        if(this.direction == Direction.SOUTH || this.direction == Direction.WEST){
            checkFromPosition = endPositions.get(this.direction);
            checkToPosition = (int) this.position -300;
            laserDirection = "X";
        }
        for(int tile = checkFromPosition; tile <= checkToPosition; tile+=300){
            if( laserDirection == "X"){
                shootPlayersXDirection(players,tile);
            }else if(laserDirection == "Y"){
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
