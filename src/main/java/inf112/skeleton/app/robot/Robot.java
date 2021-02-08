package inf112.skeleton.app.robot;

import java.awt.*;

public class Robot {

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
