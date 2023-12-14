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

    private BufferedImage enemyImage;

    private final Enemy enemy;

    protected EnemyView(Enemy enemy) {
        this.enemy = enemy;
        try {
            enemyImage = ImageIO.read(new File("src/main/java/View/ImagesForView/enemyright.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void draw(Graphics g) {
        if (enemyImage != null) {
            int x = enemy.getRectangleX();
            int y = enemy.getRectangleY();

            // Width for image
            int scaledWidth = 50;
            // Height for image
            int scaledHeight = 50;
            if (enemy.getDirection() == -1) {
                g.drawImage(enemyImage, x + scaledWidth, y, -scaledWidth, scaledHeight, null);
            } else {
                // Moving right
                g.drawImage(enemyImage, x, y, scaledWidth, scaledHeight, null);
            }
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(enemy.getRectangleX(), enemy.getRectangleY(), enemy.getWidth(), enemy.getHeight());
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Enemy enemy) {
            try {
                // Load your enemy image file
                enemyImage = ImageIO.read(new File("src/main/java/View/ImagesForView/enemyright.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Update the movingLeft flag based on the direction
            // Flag to track whether the enemy is moving left
            boolean movingLeft = (enemy.getDirection() == -1);
        }
    }
}
