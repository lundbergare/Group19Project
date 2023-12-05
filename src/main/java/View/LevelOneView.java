package View;

import Model.LevelOne;
import Model.Platform;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class LevelOneView extends LevelView{
    protected EnemyView enemyView;
    protected EnemyView enemyView2;


    public LevelOneView(LevelOne level){
        super();
        coins = level.getCoins();
        platforms = level.getPlatforms();
        keys = level.getKeys();
        super.playerView = new PlayerView(level.player);
        enemyView = new EnemyView(level.getEnemy());
        enemyView2 = new EnemyView(level.getEnemy2());

        keyView = new KeyView();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCoins(g);
        drawKeys(g);
        drawLevel(g);
        Toolkit.getDefaultToolkit().sync();

    }

    @Override
    protected void drawLevel(Graphics g) {
        // Level-specific drawing code
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform();
        g2d.translate(-camera.getX(), -camera.getY());


        for (Platform platform : this.platforms) {
            PlatformView platformView = new PlatformView(platforms);
            platformView.draw(g2d);
        }

        playerView.draw(g);
        enemyView.draw(g);
        enemyView2.draw(g);
        drawKeys(g);
        /*
        powerUpView.draw(g2d, powerUpModel);

        speedPowerUpView.draw(g2d, speedPowerUpModel);

        shieldPowerUpView.draw(g2d, shieldPowerUpModel);
        */
        drawCoins(g);

        g2d.setTransform(originalTransform);
        g2d.dispose(); // dispose the graphics copy

    }


}
