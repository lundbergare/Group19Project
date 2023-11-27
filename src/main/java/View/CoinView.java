package View;

import Model.Coin;
import java.awt.*;
import Model.Player;

public class CoinView {
    // This coin type should only have one final size
    private final int WIDTH = 50;
    private final int HEIGHT = 50;

    // Draws the visual representation of the coin
    public void drawCoin(Graphics g, Coin coin){
        Point pos = coin.getPos();
        g.setColor(Color.yellow);
        g.fillOval(pos.x, pos.y, WIDTH, HEIGHT);
        g.setColor(Color.gray);
        g.drawString("5", pos.x + 17, pos.y + 33);
    }

    /*public void drawScoreAndLives(Graphics g, Player player) {
        // Draw the player's score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Collected coins: " + player.getScore() + "/" + Coin.NUM_COINS, 10, 20);

        // Draw the player's lives
        int lives = player.getLives();
        for (int i = 0; i < lives; i++) {
            g.drawImage(heartImage, 10 + (i * 30), 40, null);
        }
    }*/
}

