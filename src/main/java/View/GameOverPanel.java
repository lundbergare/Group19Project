package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameOverPanel extends JPanel {
    private JButton returnToMenuButton;
    private BufferedImage backgroundImage;

    public GameOverPanel() {
        try {
            backgroundImage = ImageIO.read(new File("src/main/java/View/ImagesForView/sad3.png"));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception or provide a fallback image
        }

        setPreferredSize(new Dimension(300, 200));
        setLayout(new BorderLayout());
        setOpaque(false);

        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 80));
        gameOverLabel.setForeground(Color.WHITE);
        add(gameOverLabel, BorderLayout.CENTER);
//
//        returnToMenuButton = new JButton("Return to Main Menu");
//        returnToMenuButton.setPreferredSize(new Dimension(200, 30)); // Set the preferred size of the button
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setOpaque(false); // Make the panel holding the button non-opaque
//        buttonPanel.add(returnToMenuButton);
//        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public void addReturnToMenuButtonListener(ActionListener listener) {
        for (Component comp : getComponents()) {
            if (comp instanceof JButton) {
                ((JButton) comp).addActionListener(listener);
            }
        }
    }
}

