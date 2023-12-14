package Model;


public class PowerUpFactory {
    public static SpeedPowerUp createSpeedPowerUp(int x, int y) {
        return new SpeedPowerUp(x, y);
    }

    public static ShieldPowerUp createShieldPowerUp(int x, int y) {
        return new ShieldPowerUp(x, y);
    }

    public static SizePowerUp createSizePowerUp(int x, int y) {
        return new SizePowerUp(x, y);
    }
}