package View;

import Model.PowerUpModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PowerUpView {
    private BufferedImage raspberryImage;
    private int scaledWidth = 30;
    private int scaledHeight = 30;

    public PowerUpView() {
        try {
            raspberryImage = ImageIO.read(new File("src/main/java/View/ImagesForView/raspberry.png"));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error appropriately
        }
    }

    public void draw(Graphics g, PowerUpModel powerUp) {
        if (raspberryImage != null && powerUp.isActive()) {
            Point pos = powerUp.getPosition();
            g.drawImage(raspberryImage, pos.x, pos.y, scaledWidth, scaledHeight, null);
        }
    }
}
