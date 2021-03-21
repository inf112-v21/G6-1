package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;

public class Conveyor {

//TODO when player collision is implemented
// når en en annen spiller står ved enden av rullebåndet,
// da skal spilleren som blir flyttet stoppe opp siden en spiller ikkje kan pushe andre spillere

//TODO vil noen av rullebåndene ha svinger??
    public void findAndRunConveyor(ArrayList<Player> players, TiledMapTileLayer yellowConveyor, TiledMapTileLayer blueConveyor){
        for(int xDirectionTiles = Direction.WEST.getBoundaryCoordinate(); xDirectionTiles <= Direction.NORTH.getBoundaryCoordinate(); xDirectionTiles+=300) {
            for (int yDirectionTiles = Direction.SOUTH.getBoundaryCoordinate(); yDirectionTiles <= Direction.EAST.getBoundaryCoordinate(); yDirectionTiles += 300) {
                TiledMapTileLayer.Cell yellowConveyorTile = yellowConveyor.getCell(xDirectionTiles / 300, yDirectionTiles / 300);
                TiledMapTileLayer.Cell blueConveyorTile = blueConveyor.getCell(xDirectionTiles / 300, yDirectionTiles / 300);
                if (yellowConveyorTile != null) {
                    //System.out.println(yellowConveyorTile.getTile().getId());
                   //System.out.println(yellowConveyorTile.getTile().getProperties().get("yConveyorNorth"));
                    //System.out.println(yellowConveyorTile.getTile().getProperties().get("yConveyorSouth"));
                    //System.out.println(yellowConveyorTile.getTile().getProperties().get("yConveyorWest"));
                    //System.out.println(yellowConveyorTile.getTile().getProperties().get("yConveyorEast"));
                    locatePlayersOnConveyor(players,xDirectionTiles,yDirectionTiles, yellowConveyorTile.getTile().getId(),ConveyorType.COMMON.getNumberOfMoves());
                }
                if(blueConveyorTile != null) {
                    locatePlayersOnConveyor(players,xDirectionTiles,yDirectionTiles, blueConveyorTile.getTile().getId(),ConveyorType.EXPRESS.getNumberOfMoves());
                }
            }
        }
    }

    public void locatePlayersOnConveyor(ArrayList<Player> players, int xTile, int yTile, int conveyorDirection, int numberOfMoves){
        for(Player player: players){
            if(player.getPlayerXPosition() == (float) xTile && player.getPlayerYPosition() == (float) yTile){
                movePlayerOnConveyor(player,conveyorDirection, numberOfMoves);
            }
        }
    }

    public void movePlayerOnConveyor(Player player, int conveyorDirection, int numberOfMoves){
        if(conveyorDirection == Direction.NORTH.getDirectionDegree()) {
            player.updatePlayerXPosition(player.movePlayerAsFarAsPossible(player.getPlayerXPosition()+numberOfMoves, Direction.NORTH ));
        }
        if(conveyorDirection == Direction.SOUTH.getDirectionDegree()) {
            player.updatePlayerXPosition(player.movePlayerAsFarAsPossible(player.getPlayerXPosition()-numberOfMoves,Direction.SOUTH));
        }
        if(conveyorDirection == Direction.EAST.getDirectionDegree()) {
            player.updatePlayerYPosition(player.movePlayerAsFarAsPossible(player.getPlayerYPosition()+numberOfMoves,Direction.EAST));
        }
        if(conveyorDirection == Direction.WEST.getDirectionDegree()) {
            player.updatePlayerYPosition(player.movePlayerAsFarAsPossible(player.getPlayerYPosition()-numberOfMoves,Direction.WEST));
        }
    }

    public void rotatePlayerOnGear(Player player, String gearDirection) {
        if (gearDirection == "clockwise") {
            //player.direction == player.direction +90;

        }
    }



}
