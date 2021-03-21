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
HashMap<Integer,Integer> yellowConveyorDirection = new HashMap<>() {{
   put(49,Direction.NORTH.getDirectionDegree());
   put(50, Direction.SOUTH.getDirectionDegree());
   put(51,Direction.WEST.getDirectionDegree());
   put(52,Direction.EAST.getDirectionDegree());
}};
//TODO add N and W when i get it from Yasmin (mulig de ligger i masterrbannch)
HashMap<Integer,Integer> blueConveyorDirection = new HashMap<>() {{
   put(14,Direction.EAST.getDirectionDegree());
   put(21,Direction.SOUTH.getDirectionDegree());
}};

Set<Player> playersToMove;
//TODO when player collision is implemented
// når en en annen spiller står ved enden av rullebåndet,
// da skal spilleren som blir flyttet stoppe opp siden en spiller ikkje kan pushe andre spillere

//TODO vil noen av rullebåndene ha svinger??
    public void findAndRunConveyor(ArrayList<Player> players, TiledMapTileLayer yellowConveyor, TiledMapTileLayer blueConveyor, TiledMapTileLayer redGear, TiledMapTileLayer greenGear){
        playersToMove  = new HashSet<>();
        for(int xDirectionTiles = Direction.WEST.getBoundaryCoordinate(); xDirectionTiles <= Direction.NORTH.getBoundaryCoordinate(); xDirectionTiles+=300) {
            for (int yDirectionTiles = Direction.SOUTH.getBoundaryCoordinate(); yDirectionTiles <= Direction.EAST.getBoundaryCoordinate(); yDirectionTiles += 300) {
                TiledMapTileLayer.Cell yellowConveyorTile = yellowConveyor.getCell(xDirectionTiles / 300, yDirectionTiles / 300);
                TiledMapTileLayer.Cell blueConveyorTile = blueConveyor.getCell(xDirectionTiles / 300, yDirectionTiles / 300);
                TiledMapTileLayer.Cell redGearTile = redGear.getCell(xDirectionTiles / 300, yDirectionTiles / 300);
                TiledMapTileLayer.Cell greenGearTile = greenGear.getCell(xDirectionTiles / 300, yDirectionTiles / 300);
                if (yellowConveyorTile != null) {
                    locatePlayersOnConveyor(players,xDirectionTiles,yDirectionTiles, yellowConveyorDirection.get(yellowConveyorTile.getTile().getId()),ConveyorType.COMMON.getNumberOfMoves());
                }
                if(blueConveyorTile != null) {
                    locatePlayersOnConveyor(players,xDirectionTiles,yDirectionTiles, blueConveyorDirection.get(blueConveyorTile.getTile().getId()),ConveyorType.EXPRESS.getNumberOfMoves());
                }
                if(redGearTile != null){
                    locatePlayersOnConveyor(players, xDirectionTiles,yDirectionTiles,(int) Action.ROTATE_LEFT.getAction(), ConveyorType.GEAR.getNumberOfMoves());
                }
                if(greenGearTile != null){
                    locatePlayersOnConveyor(players, xDirectionTiles,yDirectionTiles,(int) Action.ROTATE_RIGHT.getAction(), ConveyorType.GEAR.getNumberOfMoves());
                }
            }
        }
    }

    public void locatePlayersOnConveyor(ArrayList<Player> players, int xTile, int yTile, int conveyorDirection, int numberOfMoves){
        for(Player player: players){
            if(!playersToMove.contains(player) && player.getPlayerXPosition() == (float) xTile && player.getPlayerYPosition() == (float) yTile){
                if(numberOfMoves!= 0) {
                    playersToMove.add(player);
                    movePlayerOnConveyor(player, conveyorDirection, numberOfMoves);
                }else {
                    rotatePlayerOnGear(player,conveyorDirection);
                }
            }
        }
    }

    public void movePlayerOnConveyor(Player player, int conveyorDirection, int numberOfMoves){
        if(conveyorDirection == Direction.NORTH.getDirectionDegree()) {
            player.updatePlayerYPosition(player.movePlayerAsFarAsPossible(player.getPlayerYPosition()+numberOfMoves, Direction.NORTH ));
        }
        if(conveyorDirection == Direction.SOUTH.getDirectionDegree()) {
            player.updatePlayerYPosition(player.movePlayerAsFarAsPossible(player.getPlayerYPosition()-numberOfMoves,Direction.SOUTH));
        }
        if(conveyorDirection == Direction.EAST.getDirectionDegree()) {
            player.updatePlayerXPosition(player.movePlayerAsFarAsPossible(player.getPlayerXPosition()+numberOfMoves,Direction.EAST));
        }
        if(conveyorDirection == Direction.WEST.getDirectionDegree()) {
            player.updatePlayerXPosition(player.movePlayerAsFarAsPossible(player.getPlayerXPosition()-numberOfMoves,Direction.WEST));
        }
    }

    public void rotatePlayerOnGear(Player player, int turnAction) {
            player.setPlayerDirection(turnAction);
    }



}
