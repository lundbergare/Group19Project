package Model;

import View.ProjectView;
import View.ShieldPowerUp;
import View.SpeedPowerUp;

import java.awt.*;
import java.util.ArrayList;

public class LevelOne extends Level {

    private final Enemy enemy;
    private final Enemy enemy2;

    private final SpeedPowerUp speedPowerUp;
    private final ShieldPowerUp shieldPowerUp;

    public LevelOne(ProjectView projectView) {
        super(projectView);
        speedPowerUp = new SpeedPowerUp(150, 420);
        shieldPowerUp = new ShieldPowerUp(200, 420);

        //This is quite ugly, but I think it is really easy to understand how we are creating platforms, keys and coins etc.
        PlatformFactory.createPlatform(0,500,300,50);
        PlatformFactory.createPlatform(370, 500, 200, 50);
        PlatformFactory.createPlatform(400, 300, 200, 50);
        PlatformFactory.createPlatform(670, 300, 200, 50);
        PlatformFactory.createPlatform(470, 700, 800, 50);
        PlatformFactory.createPlatform(380, 400, 800, 50);
        platforms = PlatformFactory.getPlatforms();

        ArrayList<Point> coinPositions = new ArrayList<>();
        coinPositions.add(new Point(60, 450));
        coinPositions.add(new Point(120, 450));
        coinPositions.add(new Point(180, 450));
        coinPositions.add(new Point(240, 450));
        coins = CoinFactory.createCoins(coinPositions);


        ArrayList<Point> keyPositions = new ArrayList<>();
        keyPositions.add(new Point(580, 270));
        keyPositions.add(new Point(500, 460));
        keys = KeyFactory.createKeys(keyPositions);


        enemy = new Enemy(500, 450, 1, 850, 10, 10, 3);
        enemy2 = new Enemy(500, 650, 1, 650, 10, 10, 3);

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

    @Override
    protected void updateLevel() {
        // Level-specific TICK
        ProjectModel.platformCollision(player, platforms);
        Coin.collectCoins(player, coins);
        Key.collectKeys(player, keys);
        enemy.move();
        enemy2.move();
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

    }
    public Enemy getEnemy() {
        return enemy;
    }
    public Enemy getEnemy2() {
        return enemy2;
    }

    
}
