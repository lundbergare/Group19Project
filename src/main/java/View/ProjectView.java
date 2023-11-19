package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;

public class ProjectView {
    private final JFrame frame;
    private final JPanel mainPanel;
    private final JButton levelSelectButton;
    private final JButton howToPlayButton;
    private final JButton quitButton;
    private JPanel currentScreen; // The current screen
    private final JLabel titleLabel;

    public ProjectView() {



        frame = new JFrame("Super Smurf Game");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int height = 750;
        int width = 1000;
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false); // Set frame to be non-resizable

        mainPanel = new BackgroundPanel("src/main/java/View/TheSmurfs.png");
        mainPanel.setLayout(new GridBagLayout());
        //mainPanel = new JPanel(new GridBagLayout());
        frame.add(mainPanel);


        titleLabel = new JLabel("Super Smurf Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60)); // Set the font, style, and size
        titleLabel.setForeground(Color.BLACK); // Set the text color

        // Add the title label to your panel
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0; // Adjust grid position as needed
        titleConstraints.insets = new Insets(0, 10, 200, 10); // Optional: Adjust spacing
        titleConstraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, titleConstraints);

        levelSelectButton = new JButton("Start Game");
        howToPlayButton = new JButton("How to Play");
        quitButton = new JButton("Quit");

        // Set the preferred size for each button
        levelSelectButton.setPreferredSize(new Dimension(150, 50));
        howToPlayButton.setPreferredSize(new Dimension(150, 50));
        quitButton.setPreferredSize(new Dimension(150, 50));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(20, 10, 20, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;

        mainPanel.add(levelSelectButton, constraints);
        constraints.gridy = 1;
        mainPanel.add(howToPlayButton, constraints);
        constraints.gridy = 2;
        mainPanel.add(quitButton, constraints);

        // Initialize the currentScreen with the main content
        currentScreen = mainPanel;

        frame.setVisible(true);


    }

    public void addLevelButtonListener(ActionListener listener) {
        levelSelectButton.addActionListener(listener);
    }
    public void addHowToPlayButtonListener(ActionListener listener){
        howToPlayButton.addActionListener(listener);
    }
    public void addQuitButtonListener(ActionListener listener) {
        quitButton.addActionListener(listener);
    }
    // Change the screen
    public void showNewScreen(JPanel newScreen) {
        frame.getContentPane().remove(currentScreen);
        frame.getContentPane().add(newScreen);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
        currentScreen = newScreen;
    }


    // Needed for in game keyboard inputs

    // Change the screen back to the main panel
    //Should this be in model?! I don't think so, but quite a lot of job for the view?
    public void showPreviousScreen() {
        NewScreenPanel newScreen = new NewScreenPanel();
        frame.getContentPane().remove(currentScreen);
        frame.getContentPane().add(mainPanel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
        currentScreen = mainPanel;
    }

}
