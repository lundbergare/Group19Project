package View;

import java.awt.*;

public class ShieldPowerUp {
    private Point position;
    private boolean active;
    private long activationTime;

    public ShieldPowerUp(int x, int y) {
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

