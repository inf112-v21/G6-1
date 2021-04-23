package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;


public class CheckPoint {

    /**
     * Finds checkpoints matching player positions
     * @param player current
     * @param checkpoints TiledMapTileLayer
     */
    public void findCheckpoints(Player player , TiledMapTileLayer checkpoints){
        int startBoundaryX = Direction.WEST.getBoundaryCoordinate();
        int endBoundaryX = Direction.EAST.getBoundaryCoordinate();
        int startBoundaryY = Direction.SOUTH.getBoundaryCoordinate();
        int endBoundaryY = Direction.NORTH.getBoundaryCoordinate();
        TiledMapTileLayer.Cell checkpoint;

        for(int tileXPositions = startBoundaryX; tileXPositions <= endBoundaryX; tileXPositions +=300) {
            for (int tileYPositions = startBoundaryY; tileYPositions <= endBoundaryY; tileYPositions += 300) {
                checkpoint = checkpoints.getCell(tileXPositions / 300, tileYPositions / 300);
                if (checkpoint != null && player.getPlayerXPosition() == tileXPositions
                            && player.getPlayerYPosition() == tileYPositions ) {
                    player.setNewPlayerCheckpointLocation(tileXPositions,tileYPositions);
                }
            }
        }
    }

}
