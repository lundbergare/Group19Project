package View;

import Model.Platform;

import java.awt.*;
import java.util.ArrayList;

public class PlatformView {
    private final ArrayList<Platform> platforms;

    protected PlatformView(ArrayList<Platform> platforms) {
        this.platforms = platforms;
    }
    protected void draw(Graphics g) {
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
