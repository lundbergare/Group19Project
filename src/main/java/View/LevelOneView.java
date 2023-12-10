package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class LevelOneView extends LevelView{
    protected EnemyView enemyView;
    private final EnemyView enemyView2;
    private final EnemyView enemyView3;
    private Image speedPowerUpImage;
    private Image shieldPowerUpImage;
    private Image sizePowerUpImage;

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
        enemyView3 = new EnemyView(level.getEnemy3());


        ImageIcon icon = new ImageIcon("src/main/java/View/ImagesForView/raspberry.png");
        speedPowerUpImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon("src/main/java/View/ImagesForView/shield2.png");
        shieldPowerUpImage = icon2.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon icon3 = new ImageIcon("src/main/java/View/ImagesForView/mushroom.png");
        sizePowerUpImage = icon3.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

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
        enemyView3.draw(g);
        drawKeys(g);

        /*if (level.speedPowerUp.isActive()) {
            Point pos = level.speedPowerUp.getPosition();
            g.drawImage(speedPowerUpImage, pos.x, pos.y, null);
        }*/

        if (level.isSpeedPowerUpActive()) {
            Point pos = level.getSpeedPowerUpPosition();
            g.drawImage(speedPowerUpImage, pos.x, pos.y, null);
        }

        if (level.isShieldPowerUpActive()) {
            Point pos = level.getShieldPowerUpPosition();
            g.drawImage(shieldPowerUpImage, pos.x, pos.y, null);
        }

        if (level.isSizePowerUpActive()) {
            Point pos = level.getSizePowerUpPosition();
            g.drawImage(sizePowerUpImage, pos.x, pos.y, null);
        }

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
