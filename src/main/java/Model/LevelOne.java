package Model;

public class LevelOne extends Level {

    private final Enemy enemy;
    private final Enemy enemy2;

    public LevelOne() {
        super();

        Platform platform1 = PlatformFactory.createPlatform(0,500,300,50);
        Platform platform2 = PlatformFactory.createPlatform(370, 500, 200, 50);
        Platform platform3 = PlatformFactory.createPlatform(400, 300, 200, 50);
        Platform platform4 = PlatformFactory.createPlatform(670, 300, 200, 50);
        Platform platform5 = PlatformFactory.createPlatform(470, 700, 800, 50);
        Platform platform6 = PlatformFactory.createPlatform(380, 400, 800, 50);


        platforms.add(platform1);
        platforms.add(platform2);
        platforms.add(platform3);
        platforms.add(platform4);

        platforms.add(platform5);
        platforms.add(platform6);

        coins = Coin.populateCoins();
        keys = Key.populateKeys();
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

}
