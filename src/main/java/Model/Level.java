package Model;

import Controller.PlayerController;
import View.PlayerView;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public abstract class Level extends JPanel implements ActionListener {

    protected static final int YAXIS = 800;
    protected static final int XAXIS = 600;

    protected Timer timer;
    public Player player;
    protected PlayerView playerView;
    protected PlayerController playerController;
    protected Image heartImage;
    private LevelListener listener;
    protected ArrayList<Platform> platforms; // Declare the ArrayList for platforms



    public Level() {
        setPreferredSize(new Dimension(YAXIS, XAXIS));
        setBackground(new Color(68, 138, 184));

        platforms = new ArrayList<Platform>();


        player = new Player();
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
        player.tick();
        updateLevel();
        if (listener != null) {
            listener.onTimerTick();
        }    }

    protected abstract void updateLevel();

    public void setLevelListener(LevelListener listener) {
        this.listener = listener;
    }


    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }


}

