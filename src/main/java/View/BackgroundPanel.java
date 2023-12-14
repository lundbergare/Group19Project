package View;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BackgroundPanel extends JPanel{
    private BufferedImage image;

    protected BackgroundPanel(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath)); // Load the background image
        } catch (IOException ex) {
            ex.printStackTrace();
            // Handle exception (e.g., file not found)
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this); // Draw the image to fit the panel size
        }
    }
}
