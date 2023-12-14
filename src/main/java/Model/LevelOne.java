package Model;


import java.awt.*;
import java.util.ArrayList;

public class LevelOne extends Level {
    private final Enemy enemy;
    private final Enemy enemy2;
    private final Enemy enemy3;
    private final SpeedPowerUp speedPowerUp;
    private final ShieldPowerUp shieldPowerUp;
    private final SizePowerUp sizePowerUp;
    public LevelOne() {
        super();
        speedPowerUp = PowerUpFactory.createSpeedPowerUp(975, 330);
        shieldPowerUp = PowerUpFactory.createShieldPowerUp(200, 450);
        sizePowerUp = PowerUpFactory.createSizePowerUp(2450, 300);

        PlatformFactory.createPlatform(0,500,300);

        PlatformFactory.createPlatform(100, 340,50);
        PlatformFactory.createPlatform(250, 270,100);
        PlatformFactory.createPlatform(520, 500,250);
        PlatformFactory.createPlatform(760, 370,100);
        PlatformFactory.createPlatform(950,370, 100 );
        PlatformFactory.createPlatform(1000, 220, 50);
        PlatformFactory.createPlatform(1100, 500, 250);
        PlatformFactory.createPlatform(1200, 370, 50);
        PlatformFactory.createPlatform(1450, 500, 150);
        PlatformFactory.createPlatform(1700, 400, 150);
        PlatformFactory.createPlatform(1850, 500, 250);
        PlatformFactory.createPlatform(1900, 300, 50);
        PlatformFactory.createPlatform(2050, 300, 50);
        PlatformFactory.createPlatform(2100,400, 100);
        PlatformFactory.createPlatform(2350, 400, 600);

        platforms = PlatformFactory.getPlatforms();

        ArrayList<Point> coinPositions = new ArrayList<>();
        coinPositions.add(new Point(60, 450));
        coinPositions.add(new Point(250, 200));
        coinPositions.add(new Point(550, 450));
        coinPositions.add(new Point(250, 450));
        coins = CoinFactory.createCoins(coinPositions);


        ArrayList<Point> keyPositions = new ArrayList<>();
        keyPositions.add(new Point(300, 230));
        keyPositions.add(new Point(1010, 180));
        keyPositions.add(new Point(2000,460));
        keys = KeyFactory.createKeys(keyPositions);

        enemy = new Enemy(400,300,1,700,10,10,6);
        enemies.add(enemy);
        enemy2 = new Enemy(520, 450, 1, 700, 10, 10, 3);
        enemies.add(enemy2);
        enemy3 = new Enemy(1850, 450, 1, 2100, 10,10,3);
        enemies.add(enemy3);

    }


    @Override
    public int getXAxisLimit() {
        return XAXIS;
    }

    @Override
    public int getYAxisLimit() {
        return YAXIS;
    }

    public boolean isSpeedPowerUpActive() {
        return speedPowerUp.isActive();
    }

    public Point getSpeedPowerUpPosition() {
        return speedPowerUp.getPosition();
    }

    public boolean isShieldPowerUpActive() {
        return shieldPowerUp.isActive();
    }

    public Point getShieldPowerUpPosition() {
        return shieldPowerUp.getPosition();
    }

    public boolean isSizePowerUpActive() {
        return sizePowerUp.isActive();
    }

    public Point getSizePowerUpPosition() {
        return sizePowerUp.getPosition();
    }

    @Override
    protected void updateLevel() {
        // Level-specific TICK
        PlatformCollision.platformCollision(getPlayer(), platforms);
        Coin.collectCoins(getPlayer(), coins);
        Key.collectKeys(getPlayer(), keys);
        enemy.move();
        enemy2.move();
        enemy3.move();
        enemy.kill(getPlayer(), enemy);
        enemy2.kill(getPlayer(), enemy2);
        getPlayer().kill(getPlayer(), enemy);
        getPlayer().kill(getPlayer(), enemy2);
        enemy3.kill(getPlayer(),enemy3);
        getPlayer().kill(getPlayer(),enemy3);

        if (speedPowerUp.isActive() && getPlayer().getPos().distance(speedPowerUp.getPosition()) < 25) {
            speedPowerUp.activate();
            getPlayer().activateSpeedBoost();
        }

        if (shieldPowerUp.isActive() && getPlayer().getPos().distance(shieldPowerUp.getPosition()) < 25) {
            shieldPowerUp.activate();
            getPlayer().activateShield(5000);
        }

        if (sizePowerUp.isActive() && getPlayer().getPos().distance(sizePowerUp.getPosition()) < 25) {
            sizePowerUp.activate();
            getPlayer().activateSizeBoost(5000);
        }
    }
    public Enemy getEnemy() {
        return enemy;
    }

    
}
