package Model;

import java.awt.Point;

public class SpeedPowerUpModel {
    private Point position;
    private boolean isActive;
    private long activationTime;

    public SpeedPowerUpModel(int x, int y) {
        this.position = new Point(x, y);
        this.isActive = true;
    }

    public Point getPosition() {
        return position;
    }

    public boolean isActive() {
        return isActive;
    }

    public void activate() {
        isActive = false;
        activationTime = System.currentTimeMillis();
    }

    public boolean isEffectActive() {
        // check if 5 seconds have passed since activation of power up
        return System.currentTimeMillis() - activationTime < 5000;
    }

    public void reset() {
        isActive = true;
    }
}
