package Model;

import Controller.PlayerController;
import View.PlayerView;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class Level extends JPanel implements ActionListener {

    protected static final int YAXIS = 800;
    protected static final int XAXIS = 600;

    protected Timer timer;
    public Player player;
    protected PlayerView playerView;
    protected PlayerController playerController;

    protected Image heartImage;

    public Level() {
        setPreferredSize(new Dimension(YAXIS, XAXIS));
        setBackground(new Color(68, 138, 184));

        player = new Player();
        playerView = new PlayerView(player);
        playerController = new PlayerController(player);

        addKeyListener(playerController);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        ImageIcon icon = new ImageIcon("src/main/java/Model/images/GameHeart.png");
        heartImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        timer = new Timer(1, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.tick();
        updateLevel();
        repaint();
    }

    protected abstract void updateLevel();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLevel(g);
        Toolkit.getDefaultToolkit().sync();
    }

    protected abstract void drawLevel(Graphics g);
}
