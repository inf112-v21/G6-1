package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Conveyor {

Set<Player> alreadyMovedPlayers;

final HashMap<Integer,Integer> yellowConveyorDirection = new HashMap<>() {{
   put(49, Direction.NORTH.getDirectionDegree());
   put(50, Direction.SOUTH.getDirectionDegree());
   put(51, Direction.WEST.getDirectionDegree());
   put(52, Direction.EAST.getDirectionDegree());
}};

final HashMap<Integer,Integer> blueConveyorDirection = new HashMap<>() {{
   put(13,Direction.NORTH.getDirectionDegree());
   put(14,Direction.EAST.getDirectionDegree());
   put(21,Direction.SOUTH.getDirectionDegree());
   put(22,Direction.WEST.getDirectionDegree());
}};



    /**
     * Iterates conveyor layer, finds blue and yellow conveyors
     * Then calls the method for location players on conveyors
     * @param players List of players in game
     * @param yellowConveyor TiledMapTileLayer
     * @param blueConveyor TiledMapTileLayer
     */
    public void runConveyor(ArrayList<Player> players,
           TiledMapTileLayer yellowConveyor, TiledMapTileLayer blueConveyor){
        int xStart = Direction.WEST.getBoundaryCoordinate();
        int xEnd = Direction.EAST.getBoundaryCoordinate();
        int yStart = Direction.SOUTH.getBoundaryCoordinate();
        int yEnd = Direction.NORTH.getBoundaryCoordinate();
        TiledMapTileLayer.Cell yellowConveyorTile;
        TiledMapTileLayer.Cell blueConveyorTile;
        alreadyMovedPlayers = new HashSet<>();

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

                    locatePlayersOnConveyor(players, tileXPosition, tileYPosition, conveyorDirection,
                            conveyorMovement);
                }
            }
        }
    }

    /**
     * This method locates players that are standing on a conveyor, then calls the
     * movePlayerOnConveyor method to move those players. The players are also added to
     * alreadyMovedPlayers set to keep them from being moved if they are already moved
     * this round
     * @param players List of players in the game
     * @param tileXPosition X position of the tile a conveyor is located
     * @param tileYPositions y position of the tile a conveyor is located
     * @param conveyorDirection  the direction of the conveyor in that tile
     * @param conveyorMovement number of moves. one if yellow conveyor two if blue conveyor
     */
    public void locatePlayersOnConveyor(ArrayList<Player> players, int tileXPosition, int tileYPositions,
           int conveyorDirection, int conveyorMovement) {
        for(Player player: players){
            if(!alreadyMovedPlayers.contains(player)
                        && player.getPlayerXPosition() == (float) tileXPosition
                        && player.getPlayerYPosition() == (float) tileYPositions){
                    alreadyMovedPlayers.add(player);
                    movePlayerOnConveyor(player, conveyorDirection, conveyorMovement);
            }
        }
    }

    /**
     * Moves players to new tile if standing on conveyor
     * @param player list of players in the game
     * @param conveyorDirection the direction of the conveyor in that tile
     * @param conveyorMovement number of moves. one if yellow conveyor two if blue conveyor
     */
    public void movePlayerOnConveyor(Player player, int conveyorDirection, int conveyorMovement){
        float oldPlayerXPos = player.getPlayerXPosition();
        float oldPlayerYPos = player.getPlayerYPosition();
        if(conveyorDirection == Direction.NORTH.getDirectionDegree()) {
            player.updatePlayerYPosition(movePlayerAsFarAsPossible(player,
                    player.getPlayerYPosition() + conveyorMovement, Direction.NORTH));
        }
        if(conveyorDirection == Direction.SOUTH.getDirectionDegree()) {
            player.updatePlayerYPosition(movePlayerAsFarAsPossible(player,
                    player.getPlayerYPosition() - conveyorMovement, Direction.SOUTH));
        }
        if(conveyorDirection == Direction.EAST.getDirectionDegree()) {
            player.updatePlayerXPosition(movePlayerAsFarAsPossible(player,
                    player.getPlayerXPosition() + conveyorMovement, Direction.EAST));
        }
        if(conveyorDirection == Direction.WEST.getDirectionDegree()) {
            player.updatePlayerXPosition(movePlayerAsFarAsPossible(player,
                    player.getPlayerXPosition() - conveyorMovement, Direction.WEST));
        }
        if(player.getPlayerYPosition() == 0 && player.getPlayerXPosition() == 1500){
            player.updatePlayerYPosition(300);
        }
    }


    /**
     * If a players move would take the player outside the board, this method return the
     * end coordinate at the board
     * @param player the player
     * @param move amount of movement
     * @param moveDir direction of movement
     * @return move
     */
    public float movePlayerAsFarAsPossible(Player player, float move, Direction moveDir){
         if(moveDir == Direction.NORTH && !player.isPlayerOnBoard(player.getPlayerXPosition(),move)) return 3900;
         else if(moveDir == Direction.SOUTH && !player.isPlayerOnBoard(player.getPlayerXPosition(),move) ) return 0;
         else if(moveDir == Direction.WEST && !player.isPlayerOnBoard(move, player.getPlayerYPosition()) ) return 0;
         else if(moveDir == Direction.EAST && !player.isPlayerOnBoard(move, player.getPlayerYPosition()) ) return 3300;
         return move;
     }


}
