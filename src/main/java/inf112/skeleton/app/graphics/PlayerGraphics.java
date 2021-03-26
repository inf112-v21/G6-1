package inf112.skeleton.app.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;

import java.util.HashMap;

public class PlayerGraphics {
    private HashMap<Color, HashMap<Direction, Texture>> playerTextures;
    private HashMap<Color, Sprite> playerSprites;

    public PlayerGraphics() {
        this.createPlayerTextures();
        this.createPlayerSprite();
    }

    /**
     * Get player texture
     * @param color color of the player
     * @param direction direction the player is facing
     * @return Texture of the player
     */
    public Texture getPlayerTexture(Color color, Direction direction) {
        return playerTextures.get(color).get(direction);
    }

    public Texture getPlayerTexture(Player player) {
        return getPlayerTexture(player.color, player.direction);
    }

    public Sprite getPlayerSprite(Color color) {
        return playerSprites.get(color);
    }

    public Sprite getPlayerSprite(Player player) {
        return getPlayerSprite(player.color);
    }

    /**
     * Creates a hashmap with all player textures (all colors and all directions)
     * Get the texture by the player color and direction.
     * @return Hashmap of player textures
     */
    private void createPlayerTextures() {
        HashMap<Direction,Texture> greenPiece = new HashMap<>();
        HashMap<Direction,Texture> greyPiece = new HashMap<>();
        HashMap<Direction,Texture> orangePiece = new HashMap<>();
        HashMap<Direction,Texture> purplePiece = new HashMap<>();
        HashMap<Direction,Texture> pinkPiece = new HashMap<>();
        playerTextures = new HashMap<>();

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
    }

    /**
     * Creates a hashmap of player sprites.
     * Get the sprite by the players color
     * @return Hashmap of player sprites
     */
    private void createPlayerSprite(){
        this.playerSprites = new HashMap<>();
        playerSprites.put(Color.ORANGE,new Sprite(getPlayerTexture(Color.ORANGE, Direction.NORTH)));
        playerSprites.put(Color.GREEN,new Sprite(getPlayerTexture(Color.GREEN, Direction.NORTH)));
        playerSprites.put(Color.PURPLE,new Sprite(getPlayerTexture(Color.PURPLE, Direction.NORTH)));
        playerSprites.put(Color.PINK,new Sprite(getPlayerTexture(Color.PINK, Direction.NORTH)));
        playerSprites.put(Color.GREY,new Sprite(getPlayerTexture(Color.GREY, Direction.NORTH)));
    }
}
