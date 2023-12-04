package View;

import Model.Key;
import Model.Player;

import java.awt.*;
import javax.swing.ImageIcon;

public class KeyView {
    // This key type should only have one final size
    private final int WIDTH = 25;
    private final int HEIGHT = 25;

    private Image keyImage;

    public KeyView() {
        // Load and scale the key image
        ImageIcon icon = new ImageIcon("src/main/java/Model/images/key.png");
        keyImage = icon.getImage();
        int scaledWidth = 25; // width
        int scaledHeight = 25; // height
        keyImage = keyImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
    }

    public void drawKey(Graphics g, Key key) {
        Point pos = key.getPos();
        g.drawImage(keyImage, pos.x, pos.y, WIDTH, HEIGHT, null);
        g.setColor(Color.gray);
    }


    public void drawScoreAndKeys(Graphics g, Player player) {
        // Draw the player's score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Collected keys: " + player.getKeys() + "/" + Key.NUM_KEYS, 300, 20);

        // Draw the player's remaining keys
        int keys = player.getKeys();
        for (int i = 0; i < keys; i++) {
            g.drawImage(keyImage, 10 + (i * 30), 40, null);
        }
    }
}
