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
public class TestingLevel extends JPanel implements ActionListener, IBoundary {

    // controls the delay between each tick in ms
    private final int DELAY = 1;
    // controls the size of the board (wrong module?)
    
    private static final int YAXIS = 1000;
    private static final int XAXIS = 3000;
    // suppress serialization warning, not really sure what it's supposed to do so commented out

    // keep a reference to the timer object that triggers actionPerformed() in
    // case we need access to it in another method
    private Timer timer;
    // objects that appear on the game board
    private Player player;
    private ArrayList<Coin> coins;
    private Enemy enemy;
    private EnemyView enView;

    private PlayerView playerView;
    private CoinView coinView;

    private LevelCamera camera;

    private long lastTime = System.nanoTime();
    private final double NS_PER_UPDATE = 1000000000.0 / 60.0; // 60 updates per second
    private double accumulatedTime = 0.0;

    private ArrayList<Platform> platforms; // Declare the ArrayList for platforms


    //TODO: Hmm, like the controls are a bit seperated now from the player, but this is really a God-class, as almost everything is done here.
    //Move all drawing and visual stuff to the GameView instead
    public TestingLevel() {
        //initiate window background and objects
        setPreferredSize(new Dimension(XAXIS, YAXIS));
        setBackground(new Color(68, 138, 184));

        player = new Player(this);
        playerView = new PlayerView(player);
        PlayerController playerController = new PlayerController(player);

        camera = new LevelCamera(1000, 750);

        addKeyListener(playerController);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        coinView = new CoinView();


        enemy=new Enemy(500, 450, 1, 850,50,50);

        enView = new EnemyView(enemy);

        platforms = new ArrayList<>();

        Platform platform1 = new Platform(0, 500, 300, 50);
        Platform platform2 = new Platform(370, 500, 200, 50);
        Platform platform3 = new Platform(400, 300, 200, 50);
        Platform platform4 = new Platform(670, 300, 200, 50);

        platforms.add(platform1);
        platforms.add(platform2);
        platforms.add(platform3);
        platforms.add(platform4);

        //addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        //enemy = new Enemy(100, 100, 1, 20);

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

        for (Platform platform : platforms) {
            PlatformView platformView = new PlatformView(platforms);
            platformView.draw(g2d);
        }
       // Toolkit.getDefaultToolkit().sync();

        g2d.setTransform(originalTransform);

        // Create a copy of the Graphics instance
        //Graphics2D g2d = (Graphics2D) g.create();

        // Translate the graphics context to simulate camera movement
        coinView.drawScoreAndLives(g, player);

        g2d.dispose(); // dispose the graphics copy
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        long now = System.nanoTime();
        double delta = (now - lastTime) / NS_PER_UPDATE; // Calculate delta time
        lastTime = now;

        accumulatedTime += delta;

        ProjectModel.platformCollision(player, platforms);

        while (accumulatedTime >= 1) {
            player.tick(); // Update player logic
            enemy.move();
            enemy.kill(player, enemy);
            player.kill(player, enemy);
            // Other game logic updates here...
            accumulatedTime -= 1;
        }
        // Update the camera position
        //camera.update(player.getPos(), 2000, 750); //
        camera.update(player.getPos(), 3000, 1000);

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

    @Override
    public int getXAxisLimit() {
        return XAXIS;
    }

    @Override
    public int getYAxisLimit() {
        return YAXIS;
    }

    //Populate the level with the coins and add to list of coins in level, returns list.
    // Currently only adapted for testing level


}