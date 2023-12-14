package View;

import Model.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerView {
    private BufferedImage playerImage;
    private BufferedImage standingStillImage;
    private final Player player;

    protected PlayerView(Player player) {
        this.player = player;
        try {
            playerImage = ImageIO.read(new File("src/main/java/View/ImagesForView/smurfForGame.png"));
            standingStillImage = ImageIO.read(new File("src/main/java/View/ImagesForView/standingStill.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void draw(Graphics g) {
        if (playerImage != null && standingStillImage != null) {
            int x = player.getPos().x;
            int y = player.getPos().y;
            int width = player.getWidth();
            int height = player.getHeight();

            if (player.isFacingRight()) {
                if (player.isStandingStill()) {
                    g.drawImage(standingStillImage, x, y, width, height, null);
                } else {
                    g.drawImage(playerImage, x, y, width, height, null);
                }
            } else {
                if (player.isStandingStill()) {
                    g.drawImage(standingStillImage, x + width, y, -width, height, null);
                } else {
                    g.drawImage(playerImage, x + width, y, -width, height, null);
                }
            }
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(player.getPos().x, player.getPos().y, player.getWidth(), player.getHeight());
        }
    }
}