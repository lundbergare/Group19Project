package View;

import Model.PowerUpModel;
import Model.ShieldPowerUpModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShieldPowerUpView {
    private BufferedImage shieldImage;
    private int scaledWidth = 30;
    private int scaledHeight = 30;

    public ShieldPowerUpView() {
        try {
            shieldImage = ImageIO.read(new File("src/main/java/View/ImagesForView/shield2.png"));
        } catch (IOException e) {
            e.printStackTrace();
            // handle the error
        }
    }

    public void draw(Graphics g, ShieldPowerUpModel powerUp) {
        if (shieldImage != null && powerUp.isActive()) {
            Point pos = powerUp.getPosition();
            g.drawImage(shieldImage, pos.x, pos.y, scaledWidth, scaledHeight, null);
        }
    }
}

