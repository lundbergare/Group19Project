package View;

import Model.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerView {

    private BufferedImage playerImage; // Image for the player

    private BufferedImage standingStillImage; // Image for standing still

    private final Player player;
    private int scaledWidth = 50; // Width for image
    private int scaledHeight = 50; // Height for image

    public PlayerView(Player player) {
        this.player = player;
        try {
            // Load your image file
            playerImage = ImageIO.read(new File("src/main/java/View/ImagesForView/smurfForGame.png"));
            standingStillImage = ImageIO.read(new File("src/main/java/View/ImagesForView/standingStill.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        if (playerImage != null && standingStillImage != null) {
            int x = player.getPos().x;
            int y = player.getPos().y;

            if (player.isFacingRight()) {
                if (player.isStandingStill()) {
                    // Standing still
                    g.drawImage(standingStillImage, x, y, scaledWidth, scaledHeight, null);
                } else {
                    // Moving right
                    g.drawImage(playerImage, x, y, scaledWidth, scaledHeight, null);
                }
            } else {
                if (player.isStandingStill()) {
                    // Draw the standing still flipped image when facing left and not moving
                    g.drawImage(standingStillImage, x + scaledWidth, y, -scaledWidth, scaledHeight, null);
                } else {
                    // Moving left(flipped image)
                    g.drawImage(playerImage, x + scaledWidth, y, -scaledWidth, scaledHeight, null);
                }
            }
        } else {
            // Draw boring black box if images cant load :(
            g.setColor(Color.BLACK);
            g.fillRect(player.getPos().x, player.getPos().y, player.getWidth(), player.getHeight());
        }
    }
}
