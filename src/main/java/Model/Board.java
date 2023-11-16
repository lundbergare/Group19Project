package Model;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;



public class Board extends JPanel implements ActionListener, KeyListener {

    // controls the delay between each tick in ms
    private final int DELAY = 1;
    // controls the size of the board
    public static final int TILE_SIZE = 50;
    public static final int YAXIS = 800;
    public static final int XAXIS = 600;
    // controls how many coins appear on the board
    public static final int NUM_COINS = 5;
    // suppress serialization warning
    private static final long serialVersionUID = 490905409104883233L;

    // keep a reference to the timer object that triggers actionPerformed() in
    // case we need access to it in another method
    private Timer timer;
    // objects that appear on the game board
    private Player player;
    private ArrayList<Coin> coins;

    private Platform platform;
    private Enemy enemy;

    public Board() {
        setPreferredSize(new Dimension(YAXIS, XAXIS));
        setBackground(new Color(68, 138, 184));

        player = new Player();
        player.configureKeyBindings(this);
        coins = populateCoins();
        platform = new Platform(5, 500);
        enemy = new Enemy(100, 100, 1, 20);


        // this timer will call the actionPerformed() method every DELAY ms
        timer = new Timer(DELAY, this);
        timer.start();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw our graphics.
        //drawBackground(g);
        textAA(g);
        for (Coin coin : coins) {
            coin.drawCoin(g);
        }
        player.drawPlayer(g);
        platform.drawPlatform(g);
        enemy.drawEnemy(g);
        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // this method is called by the timer every DELAY ms.
        // use this space to update the state of your game or animation
        // before the graphics are redrawn.

        // prevent the player from disappearing off the board

        player.tick();
        enemy.moveRectangle();
        ProjectModel.groundCollision(player, platform);

        // give the player points for collecting coins
        collectCoins();
        // calling repaint() will trigger paintComponent() to run again,
        // which will refresh/redraw the graphics.
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // this is not used but must be defined as part of the KeyListener interface
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // react to key down events

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // react to key up events
    }


    private void textAA(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(
                RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        // set the text color and font
        g2d.setColor(new Color(30, 201, 139));
        g2d.setFont(new Font("Lato", Font.BOLD, 25));
    }

    private ArrayList populateCoins() {
        ArrayList coinList = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < NUM_COINS; i++) {
            int coinX = (i + 1) * 60;
            int coinY = 450;
            coinList.add(new Coin(coinX, coinY));
        }

        return coinList;
    }

    private void collectCoins() {
        // allow player to pickup coins
        ArrayList collectedCoins = new ArrayList<>();
        // if the player is on the same tile as a coin, collect it
        for (Coin coin: coins) {
            if (player.getPos().equals(coin.getPos())) {
                // give the player some points for picking this up
                player.addScore(5);
                collectedCoins.add(coin);
            }
        }
        // remove collected coins from the board
        coins.removeAll(collectedCoins);
    }

}