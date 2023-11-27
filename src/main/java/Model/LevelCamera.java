package Model;

import java.awt.Point;

public class LevelCamera {
    private int x, y;
    private final int width, height;

    public LevelCamera(int width, int height) {
        this.width = width;
        this.height = height;
    }

    //gammal metod, eventuellt ta bort
    /*public void update(Point playerPos, int levelWidth, int levelHeight) {
        // Center the camera on the player, but don't go out of the level bounds
        x = Math.max(0, Math.min(playerPos.x - width / 2, levelWidth - width));
        y = Math.max(0, Math.min(playerPos.y - height / 2, levelHeight - height));
    }*/

    public void update(Point playerPos, int levelWidth, int levelHeight) {
        // center the camera on the player
        // adjust the camera's x-coordinate
        x = playerPos.x - width / 2;
        // ensure the camera doesn't go outside the level's left/right boundaries
        x = Math.max(0, Math.min(x, levelWidth - width));

        // Adjust the camera's y-coordinate similarly if needed
        y = playerPos.y - height / 2;
        // same as above but for the up/down boundaries
        y = Math.max(0, Math.min(y, levelHeight - height));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

