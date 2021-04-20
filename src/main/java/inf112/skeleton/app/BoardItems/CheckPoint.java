package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;

public class CheckPoint {

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
                    System.out.println("Player old checkpoint x" + player.playerCheckpointPositionX + " old y " + player.playerCheckpointPositionY );
                    player.setNewPlayerCheckpointLocation(tileXPositions,tileYPositions);
                    System.out.println("Player new checkpoint x" + player.playerCheckpointPositionX + " new y " + player.playerCheckpointPositionY );

                }
            }
        }
    }

}
