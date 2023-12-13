package Model;

import View.ProjectView;
import View.ShieldPowerUp;
import View.SizePowerUp;
import View.SpeedPowerUp;

import java.awt.*;
import java.util.ArrayList;

public class LevelOne extends Level {

    private final Enemy enemy;
    private final Enemy enemy2;
    private final Enemy enemy3;

    private final SpeedPowerUp speedPowerUp;
    private final ShieldPowerUp shieldPowerUp;
    private SizePowerUp sizePowerUp;

    public LevelOne() {
        super();
        speedPowerUp = new SpeedPowerUp(150, 420);
        shieldPowerUp = new ShieldPowerUp(200, 420);
        sizePowerUp = new SizePowerUp(500, 250);

        //This is quite ugly, but I think it is really easy to understand how we are creating platforms, keys and coins etc.
        PlatformFactory.createPlatform(0,500,300);


        PlatformFactory.createPlatform(100, 340,50);
        PlatformFactory.createPlatform(250, 270,100);
        PlatformFactory.createPlatform(520, 500,250);
        PlatformFactory.createPlatform(760, 370,100);
        PlatformFactory.createPlatform(950,370, 100 );
        PlatformFactory.createPlatform(1000, 220, 50);
        PlatformFactory.createPlatform(1100, 500, 250);
        PlatformFactory.createPlatform(1200, 370, 50);
        PlatformFactory.createPlatform(1450, 500, 150); //x:1600
        PlatformFactory.createPlatform(1700, 400, 150);
        PlatformFactory.createPlatform(1850, 500, 250);
        PlatformFactory.createPlatform(1900, 300, 50);
        PlatformFactory.createPlatform(2050, 300, 50);

        platforms = PlatformFactory.getPlatforms();

        ArrayList<Point> coinPositions = new ArrayList<>();
        coinPositions.add(new Point(60, 450));
        coinPositions.add(new Point(120, 450));
        coinPositions.add(new Point(180, 450));
        coinPositions.add(new Point(240, 450));
        coins = CoinFactory.createCoins(coinPositions);


        ArrayList<Point> keyPositions = new ArrayList<>();
        keyPositions.add(new Point(300, 230));
        keyPositions.add(new Point(1010, 180));
        keyPositions.add(new Point(2000,460));
        keys = KeyFactory.createKeys(keyPositions);


        //enemy = new Enemy(650, 300, 1, 790, 10, 10, 3);
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
        ProjectModel.platformCollision(player, platforms);
        Coin.collectCoins(player, coins);
        Key.collectKeys(player, keys);
        enemy.move();
        enemy2.move();
        enemy3.move();
        enemy.kill(player, enemy);
        enemy2.kill(player, enemy2);
        player.kill(player, enemy);
        player.kill(player, enemy2);

        if (speedPowerUp.isActive() && player.getPos().distance(speedPowerUp.getPosition()) < 25) {
            speedPowerUp.activate();
            player.activateSpeedBoost(5000); // 5 seconds
        }

        if (shieldPowerUp.isActive() && player.getPos().distance(shieldPowerUp.getPosition()) < 25) {
            shieldPowerUp.activate();
            player.activateShield(5000); // 5 seconds
        }

        if (sizePowerUp.isActive() && player.getPos().distance(sizePowerUp.getPosition()) < 25) {
            sizePowerUp.activate();
            player.activateSizeBoost(5000); // 5 seconds
        }

    }
    public Enemy getEnemy() {
        return enemy;
    }
    public Enemy getEnemy2() {
        return enemy2;
    }

    public Enemy getEnemy3(){
        return enemy3;
    }



    
}
