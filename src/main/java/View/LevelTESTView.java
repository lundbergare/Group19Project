package View;

import Model.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class LevelTESTView extends LevelView {

    private PowerUpView powerUpView;
    private PowerUpModel powerUpModel;

    private SpeedPowerUpView speedPowerUpView;
    private SpeedPowerUpModel speedPowerUpModel;

    private ShieldPowerUpView shieldPowerUpView;
    private ShieldPowerUpModel shieldPowerUpModel;

    protected EnemyView enemyView;


    public LevelTESTView(LevelTEST levelTEST){
        super();
        coins = levelTEST.getCoins();
        platforms = levelTEST.getPlatforms();
        keys = levelTEST.getKeys();
        super.playerView = new PlayerView(levelTEST.player);
        enemyView = new EnemyView(levelTEST.getEnemy());
        keyView = new KeyView();

        powerUpView = new PowerUpView(powerUpModel);
        speedPowerUpView = new SpeedPowerUpView(speedPowerUpModel);
        shieldPowerUpView = new ShieldPowerUpView(shieldPowerUpModel);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCoins(g);
        drawKeys(g);
        drawLevel(g);
        Toolkit.getDefaultToolkit().sync();

    }

    protected void drawLevel(Graphics g){
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


