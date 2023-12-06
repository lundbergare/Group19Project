package Model;

import Controller.PlayerController;
import View.PlayerView;
import View.PowerUpView;
import View.ShieldPowerUpView;
import View.SpeedPowerUpView;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public abstract class Level extends JPanel implements ActionListener, IBoundary {

    protected static final int YAXIS = 800;
    protected static final int XAXIS = 1600;

    protected Timer timer;
    public Player player;
    protected PlayerController playerController;
    protected Image heartImage;
    private LevelListener listener;
    protected ArrayList<Platform> platforms; // Declare the ArrayList for platforms
    protected ArrayList<Coin> coins;

    protected PowerUpModel powerUpModel;
    protected SpeedPowerUpModel speedPowerUpModel;
    protected ShieldPowerUpModel shieldPowerUpModel;

    protected ArrayList<Key> keys;

    private long lastTime = System.nanoTime();
    private final double NS_PER_UPDATE = 1000000000.0 / 60.0; // 60 updates per second
    private double accumulatedTime = 0.0;




    public Level() {
        setPreferredSize(new Dimension(YAXIS, XAXIS));
        setBackground(new Color(68, 138, 184));

        platforms = new ArrayList<Platform>();
        keys = new ArrayList<Key>();

        player = new Player(this);
        playerController = new PlayerController(player);

        addKeyListener(playerController);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        ImageIcon icon = new ImageIcon("src/main/java/Model/images/GameHeart.png");
        heartImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

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

        if (checkCollision(player, powerUpModel)) {
            powerUpModel.activate();
            player.applyPowerUp(powerUpModel);
        }

        if (checkCollision2(player, speedPowerUpModel)) {
            speedPowerUpModel.activate();
            player.applySpeedPowerUp(speedPowerUpModel);
        }

        if (checkCollision3(player, shieldPowerUpModel)) {
            shieldPowerUpModel.activate();
            player.applyShieldPowerUp(shieldPowerUpModel);
        }
    }

    protected abstract void updateLevel();

    public void setLevelListener(LevelListener listener) {
        this.listener = listener;
    }


    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public ArrayList<Key> getKeys() {
        return keys;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public boolean checkCollision(Player player, PowerUpModel powerUp) {
        if (!powerUp.isActive()) {
            return false; // No collision if the power-up is not active
        }

        Rectangle playerRect = new Rectangle(player.getPos().x, player.getPos().y, player.getWidth(), player.getHeight());
        Point powerUpPos = powerUp.getPosition();
        Rectangle powerUpRect = new Rectangle(powerUpPos.x, powerUpPos.y, 30, 30);

        return playerRect.intersects(powerUpRect);
    }

    public boolean checkCollision2(Player player, SpeedPowerUpModel powerUp) {
        if (!powerUp.isActive()) {
            return false; // No collision if the power-up is not active
        }

        Rectangle playerRect = new Rectangle(player.getPos().x, player.getPos().y, player.getWidth(), player.getHeight());
        Point powerUpPos = powerUp.getPosition();
        Rectangle powerUpRect = new Rectangle(powerUpPos.x, powerUpPos.y, 30, 30);

        return playerRect.intersects(powerUpRect);
    }

    public boolean checkCollision3(Player player, ShieldPowerUpModel powerUp) {
        if (!powerUp.isActive()) {
            return false; // No collision if the power-up is not active
        }

        Rectangle playerRect = new Rectangle(player.getPos().x, player.getPos().y, player.getWidth(), player.getHeight());
        Point powerUpPos = powerUp.getPosition();
        Rectangle powerUpRect = new Rectangle(powerUpPos.x, powerUpPos.y, 30, 30);

        return playerRect.intersects(powerUpRect);
    }
}
