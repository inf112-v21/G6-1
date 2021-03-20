package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;

public class Conveyer {


    public void findLasersAndFire(ArrayList<Player> players, TiledMapTileLayer yellowConveyor){
        for(int xDirectionTiles = Direction.WEST.getBoundaryCoordinate(); xDirectionTiles <= Direction.NORTH.getBoundaryCoordinate(); xDirectionTiles+=300) {
            for (int yDirectionTiles = Direction.SOUTH.getBoundaryCoordinate(); yDirectionTiles <= Direction.EAST.getBoundaryCoordinate(); yDirectionTiles += 300) {
                TiledMapTileLayer.Cell laserTile = yellowConveyor.getCell(xDirectionTiles / 300, yDirectionTiles / 300);
                if (laserTile != null) {
                    shootPlayers(players,xDirectionTiles,yDirectionTiles, Direction.NORTH);
                }
            }
        }
    }

    public void shootPlayers(ArrayList<Player> players, int xTile, int yTile, Direction conveyerDirection){
        for(Player player: players){
            if(player.getPlayerXPosition() == (float) xTile && player.getPlayerYPosition() == (float) yTile){

                player.dealDamageToPlayer();
            }
        }
    }
    public void movePlayerOnConveyer(Player player, Direction conveyerDirection){
        if(conveyerDirection == Direction.NORTH) player.updatePlayerXPosition(player.movePlayerAsFarAsPossible(player.getPlayerXPosition()+1, ));
        if(conveyerDirection == Direction.SOUTH) player.updatePlayerXPosition();

    }


}
