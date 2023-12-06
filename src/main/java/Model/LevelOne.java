package Model;
import View.ProjectView;
import java.awt.*;
import java.util.ArrayList;

/**
 * Represents the first level, extended from Level class.
 *
 * Defines specific configuration for LevelOne while inheriting
 * from the Level class.
 *
 */
public class LevelOne extends Level {
    private final Enemy enemy;
    private final Enemy enemy2;

    /**
     * Constructs LevelOne, initializing the platforms, power-ups, coins, keys and enemies.
     *
     * @param projectView ProjectView instance associated with LevelOne
     */
    public LevelOne(ProjectView projectView) {
        super(projectView);

        Platform platform1 = PlatformFactory.createPlatform(0,500,300,50);
        Platform platform2 = PlatformFactory.createPlatform(370, 500, 200, 50);
        Platform platform3 = PlatformFactory.createPlatform(400, 300, 200, 50);
        Platform platform4 = PlatformFactory.createPlatform(670, 300, 200, 50);
        Platform platform5 = PlatformFactory.createPlatform(470, 700, 800, 50);
        Platform platform6 = PlatformFactory.createPlatform(380, 400, 800, 50);

        powerUpModel = new PowerUpModel(200, 420);
        speedPowerUpModel = new SpeedPowerUpModel(150, 420);
        shieldPowerUpModel = new ShieldPowerUpModel(450, 250);

        platforms.add(platform1);
        platforms.add(platform2);
        platforms.add(platform3);
        platforms.add(platform4);
        platforms.add(platform5);
        platforms.add(platform6);

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



        enemy = new Enemy(500, 450, 1, 850, 10, 10);
        enemy2 = new Enemy(500, 650, 1, 650, 10, 10);
    }

    @Override
    public int getXAxisLimit() {
        return XAXIS;
    }

    @Override
    public int getYAxisLimit() {
        return YAXIS;
    }
    /**
     * Updates game features for the level
     *
     * Is responsible for updating the level by performing
     * actions related to platform collisions, coin and keys,
     * enemy movements, and the player killing enemies.
     */

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
    }
    public Enemy getEnemy() {
        return enemy;
    }
    public Enemy getEnemy2() {
        return enemy2;
    }

    public PowerUpModel getPowerUpModel() {
        return powerUpModel;
    }

    public SpeedPowerUpModel getSpeedPowerUpModel() {
        return speedPowerUpModel;
    }

    public ShieldPowerUpModel getShieldPowerUpModel() {
        return shieldPowerUpModel;
    }
    
}
