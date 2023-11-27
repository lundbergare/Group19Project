package Model;

import Controller.PlayerController;
import View.CoinView;
import View.EnemyView;
import View.PlatformView;
import View.PlayerView;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.*;

//TODO Fix platform extension
public class TestingLevel extends JPanel implements ActionListener {

    // controls the delay between each tick in ms
    private final int DELAY = 1;
    // controls the size of the board (wrong module?)
    
    public static final int YAXIS = 1000;
    public static final int XAXIS = 3000;
    // suppress serialization warning, not really sure what it's supposed to do so commented out

    // keep a reference to the timer object that triggers actionPerformed() in
    // case we need access to it in another method
    private Timer timer;
    // objects that appear on the game board
    private Player player;
    private ArrayList<Coin> coins;
    private Platform platform;
    private PlatformView platformView;
    private Enemy enemy;
    private EnemyView enView;
    private PlayerView playerView; // Declare it as a class-level field
    private CoinView coinView; // Add this field
    private Image heartImage;

    private LevelCamera camera;

    private long lastTime = System.nanoTime();
    private final double NS_PER_UPDATE = 1000000000.0 / 60.0; // 60 updates per second
    private double accumulatedTime = 0.0;

    //TODO: Hmm, like the controls are a bit seperated now from the player, but this is really a God-class, as almost everything is done here.
    //Move all drawing and visual stuff to the GameView instead
    public TestingLevel() {
        //initiate window background and objects
        setPreferredSize(new Dimension(XAXIS, YAXIS));
        setBackground(new Color(68, 138, 184));

        player = new Player();
        playerView = new PlayerView(player);
        PlayerController playerController = new PlayerController(player);

        //camera = new LevelCamera(XAXIS, YAXIS);
        camera = new LevelCamera(1000, 750);

        addKeyListener(playerController);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        coinView = new CoinView();

        enemy=new Enemy(500, 450, 1, 850);
        enView = new EnemyView(enemy);
        platform = new Platform(0, 500, 1600, 50);

        platformView = new PlatformView(platform);

        //addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        //enemy = new Enemy(100, 100, 1, 20);
        ImageIcon icon = new ImageIcon("src/main/java/Model/images/GameHeart.png");
        heartImage = icon.getImage();
        int scaledWidth = 25; // width
        int scaledHeight = 25; // height
        heartImage = heartImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

        coins = Coin.populateCoins();
        // this timer will call the actionPerformed() method every DELAY ms
        timer = new Timer(DELAY, this);
        timer.start();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Save the current transform
        AffineTransform originalTransform = g2d.getTransform();
        // draw our graphics.
        //drawBackground(g);
        g2d.translate(-camera.getX(), -camera.getY());
        textAA(g);
        for (Coin coin : coins) {
            coinView.drawCoin(g2d, coin); // Draw each coin
        }
        playerView.draw(g2d);
        enView.draw(g2d);
        platformView.draw(g2d);
        //enemy.drawEnemy(g);
        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();

        g2d.setTransform(originalTransform);

        // Create a copy of the Graphics instance
        //Graphics2D g2d = (Graphics2D) g.create();

        // Translate the graphics context to simulate camera movement

        // Draw the player's score
        g.setColor(Color.BLACK); // color for the score text
        g.setFont(new Font("Arial", Font.BOLD, 20)); // font for score
        g.drawString("Collected coins: " + player.getScore() + "/" + Coin.NUM_COINS, 10, 20); // position of score on screen

        // Draw the player's lives
        int lives = player.getLives();
        for (int i = 0; i < lives; i++) {
            g.drawImage(heartImage, 10 + (i * 30), 40, this); // Adjust position and spacing as needed
        }
        g2d.dispose(); // dispose the graphics copy
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        long now = System.nanoTime();
        double delta = (now - lastTime) / NS_PER_UPDATE; // Calculate delta time
        lastTime = now;

        accumulatedTime += delta;
        // this method is called by the timer every DELAY ms.
        // use this space to update the state of the game or animation
        // before the graphics are redrawn.
        // allow for player control and prevent the player from disappearing off the board
        //enemy.moveRectangle(); //TODO enemy does not move
        //Activates collisions when necessary
        while (accumulatedTime >= 1) {
            player.tick(); // Update player logic
            enemy.move();
            // Other game logic updates here...
            accumulatedTime -= 1;
        }

        // Update the camera position
        //camera.update(player.getPos(), 2000, 750); //
        camera.update(player.getPos(), 3000, 1000);
        //TODO: Make so that ALL platforms are collisionable, currently we now have to specify that a platform is collisionable :/

        ProjectModel.platformCollision(player, platform);
        Coin.collectCoins(player, coins);

        // calling repaint() will trigger paintComponent() to run again,
        // which will refresh/redraw the graphics.
        repaint();
    }

    // these are not used but must be defined as part of the KeyListener interface

    //Supposed to make text and certain edges look smoother, does not always work. Also adds font to Coin "5"
    private void textAA(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        // set the text color and font
        g2d.setColor(new Color(30, 201, 139));
        g2d.setFont(new Font("Lato", Font.BOLD, 25));
    }

    //Populate the level with the coins and add to list of coins in level, returns list.
    // Currently only adapted for testing level


}