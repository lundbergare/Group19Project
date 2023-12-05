package Model;


import java.util.ArrayList;


public class LevelTEST extends Level {

    private Platform platform;
    private final Enemy enemy;
    private PowerUpModel powerUpModel;
    private SpeedPowerUpModel speedPowerUpModel;
    private ShieldPowerUpModel shieldPowerUpModel;



    public LevelTEST() {
        super();
        Platform platform1 = new Platform(0, 500, 300, 50);
        Platform platform2 = new Platform(370, 500, 200, 50);
        Platform platform3 = new Platform(400, 300, 200, 50);
        Platform platform4 = new Platform(670, 300, 200, 50);

        platforms.add(platform1);
        platforms.add(platform2);
        platforms.add(platform3);
        platforms.add(platform4);
        coins = Coin.populateCoins();
        keys = Key.populateKeys();
        enemy = new Enemy(500, 450, 1, 850, 10, 10);

        powerUpModel = new PowerUpModel(200, 420);
        speedPowerUpModel = new SpeedPowerUpModel(150, 420);
        shieldPowerUpModel = new ShieldPowerUpModel(450, 250);

    }

    @Override
    protected void updateLevel() {
        // Level-specific TICK



        ProjectModel.platformCollision(player, platforms);
        Coin.collectCoins(player, coins);
        Key.collectKeys(player, keys);
        enemy.move();
        enemy.kill(player, enemy);
        player.kill(player, enemy);
    }


    public Enemy getEnemy() {
        return enemy;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }
    @Override
    public int getXAxisLimit() {
        return XAXIS;
    }

    @Override
    public int getYAxisLimit() {
        return YAXIS;
    }

}