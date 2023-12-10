package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class LevelView extends JPanel implements LevelListener{
    protected static final int YAXIS = 1000;
    protected static final int XAXIS = 3000;
    protected Image heartImage;
    private final Image keyImage;

    protected CoinView coinView;
    protected PlayerView playerView;

    public LevelCamera camera;

    protected ArrayList<Coin> coins;
    protected ArrayList<Platform> platforms;
    protected ArrayList<Key> keys;


    protected Graphics g;
    protected Graphics2D g2d;

    protected String score;
    protected int lives;

    public LevelView() {
        setPreferredSize(new Dimension(XAXIS, YAXIS));
        setBackground(new Color(68, 138, 184));
        ImageIcon icon = new ImageIcon("src/main/java/Model/images/GameHeart.png");
        heartImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        ImageIcon iconKey = new ImageIcon("src/main/java/Model/images/key.png");
        keyImage = iconKey.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        coinView = new CoinView();
        g2d = (Graphics2D) g;
        camera = new LevelCamera(1000, 750);

    }



    protected void paintUserInterface(Graphics g) {
        g.setColor(Color.BLACK); // color for the score text
        g.setFont(new Font("Arial", Font.BOLD, 20)); // font for score
        g.drawString("Collected coins: " + score + "/" + Coin.NUM_COINS, 10, 20); // position of score on screen

        // Draw the player's lives
        for (int i = 0; i < lives; i++) {
            g.drawImage(heartImage, 10 + (i * 30), 40, this); // Adjust position and spacing as needed
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Collected keys: " + keys + "/" + Key.NUM_KEYS, 300, 20);

        // Draw the player's remaining keys
        for (int i = 0; i < keys.size(); i++) {
            g.drawImage(keyImage, 10 + (i * 30), 60, this);
        }
    }

    protected void drawCoins(Graphics g) {
        for (Coin coin : coins) {
            coinView.drawCoin(g, coin); // Draw each coin
        }
    }

    public void drawKey(Graphics g, Key key) {
        Point pos = key.getPos();
        g.setColor(Color.gray);
        g.drawImage(keyImage, pos.x, pos.y, 25, 25, null);
        g.setColor(Color.gray);
    }

    protected void drawKeys(Graphics g) {
        for (Key key : keys) {
            drawKey(g, key);
        }
    }

        @Override
        protected void paintComponent (Graphics g){
            super.paintComponent(g);
            paintUserInterface(g);
            drawLevel(g);
        }

        protected abstract void drawLevel (Graphics g);

        public void setScore (String score){
            this.score = score;
        }

        public void setLives ( int lives){
            this.lives = lives;
        }

    }
