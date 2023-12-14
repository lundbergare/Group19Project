package Model;

import java.awt.*;

public class SpeedPowerUp implements IPowerUp {
    private final Point position;
    private boolean active;

    public SpeedPowerUp(int x, int y) {
        this.position = new Point(x, y);
        this.active = true;
    }
    @Override
    public Point getPosition() {
        return position;
    }
    @Override
    public boolean isActive() {
        return active;
    }
    @Override
    public void activate() {
        this.active = false;
    }

}
