package Model;

import java.awt.Point;

public class SpeedPowerUpModel implements IPowerUp {
    private Point position;
    private boolean isActive;
    private long activationTime;

    public SpeedPowerUpModel(int x, int y) {
        this.position = new Point(x, y);
        this.isActive = true;
    }
    @Override
    public Point getPosition() {
        return position;
    }
    @Override
    public boolean isActive() {
        return isActive;
    }
    @Override
    public void activate() {
        isActive = false;
        activationTime = System.currentTimeMillis();
    }
    @Override
    public boolean isEffectActive() {
        // check if 5 seconds have passed since activation of power up
        return System.currentTimeMillis() - activationTime < 5000;
    }

}
