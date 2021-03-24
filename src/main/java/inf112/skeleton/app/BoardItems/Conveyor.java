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
   put(13,Direction.NORTH.getDirectionDegree());
   put(14,Direction.EAST.getDirectionDegree());
   put(21,Direction.SOUTH.getDirectionDegree());
   put(22,Direction.WEST.getDirectionDegree());
}};




//TODO add turns for ned delivery


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
        float oldPlayerXPos = player.getPlayerXPosition();
        float oldPlayerYPos = player.getPlayerYPosition();
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
        System.out.println("Player " + player.color +" was standing on a conveyor and was moved "
                + conveyorMovement/300 + " tiles");
        System.out.println("Old position was x " + oldPlayerXPos / 300 + " y " + oldPlayerYPos / 300 +
                " New position is x " + player.getPlayerXPosition() / 300  + " y " + player.getPlayerYPosition() / 300);
        System.out.println(" ");

    }



}
