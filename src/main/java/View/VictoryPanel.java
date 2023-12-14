package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class VictoryPanel extends JPanel {
    private BufferedImage backgroundImage;
    protected VictoryPanel() {
        try {
            backgroundImage = ImageIO.read(new File("src/main/java/View/ImagesForView/victoryScreen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(300, 200));
        setLayout(new BorderLayout());
        setOpaque(false);

        JLabel gameOverLabel = new JLabel("Victory", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 180));
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
