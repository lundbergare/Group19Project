package View;

import Model.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class LevelOneView extends LevelView{
    protected EnemyView enemyView;
    protected EnemyView enemyView2;

    private LevelOne level;


    public LevelOneView(LevelOne level){
        super();
        this.level = level;
        coins = level.getCoins();
        platforms = level.getPlatforms();
        keys = level.getKeys();
        super.playerView = new PlayerView(level.player);
        enemyView = new EnemyView(level.getEnemy());
        enemyView2 = new EnemyView(level.getEnemy2());

        //keyView = new KeyView();

//        powerUpView = new PowerUpView(level.getPowerUpModel());
//        speedPowerUpView = new SpeedPowerUpView(level.getSpeedPowerUpModel());
//        shieldPowerUpView = new ShieldPowerUpView(level.getShieldPowerUpModel());

        addKeyListener(level.getPlayerController());


        // Make sure the view is focusable and has focus for key events to work
        level.startTimer();
        setFocusable(true);
        requestFocusInWindow();

        this.level.setLevelListener(this);

    }

    @Override
    public void onTimerTick(){
        camera.update(level.player.getPos(), 3000, 1000);
        uiUpdate();


        repaint();
    }

    public void uiUpdate(){
        setScore(level.player.getScore());
        setLives(level.player.getLives());
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCoins(g);
        drawKeys(g);
        paintUserInterface(g);
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

//        powerUpView.draw(g2d);
//
//        speedPowerUpView.draw(g2d);
//
//        shieldPowerUpView.draw(g2d);

        drawCoins(g);

        g2d.setTransform(originalTransform);
        g2d.dispose(); // dispose the graphics copy

    }


}
