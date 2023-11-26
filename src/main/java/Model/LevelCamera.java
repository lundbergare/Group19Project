package Model;

import java.awt.Point;

public class LevelCamera {
    private int x, y;
    private final int width, height;

    public LevelCamera(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void update(Point playerPos, int levelWidth, int levelHeight) {
        // Center the camera on the player, but don't go out of the level bounds
        x = Math.max(0, Math.min(playerPos.x - width / 2, levelWidth - width));
        y = Math.max(0, Math.min(playerPos.y - height / 2, levelHeight - height));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

