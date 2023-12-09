package View;

//import Model.PowerUpModel;
import Model.SpeedPowerUpModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpeedPowerUpView {
    private BufferedImage mushroomImage;
    private SpeedPowerUpModel speedPowerUpModel;
    private int scaledWidth = 30;
    private int scaledHeight = 30;

    public SpeedPowerUpView(SpeedPowerUpModel speedPowerUpModel) {
        this.speedPowerUpModel = speedPowerUpModel;
        try {
            mushroomImage = ImageIO.read(new File("src/main/java/View/ImagesForView/raspberry.png"));
        } catch (IOException e) {
            e.printStackTrace();
            // handle the error
        }
    }

    public void draw(Graphics g) {
        if (mushroomImage != null && speedPowerUpModel.isActive()) {
            Point pos = speedPowerUpModel.getPosition();
            g.drawImage(mushroomImage, pos.x, pos.y, scaledWidth, scaledHeight, null);
        }
    }
}

