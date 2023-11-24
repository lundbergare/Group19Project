package View;

import Model.Platform;

import java.awt.*;

public class PlatformView {
    private Platform platform;

    public PlatformView(Platform platform) {
        this.platform = platform;
    }

    public void draw(Graphics g) {
        int xPos = platform.getXPos();
        int yPos = platform.getYPos();
        int width = platform.getWidth();
        int height = platform.getHeight();

        g.setColor(Color.gray);
        g.fillRect(xPos, yPos, width, height);
    }
}
