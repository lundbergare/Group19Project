package Model;

import Controller.PlayerController;
import View.*;

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
    private ArrayList<Key> keys;
    private Enemy enemy;
    private EnemyView enView;
    private KeyView keyView;

    private PlayerView playerView;
    private CoinView coinView;

    private PowerUpModel powerUpModel;
    private PowerUpView powerUpView;

    private SpeedPowerUpModel speedPowerUpModel;
    private SpeedPowerUpView speedPowerUpView;

    private ShieldPowerUpModel shieldPowerUpModel;
    private ShieldPowerUpView shieldPowerUpView;

    private LevelCamera camera;

    private ProjectView projectView;

    private long lastTime = System.nanoTime();
    private final double NS_PER_UPDATE = 1000000000.0 / 60.0; // 60 updates per second
    private double accumulatedTime = 0.0;

    private ArrayList<Platform> platforms; // Declare the ArrayList for platforms


    //TODO: Hmm, like the controls are a bit seperated now from the player, but this is really a God-class, as almost everything is done here.
    //Move all drawing and visual stuff to the GameView instead
    public TestingLevel(ProjectView projectView) {
        this.projectView = projectView;
        //initiate window background and objects
        setPreferredSize(new Dimension(XAXIS, YAXIS));
        setBackground(new Color(68, 138, 184));

        player = new Player(this);
        playerView = new PlayerView(player);
        PlayerController playerController = new PlayerController(player);

        camera = new LevelCamera(1000, 750);

        powerUpModel = new PowerUpModel(200, 420);
        powerUpView = new PowerUpView(powerUpModel);

        speedPowerUpModel = new SpeedPowerUpModel(150, 420);
        speedPowerUpView = new SpeedPowerUpView(speedPowerUpModel);

        shieldPowerUpModel = new ShieldPowerUpModel(450, 250);
        shieldPowerUpView = new ShieldPowerUpView(shieldPowerUpModel);

        addKeyListener(playerController);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        coinView = new CoinView();
        keyView= new KeyView();


        enemy=new Enemy(500, 450, 1, 850,50,50);

        enView = new EnemyView(enemy);

        //Code below here is quite ugly, but I don't know.
        platforms = new ArrayList<>();
        Platform platform1 = PlatformFactory.createPlatform(0,600,200,50);
        Platform platform2 = PlatformFactory.createPlatform(370, 500, 200, 50);
        Platform platform3 = PlatformFactory.createPlatform(400,300,200,50);
        Platform platform4 = PlatformFactory.createPlatform(670,300,200,50);

        platforms.add(platform1);
        platforms.add(platform2);
        platforms.add(platform3);
        platforms.add(platform4);

        //addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        //enemy = new Enemy(100, 100, 1, 20);

        coins = Coin.populateCoins();
        keys= Key.populateKeys();
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

        for (Key key : keys) {
            keyView.drawKey(g2d, key);
        }
        playerView.draw(g2d);

        enView.draw(g2d);

        powerUpView.draw(g2d);

        speedPowerUpView.draw(g2d);

        shieldPowerUpView.draw(g2d);

        PlatformView platformView = new PlatformView(platforms);
        for (Platform platform : platforms) {
            platformView.draw(g2d);
        }
       // Toolkit.getDefaultToolkit().sync();

        g2d.setTransform(originalTransform);

        // Create a copy of the Graphics instance
        //Graphics2D g2d = (Graphics2D) g.create();

        // Translate the graphics context to simulate camera movement
        coinView.drawScoreAndLives(g, player);
        keyView.drawScoreAndKeys(g,player);

        g2d.dispose(); // dispose the graphics copy
    }
//TODO Change all of this, should probably not be in the TestingLevel class, and code duplication.
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

    @Override
    public void actionPerformed(ActionEvent e) {

        long now = System.nanoTime();
        double delta = (now - lastTime) / NS_PER_UPDATE; // Calculate delta time
        lastTime = now;

        accumulatedTime += delta;

        ProjectModel.platformCollision(player, platforms);

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

        if (player.getLives() <= 0) {
            SwingUtilities.invokeLater(() -> {
                // Assuming you have a reference to ProjectView in TestingLevel
                projectView.showGameOverScreen();
            });
        }

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
        Key.collectKeys(player, keys);


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