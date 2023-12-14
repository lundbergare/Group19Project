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

        x = playerPos.x - width / 2;

        x = Math.max(0, Math.min(x, levelWidth - width));

        y = playerPos.y - height / 2;

        y = Math.max(0, Math.min(y, levelHeight - height));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

