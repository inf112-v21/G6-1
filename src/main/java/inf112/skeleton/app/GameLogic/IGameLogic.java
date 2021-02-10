package inf112.skeleton.app.GameLogic;

import inf112.skeleton.app.location.Location;

public interface IGameLogic {

    /**
     * Finds location
     *
     * @param
     * @return
     */
  Location getLocation();


    /**
     * Sets new location
     *
     * @param
     * @return
     */
  Location setNewLocation();

    /**
     * Checks if there is an object at a given location...
     *
     * @param
     * @return
     */

    //Location isLocationEmpty();

    /**
     * Gets the current X coordinate
     *
     * @param
     * @return
     */

  Location getXCoordinate();

    /**
     * Gets the current Y coordinate
     *
     * @param
     * @return
     */

  Location getYCoordinate();
}
