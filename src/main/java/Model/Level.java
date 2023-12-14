package Model;

import Controller.PlayerController;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public abstract class Level extends JPanel implements ActionListener, IBoundary {

    protected static final int YAXIS = 800;
    protected static final int XAXIS = 2900;

    protected Timer timer;
    private final Player player;
    private final PlayerController playerController;
    private LevelListener listener;

    protected ArrayList<Platform> platforms;
    protected ArrayList<Coin> coins;

    protected ArrayList<Key> keys;

    protected ArrayList<Enemy> enemies;

    private long lastTime = System.nanoTime();
    private double accumulatedTime = 0.0;

    protected Level() {
        setPreferredSize(new Dimension(YAXIS, XAXIS));
        setBackground(new Color(68, 138, 184));

        platforms = new ArrayList<>();
        keys = new ArrayList<>();
        enemies = new ArrayList<>();

        player = new Player(this);
        playerController = new PlayerController(player);

        addKeyListener(playerController);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer = new Timer(1, this);
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer(){
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long now = System.nanoTime();
        //Updates per second, 100 now
        double NS_PER_UPDATE = 1000000000.0 / 60.0;
        double delta = (now - lastTime) / NS_PER_UPDATE; // Calculate delta time
        lastTime = now;

        accumulatedTime += delta;
        while (accumulatedTime >= 1) {
            player.tick(); // Update player logic
            updateLevel();
            if (listener != null) {
                listener.onTimerTick();
            }
            accumulatedTime -= 1;
        }
    }

    private boolean winGameCheck(){
        return player.getKeyScore() == Key.NUM_KEYS && player.getCenterX() > 2800;
    }

    private boolean loseGameCheck(){
        return player.getLives() <= 0;
    }

    protected abstract void updateLevel();

    public boolean isGameWon() {
        return winGameCheck();
    }

    public boolean isGameLost() {
        return loseGameCheck();
    }

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

    public Player getPlayer() {
        return player;
    }
}
