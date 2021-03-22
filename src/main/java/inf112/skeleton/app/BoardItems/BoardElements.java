package inf112.skeleton.app.BoardItems;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;

import java.util.*;


public class BoardElements {


    Set<Player> playersToMove;
    HashMap<String,ArrayList> boardElementsPositions = new HashMap<>();
    ArrayList<Integer> laserPositions = new ArrayList<>();
    ArrayList<Integer> blueConveyorPositions = new ArrayList<>();
    ArrayList<Integer> yellowConveyorPositions = new ArrayList<>();
    ArrayList<Integer> greenGearPositions = new ArrayList<>();
    ArrayList<Integer> redGearPositions = new ArrayList<>();
    ArrayList<Integer> blueConveyorDirections = new ArrayList<>();
    ArrayList<Integer> yellowConveyorDirections = new ArrayList<>();

    Conveyor conveyor = new Conveyor();
    Laser laser = new Laser();
    Gear gear = new Gear();


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
                    yellowConveyorDirections.add(yellowConveyorTile.getTile().getId());

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
        boardElementsPositions.put("yellowConveyorDirection", yellowConveyorDirections);
        /*
        System.out.println("Laser " + boardElementsPositions.get("laser"));
        System.out.println("yellowConveyor " + boardElementsPositions.get("yellowConveyor"));
        System.out.println("BlueConveyor " + boardElementsPositions.get("blueConveyor"));
        System.out.println("redGear " + boardElementsPositions.get("redGear"));
        System.out.println("greenGear " + boardElementsPositions.get("greenGear"));

         */

    }



    ArrayList<String> boardItems = new ArrayList<>(Arrays.asList("laser","yellowConveyor","blueConveyor","redGear","greenGear"));
    public void locatePlayers(ArrayList<Player> players){
        playersToMove = new HashSet<>();
        for(String Items: boardItems){
            ArrayList<Integer> boardPiecePositions = boardElementsPositions.get(Items);
            //System.out.println(Items);
                int index = 0;
                for (int i = 0; i < boardPiecePositions.size() / 2; i++) {
                    switch (Items) {
                        case "laser":
                           laser.damagePlayerInHarmsWay(players,300,300);
                            break;
                        case "yellowConveyor":
                            //System.out.println(boardPiecePositions.get(index));
                            //System.out.println(boardPiecePositions.get(index+1));
                            int test = (int) boardElementsPositions.get("yellowConveyorDirection").get(index/2);
                            conveyor.locatePlayersOnConveyor(players,boardPiecePositions.get(index),boardPiecePositions.get(index+1),test
                                    ,ConveyorType.COMMON.getNumberOfMoves());
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
