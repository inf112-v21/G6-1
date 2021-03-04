package inf112.skeleton.app.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;

import java.util.HashMap;

public class PlayerGraphics {

    public HashMap<Color, HashMap<Direction, Texture>> getPlayerTextures() {
        HashMap<Direction,Texture> greenPiece = new HashMap<>();
        HashMap<Direction,Texture> greyPiece = new HashMap<>();
        HashMap<Direction,Texture> orangePiece = new HashMap<>();
        HashMap<Direction,Texture> purplePiece = new HashMap<>();
        HashMap<Direction,Texture> pinkPiece = new HashMap<>();
        HashMap<Color, HashMap<Direction, Texture>> playerTextures = new HashMap<>();

        greenPiece.put(Direction.EAST, new Texture("Player/RobotGreenEast.png"));
        greenPiece.put(Direction.WEST, new Texture("Player/RobotGreenWest.png"));
        greenPiece.put(Direction.NORTH, new Texture("Player/RobotGreenNorth.png"));
        greenPiece.put(Direction.SOUTH, new Texture("Player/RobotGreenSouth.png"));

        greyPiece.put(Direction.EAST, new Texture("Player/RobotGreyEast.png"));
        greyPiece.put(Direction.WEST, new Texture("Player/RobotGreyWest.png"));
        greyPiece.put(Direction.NORTH, new Texture("Player/RobotGreyNorth.png"));
        greyPiece.put(Direction.SOUTH, new Texture("Player/RobotGreySouth.png"));

        orangePiece.put(Direction.EAST, new Texture("Player/RobotOrangeEast.png"));
        orangePiece.put(Direction.WEST, new Texture("Player/RobotOrangeWest.png"));
        orangePiece.put(Direction.NORTH, new Texture("Player/RobotOrangeNorth.png"));
        orangePiece.put(Direction.SOUTH, new Texture("Player/RobotOrangeSouth.png"));

        pinkPiece.put(Direction.EAST, new Texture("Player/RobotPinkEast.png"));
        pinkPiece.put(Direction.WEST, new Texture("Player/RobotPinkWest.png"));
        pinkPiece.put(Direction.NORTH, new Texture("Player/RobotPinkNorth.png"));
        pinkPiece.put(Direction.SOUTH, new Texture("Player/RobotPinkSouth.png"));

        purplePiece.put(Direction.EAST, new Texture("Player/RobotPurpleEast.png"));
        purplePiece.put(Direction.WEST, new Texture("Player/RobotPurpleWest.png"));
        purplePiece.put(Direction.NORTH, new Texture("Player/RobotPurpleNorth.png"));
        purplePiece.put(Direction.SOUTH, new Texture("Player/RobotPurpleSouth.png"));
        playerTextures.put(Color.GREEN,greenPiece);
        playerTextures.put(Color.ORANGE,orangePiece);
        playerTextures.put(Color.GREY,greyPiece);
        playerTextures.put(Color.PINK,pinkPiece);
        playerTextures.put(Color.PURPLE,purplePiece);

        return playerTextures;
    }

}
