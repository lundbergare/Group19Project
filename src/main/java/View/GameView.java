package View;

import Model.*;

import javax.swing.*;
import java.awt.*;

//TODO: Sort of implemented, but must be connected to everything. Right now TestingLevel has too much stuff in it, like drawing etc.


public class GameView extends JPanel {
    private PlayerView playerView;
    private EnemyView enemyView;
    // Other views for coins, platforms, etc.

    public GameView(Player player, Enemy enemy) {
        playerView = new PlayerView(player);
        enemyView = new EnemyView(enemy);
        // Initialize other views
        setBackground(new Color(68, 138, 184)); // Set background color
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw game objects using respective views
        playerView.draw(g);
        enemyView.draw(g);
        // Draw other game objects
    }
}
