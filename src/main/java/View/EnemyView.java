package View;

import Model.Enemy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class EnemyView implements Observer {

    private BufferedImage enemyImage; // Image for the enemy

    private final Enemy enemy;
    private int scaledWidth = 50; // Width for image
    private int scaledHeight = 50; // Height for image
    private boolean movingLeft = false; // Flag to track whether the enemy is moving left

    public EnemyView(Enemy enemy) {
        this.enemy = enemy;
        try {
            // Load your enemy image file
            enemyImage = ImageIO.read(new File("src/main/java/View/ImagesForView/enemyright.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public void draw(Graphics g) {
    if (enemyImage != null) {
        int x = enemy.getRectangleX();
        int y = enemy.getRectangleY();

        if (enemy.getDirection() == -1) {
            // Draw the flipped image when moving left
            g.drawImage(enemyImage, x + scaledWidth, y, -scaledWidth, scaledHeight, null);
        } else {
            // Moving right
            g.drawImage(enemyImage, x, y, scaledWidth, scaledHeight, null);
        }
    } else {
        // Draw boring black box if image can't load :(
        g.setColor(Color.BLACK);
        g.fillRect(enemy.getRectangleX(), enemy.getRectangleY(), enemy.getWidth(), enemy.getHeight());
    }
}


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Enemy) {
            Enemy enemy = (Enemy) o;
            try {
                // Load your enemy image file
                enemyImage = ImageIO.read(new File("src/main/java/View/ImagesForView/enemyright.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Update the movingLeft flag based on the direction
            movingLeft = (enemy.getDirection() == -1);
        }
    }
}
