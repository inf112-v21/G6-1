package inf112.skeleton.app.BoardItems;

import inf112.skeleton.app.graphics.TileLayers;
import inf112.skeleton.app.player.Player;

import java.util.ArrayList;

public class BoardItems {

    public Conveyor conveyor = new Conveyor();
    public Gear gear = new Gear();
    public Laser laser = new Laser();
    public Hole hole = new Hole();

    /**
     * This method runs all active board items:
     * Gear
     * Conveyors
     * Laser
     * Hole
     * @param players list of players in the game
     * @param layers all tiled layers
     */
    public void activateBoardItems(ArrayList<Player> players, TileLayers layers){
        conveyor.runConveyor(players, layers.yellowConveyor, layers.blueConveyor);
        gear.runGears(players,layers.redGear, layers.greenGear);
        laser.fireAllLasers(players,layers.laser);
        hole.hole(players, layers.hole);
    }
}
