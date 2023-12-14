package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameOverPanel extends JPanel {
    private BufferedImage backgroundImage;

    public GameOverPanel() {
        try {
            backgroundImage = ImageIO.read(new File("src/main/java/View/ImagesForView/sad3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(300, 200));
        setLayout(new BorderLayout());
        setOpaque(false);

        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 80));
        gameOverLabel.setForeground(Color.WHITE);
        add(gameOverLabel, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

}

