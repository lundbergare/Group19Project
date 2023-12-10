package View;

import java.awt.*;

public class SizePowerUp {
    private Point position;
    private boolean active;
    private long activationTime;

    public SizePowerUp(int x, int y) {
        this.position = new Point(x, y);
        this.active = true;
    }

    public Point getPosition() {
        return position;
    }

    public boolean isActive() {
        return active;
    }

    public void activate() {
        this.active = false;
        this.activationTime = System.currentTimeMillis();
    }

    public long getActivationTime() {
        return activationTime;
    }
}

