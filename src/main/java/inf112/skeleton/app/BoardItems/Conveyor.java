package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Conveyor {

Set<Player> playersToMove;
HashMap<Integer,Integer> yellowConveyorDirection = new HashMap<>() {{
   put(49, Direction.NORTH.getDirectionDegree());
   put(50, Direction.SOUTH.getDirectionDegree());
   put(51, Direction.WEST.getDirectionDegree());
   put(52, Direction.EAST.getDirectionDegree());
}};


HashMap<Integer,Integer> blueConveyorDirection = new HashMap<>() {{
   put(14,Direction.EAST.getDirectionDegree());
   put(21,Direction.SOUTH.getDirectionDegree());
}};

//Set<Player> playersToMove;
//TODO when player collision is implemented
// når en en annen spiller står ved enden av rullebåndet,
// da skal spilleren som blir flyttet stoppe opp siden en spiller ikkje kan pushe andre spillere

//TODO vil noen av rullebåndene ha svinger??


    public void findAndRunConveyor(ArrayList<Player> players,
           TiledMapTileLayer yellowConveyor, TiledMapTileLayer blueConveyor){
        int xStart = Direction.WEST.getBoundaryCoordinate();
        int xEnd = Direction.EAST.getBoundaryCoordinate();
        int yStart = Direction.SOUTH.getBoundaryCoordinate();
        int yEnd = Direction.NORTH.getBoundaryCoordinate();
        TiledMapTileLayer.Cell yellowConveyorTile;
        TiledMapTileLayer.Cell blueConveyorTile;
        playersToMove  = new HashSet<>();

        for(int tileXPosition = xStart; tileXPosition <= xEnd; tileXPosition += 300) {
            for (int tileYPosition = yStart; tileYPosition <= yEnd; tileYPosition += 300) {
                yellowConveyorTile = yellowConveyor.getCell(tileXPosition / 300, tileYPosition / 300);
                blueConveyorTile = blueConveyor.getCell(tileXPosition / 300, tileYPosition / 300);

                if (yellowConveyorTile != null) {
                    int conveyorDirection = yellowConveyorDirection.get(yellowConveyorTile.getTile().getId());
                    int conveyorMovement = ConveyorType.COMMON.getNumberOfMoves();
                    locatePlayersOnConveyor(players, tileXPosition, tileYPosition, conveyorDirection ,conveyorMovement);
                }
                if(blueConveyorTile != null) {
                    int conveyorDirection = blueConveyorDirection.get(blueConveyorTile.getTile().getId());
                    int conveyorMovement = ConveyorType.EXPRESS.getNumberOfMoves();
                    locatePlayersOnConveyor(players, tileXPosition, tileYPosition, conveyorDirection,conveyorMovement);
                }
            }
        }
    }

    public void locatePlayersOnConveyor(ArrayList<Player> players, int tileXPosition, int tileYPositions,
           int conveyorDirection, int numberOfMoves){
        //int direction = yellowConveyorDirection.get(conveyorDirection);
        System.out.println(conveyorDirection);
        for(Player player: players){
            if(!playersToMove.contains(player)
                        && player.getPlayerXPosition() == (float) tileXPosition
                        && player.getPlayerYPosition() == (float) tileYPositions){
                    playersToMove.add(player);
                    movePlayerOnConveyor(player, conveyorDirection, numberOfMoves);
            }
        }
    }

    public void movePlayerOnConveyor(Player player, int conveyorDirection, int conveyorMovement){

        if(conveyorDirection == Direction.NORTH.getDirectionDegree()) {
            player.updatePlayerYPosition(player.movePlayerAsFarAsPossible(
                    player.getPlayerYPosition() + conveyorMovement, Direction.NORTH ));
        }
        if(conveyorDirection == Direction.SOUTH.getDirectionDegree()) {
            player.updatePlayerYPosition(player.movePlayerAsFarAsPossible(
                    player.getPlayerYPosition() - conveyorMovement, Direction.SOUTH));
        }
        if(conveyorDirection == Direction.EAST.getDirectionDegree()) {
            player.updatePlayerXPosition(player.movePlayerAsFarAsPossible(
                    player.getPlayerXPosition() + conveyorMovement, Direction.EAST));
        }
        if(conveyorDirection == Direction.WEST.getDirectionDegree()) {
            player.updatePlayerXPosition(player.movePlayerAsFarAsPossible(
                    player.getPlayerXPosition() - conveyorMovement, Direction.WEST));
        }
    }



}
