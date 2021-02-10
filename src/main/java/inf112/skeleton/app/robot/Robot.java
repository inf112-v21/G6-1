package inf112.skeleton.app.robot;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class Robot {

    // Creates a robot to assign to a player

    private final String name;
    private final Color color;
    // private Image image;

    public Robot(String name, Color color) {

        this.name = name;
        this.color = color;

    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    // public Image getImage() { return image; }

}
