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

    /**
     * legge singleplayer inn i playersliste
     */
    HashMap<Direction,Integer> endPositions = getLocationEndPositions();

    public HashMap<Direction, Integer> getLocationEndPositions() {
        HashMap<Direction,Integer> locationEndPosition = new HashMap<>();
        locationEndPosition.put(Direction.NORTH, 3900);
        locationEndPosition.put(Direction.SOUTH, 0);
        locationEndPosition.put(Direction.WEST, 0);
        locationEndPosition.put(Direction.EAST, 3300);
        return locationEndPosition;
    }

    public void ShootLaser(ArrayList<Player> players){
        int checkFromPosition = (int) this.position +300;
        int checkToPosition = endPositions.get(this.direction);
        String laserDirection = "Y";;
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
    public void shootPlayersXDirection(ArrayList<Player> players, int tile){
        for(Player player: players){
            if(player.getPlayerXPosition() == (float) tile) player.dealDamageToPlayer();
        }
    }

    public void shootPlayersYDirection(ArrayList<Player> players, int tile){
        for(Player player: players){
            if(player.getPlayerYPosition() == (float) tile) player.dealDamageToPlayer();
        }
    }
}
