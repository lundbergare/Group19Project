package Model;


public class PowerUpFactory {
    protected static SpeedPowerUp createSpeedPowerUp(int x, int y) {
        return new SpeedPowerUp(x, y);
    }

    protected static ShieldPowerUp createShieldPowerUp(int x, int y) {
        return new ShieldPowerUp(x, y);
    }

    protected static SizePowerUp createSizePowerUp(int x, int y) {
        return new SizePowerUp(x, y);
    }
}