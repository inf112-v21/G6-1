package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;

public class Hole {


    /**
     * This method iterates over the game board and finds tiles containing holes.
     * Than call playersInHole to find and take a life from players standing in holes.
     * @param players list of players in the game
     * @param holeLayer TiledMapTileLayer
     */
        public void hole(ArrayList<Player> players, TiledMapTileLayer holeLayer){
        int xStart = Direction.WEST.getBoundaryCoordinate();
        int xEnd = Direction.EAST.getBoundaryCoordinate();
        int yStart = Direction.SOUTH.getBoundaryCoordinate();
        int yEnd = Direction.NORTH.getBoundaryCoordinate();
        TiledMapTileLayer.Cell holeTile;

        for(int tileXPositions = xStart; tileXPositions <= xEnd; tileXPositions +=300) {
            for (int tileYPositions = yStart; tileYPositions <= yEnd; tileYPositions += 300) {
                holeTile = holeLayer.getCell(tileXPositions / 300, tileYPositions / 300);
                if (holeTile != null) {
                    playersInHole(players, (float) tileXPositions,(float) tileYPositions);
                }
            }
        }
    }

    /**
     * Find players in holes and takes a life from them
     * @param players list of players in the game
     * @param tileXPosition x position on hole tile
     * @param tileYPosition y position on hole tile
     */
    public void playersInHole(ArrayList<Player> players, float tileXPosition, float tileYPosition){
        for(Player player: players){
            if(player.getPlayerXPosition() == tileXPosition && player.getPlayerYPosition() == tileYPosition){
                player.takePlayerLife();


            }
        }
    }

}
