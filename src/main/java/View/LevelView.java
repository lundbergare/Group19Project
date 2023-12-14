package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class LevelView extends JPanel implements LevelListener{
    private static final int YAXIS = 1000;
    private static final int XAXIS = 3000;
    private final Image heartImage;
    private final Image keyImage;

    private final coinSprite coinSprite;
    private final PlayerView playerView;
    private final LevelCamera camera;
    private final ArrayList<Coin> coins;
    private final ArrayList<Platform> platforms;
    private final ArrayList<Key> keys;
    private final ArrayList<Enemy> enemies;
    private String score;
    private int lives;
    private final Level level;
    private final ProjectView view;
    private final Image speedPowerUpImage;
    private final Image shieldPowerUpImage;
    private final Image sizePowerUpImage;

    public LevelView(Level level, ProjectView view) {
        setPreferredSize(new Dimension(XAXIS, YAXIS));
        setBackground(new Color(68, 138, 184));
        ImageIcon icon = new ImageIcon("src/main/java/Model/images/GameHeart.png");
        heartImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        ImageIcon iconKey = new ImageIcon("src/main/java/Model/images/key.png");
        keyImage = iconKey.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        coinSprite = new coinSprite();
        camera = new LevelCamera(1000, 750);

        coins = level.getCoins();
        platforms = level.getPlatforms();
        keys = level.getKeys();
        playerView = new PlayerView(level.getPlayer());
        enemies = level.getEnemies();
        this.level = level;
        this.view = view;
        addKeyListener(level.getPlayerController());

        ImageIcon icon1 = new ImageIcon("src/main/java/View/ImagesForView/raspberry.png");
        speedPowerUpImage = icon1.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon("src/main/java/View/ImagesForView/shield2.png");
        shieldPowerUpImage = icon2.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon icon3 = new ImageIcon("src/main/java/View/ImagesForView/mushroom.png");
        sizePowerUpImage = icon3.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        level.startTimer();
        setFocusable(true);
        requestFocusInWindow();

        this.level.setLevelListener(this);
    }

    private void paintUserInterface(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Collected coins: " + score + "/" + Coin.NUM_COINS, 10, 20);

        for (int i = 0; i < lives; i++) {
            g.drawImage(heartImage, 10 + (i * 30), 40, this);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Collected keys: " + keys + "/" + Key.NUM_KEYS, 300, 20);

        for (int i = 0; i < keys.size(); i++) {
            g.drawImage(keyImage, 10 + (i * 30), 60, this);
        }
    }

    private void drawCoins(Graphics g) {
        for (Coin coin : coins) {
            coinSprite.drawCoin(g, coin);
        }
    }

    private void drawEnemies(Graphics g) {
        for (Enemy enemy: this.enemies) {
            EnemyView enemyView = new EnemyView(enemy);
            enemyView.draw(g);
        }
    }


    private void drawKey(Graphics g, Key key) {
        Point pos = key.getPos();
        g.setColor(Color.gray);
        g.drawImage(keyImage, pos.x, pos.y, 25, 25, null);
        g.setColor(Color.gray);
    }

    private void drawKeys(Graphics g) {
        for (Key key : keys) {
            drawKey(g, key);
        }
    }

    @Override
    protected void paintComponent (Graphics g){
            super.paintComponent(g);
            paintUserInterface(g);
            drawLevel(g);
            Toolkit.getDefaultToolkit().sync();
        }

        @Override
        public void onTimerTick(){
            camera.update(level.getPlayer().getPos(), 3000, 1000);
            uiUpdate();
            repaint();
            gameStatusCheck();
        }

        private void gameStatusCheck(){
            if (level.isGameLost()) {
                view.showGameOverScreen();
                level.stopTimer();
            }

            if (level.isGameWon()) {
                view.showVictoryScreen();
                level.stopTimer();
        }
    }

        private void uiUpdate(){
            setScore(level.getPlayer().getScore());
            setLives(level.getPlayer().getLives());
        }

        private void drawLevel (Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform originalTransform = g2d.getTransform();
            g2d.translate(-camera.getX(), -camera.getY());

            for (Platform platform : this.platforms) {
                PlatformView platformView = new PlatformView(platforms);
                platformView.draw(g2d);
            }

            playerView.draw(g);
            drawEnemies(g);
            drawKeys(g);
            drawCoins(g);

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
            g2d.setTransform(originalTransform);
            g2d.dispose();
        }
        public void setScore (String score){
            this.score = score;
        }
        public void setLives ( int lives){
            this.lives = lives;
        }

    }
