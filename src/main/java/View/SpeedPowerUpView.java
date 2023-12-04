package View;

import Model.PowerUpModel;
import Model.SpeedPowerUpModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpeedPowerUpView {
    private BufferedImage mushroomImage;
    private int scaledWidth = 30;
    private int scaledHeight = 30;

    public SpeedPowerUpView() {
        try {
            mushroomImage = ImageIO.read(new File("src/main/java/View/ImagesForView/mushroom.png"));
        } catch (IOException e) {
            e.printStackTrace();
            // handle the error
        }
    }

    public void draw(Graphics g, SpeedPowerUpModel powerUp) {
        if (mushroomImage != null && powerUp.isActive()) {
            Point pos = powerUp.getPosition();
            g.drawImage(mushroomImage, pos.x, pos.y, scaledWidth, scaledHeight, null);
        }
    }
}

