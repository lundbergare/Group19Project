package Model;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LevelOne extends Level {

    private final Enemy enemy;
    private final Enemy enemy2;

    public LevelOne() {
        super();

        //This is quite ugly, but I think it is really easy to understand how we are creating platforms, keys and coins etc.
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

        ArrayList<Point> coinPositions = new ArrayList<>();
        coinPositions.add(new Point(60, 450));
        coinPositions.add(new Point(120, 450));
        coinPositions.add(new Point(180, 450));
        coinPositions.add(new Point(240, 450));


        coins = Coin.populateCoins(coinPositions);
        ArrayList<Point> keyPositions = new ArrayList<>();
        keyPositions.add(new Point(580, 270));
        keyPositions.add(new Point(500, 460));

        keys = Key.populateKeys(keyPositions);



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
