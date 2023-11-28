package View;

import Model.Platform;

import java.awt.*;
import java.util.ArrayList;

public class PlatformView {
    private ArrayList<Platform> platforms; // Update to hold multiple platforms

    public PlatformView(ArrayList<Platform> platforms) {
        this.platforms = platforms;
    }
    public void draw(Graphics g) {
        for (Platform platform : platforms) {
            int xPos = platform.getXPos();
            int yPos = platform.getYPos();
            int width = platform.getWidth();
            int height = platform.getHeight();

            g.setColor(Color.pink);
            g.fillRect(xPos, yPos, width, height);
        }
    }
}
