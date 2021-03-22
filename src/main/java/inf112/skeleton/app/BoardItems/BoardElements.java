package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class BoardElements {



    HashMap<String,ArrayList> boardElementsPositions = new HashMap<>();
    ArrayList<Integer> laserPositions = new ArrayList<>();
    ArrayList<Integer> blueConveyorPositions = new ArrayList<>();
    ArrayList<Integer> yellowConveyorPositions = new ArrayList<>();
    ArrayList<Integer> greenGearPositions = new ArrayList<>();
    ArrayList<Integer> redGearPositions = new ArrayList<>();
    Conveyor conveyor;
    Laser laser;


    public void getBoardElementPositions(ArrayList<Player> players,
           TiledMapTileLayer laserLayer,
           TiledMapTileLayer redGear,
           TiledMapTileLayer greenGear,
           TiledMapTileLayer yellowConveyor,
           TiledMapTileLayer blueConveyor){

        int xStart = Direction.WEST.getBoundaryCoordinate();
        int xEnd = Direction.EAST.getBoundaryCoordinate();
        int yStart = Direction.SOUTH.getBoundaryCoordinate();
        int yEnd = Direction.NORTH.getBoundaryCoordinate();
        TiledMapTileLayer.Cell laserTile;
        TiledMapTileLayer.Cell yellowConveyorTile;
        TiledMapTileLayer.Cell blueConveyorTile;
        TiledMapTileLayer.Cell redGearTile;
        TiledMapTileLayer.Cell greenGearTile;


        for(int tileXPositions = xStart; tileXPositions <= xEnd; tileXPositions +=300) {
            for (int tileYPositions = yStart; tileYPositions <= yEnd; tileYPositions += 300) {

                laserTile = laserLayer.getCell(tileXPositions / 300, tileYPositions / 300);
                yellowConveyorTile = yellowConveyor.getCell(tileXPositions / 300, tileYPositions / 300);
                blueConveyorTile = blueConveyor.getCell(tileXPositions / 300, tileYPositions / 300);
                redGearTile = redGear.getCell(tileXPositions / 300, tileYPositions / 300);
                greenGearTile = greenGear.getCell(tileXPositions / 300, tileYPositions / 300);

                if (laserTile != null) {
                    laserPositions.add(tileXPositions);
                    laserPositions.add(tileYPositions);
                }
                if (yellowConveyorTile != null) {
                    yellowConveyorPositions.add(tileXPositions);
                    yellowConveyorPositions.add(tileYPositions);
                }
                if (blueConveyorTile != null) {
                    blueConveyorPositions.add(tileXPositions);
                    blueConveyorPositions.add(tileYPositions);

                }
                if (redGearTile != null) {
                    redGearPositions.add(tileXPositions);
                    redGearPositions.add(tileYPositions);

                }
                if (greenGearTile != null) {
                    greenGearPositions.add(tileXPositions);
                    greenGearPositions.add(tileYPositions);
                }
            }
        }
        boardElementsPositions.put("laser", laserPositions);
        boardElementsPositions.put("yellowConveyor", yellowConveyorPositions);
        boardElementsPositions.put("blueConveyor", blueConveyorPositions);
        boardElementsPositions.put("redGear", redGearPositions);
        boardElementsPositions.put("greenGear", greenGearPositions);
        /*
        System.out.println("Laser " + boardElementsPositions.get("laser"));
        System.out.println("yellowConveyor " + boardElementsPositions.get("yellowConveyor"));
        System.out.println("BlueConveyor " + boardElementsPositions.get("blueConveyor"));
        System.out.println("redGear " + boardElementsPositions.get("redGear"));
        System.out.println("greenGear " + boardElementsPositions.get("greenGear"));

         */

    }


    public void damagePlayerInHarmsWay(ArrayList<Player> players, float tileXPosition, float tileYPosition){
        for(Player player: players){
            if(player.getPlayerXPosition() == tileXPosition && player.getPlayerYPosition() == tileYPosition){
                player.dealDamageToPlayer();
            }
        }
    }
    ArrayList<String> boardItems = new ArrayList<>(Arrays.asList("laser","yellowConveyor","blueConveyor","redGear","greenGear"));
    public void locatePlayers(ArrayList<Player> players){

        for(String Items: boardItems){
            ArrayList<Integer> boardPiecePositions = boardElementsPositions.get(Items);
            for(Player player: players) {
                int index = 0;
                for (int i = 0; i < boardPiecePositions.size() / 2; i++) {
                    //System.out.println(boardPiecePositions.get(index) + " X");
                    //System.out.println(boardPiecePositions.get(index + 1) + " Y");

                    switch (Items) {
                        case "laser":
                            //System.out.println("laser");
                            if(player.getPlayerXPosition() == boardPiecePositions.get(index) && player.getPlayerYPosition() == boardPiecePositions.get(index + 1)){
                                System.out.println(player.damageTaken);
                                player.dealDamageToPlayer();
                                System.out.println(player.damageTaken);
                            }
                            break;
                        case "yellowConveyor":
                            //System.out.println("yellowConveyor");
                            break;
                        case "blueConveyor":
                            //System.out.println("blueConveyor");
                            break;
                        case "redGear":
                            //System.out.println("redGear");
                            break;
                        case "greenGear":
                            //System.out.println("greenGear");
                            break;
                    }
                    index += 2;
                }
            }
        }


    }
}
