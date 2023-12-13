package Model;

import Controller.PlayerController;
import View.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public abstract class Level extends JPanel implements ActionListener, IBoundary {

    protected static final int YAXIS = 800;
    protected static final int XAXIS = 3500;

    protected Timer timer;
    public Player player;
    protected PlayerController playerController;
    private LevelListener listener;

    protected ArrayList<Platform> platforms; // Declare the ArrayList for platforms
    protected ArrayList<Coin> coins;

    protected ArrayList<Key> keys;

    protected ArrayList<Enemy> enemies;

    private long lastTime = System.nanoTime();
    private final double NS_PER_UPDATE = 1000000000.0 / 60.0; //Updates per second, 100 now
    private double accumulatedTime = 0.0;

    public Level() {
        setPreferredSize(new Dimension(YAXIS, XAXIS));
        setBackground(new Color(68, 138, 184));

        platforms = new ArrayList<Platform>();
        keys = new ArrayList<Key>();
        enemies = new ArrayList<Enemy>();


        player = new Player(this);
        playerController = new PlayerController(player);

        addKeyListener(playerController);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        //ImageIcon icon = new ImageIcon("src/main/java/Model/images/GameHeart.png");
        //heartImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        timer = new Timer(1, this);
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long now = System.nanoTime();
        double delta = (now - lastTime) / NS_PER_UPDATE; // Calculate delta time
        lastTime = now;

        accumulatedTime += delta;
        while (accumulatedTime >= 1) {
            player.tick(); // Update player logic
            updateLevel();
            if (listener != null) {
                listener.onTimerTick();
            }
            // Other game logic updates here...
            accumulatedTime -= 1;
        }

    }

    public boolean winGameCheck(){
        return player.getKeyScore() == Key.NUM_KEYS && player.getCenterX() > 2800;
    }

    public boolean loseGameCheck(){
        return player.getLives() <= 0;
    }

    protected abstract void updateLevel();

    public void setLevelListener(LevelListener listener) {
        this.listener = listener;
    }

    public abstract boolean isSpeedPowerUpActive();

    public abstract Point getSpeedPowerUpPosition();

    public abstract boolean isShieldPowerUpActive();

    public abstract Point getShieldPowerUpPosition();

    public abstract boolean isSizePowerUpActive();

    public abstract Point getSizePowerUpPosition();

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public ArrayList<Key> getKeys() {
        return keys;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }


}
