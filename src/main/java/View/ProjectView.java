package View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;

public class ProjectView {
    private final JFrame frame;
    private final JPanel mainPanel;
    private final JButton levelSelectButton;
    private final JButton howToPlayButton;
    private final JButton quitButton;
    private JPanel currentScreen;

    private final Timer returnToMenuTimer; // Add a Timer object
    private boolean gameOverOrVictory; // Track if game over or victory screen is displayed


    public ProjectView() {
        frame = new JFrame("Super Smurf Game");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int height = 750;
        int width = 1000;
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        mainPanel = new BackgroundPanel("src/main/java/View/ImagesForView/TheSmurfs.png");
        frame.add(mainPanel);
        mainPanel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Super Smurf Bros");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        titleLabel.setForeground(Color.BLACK); // Set the text color

        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.insets = new Insets(0, 10, 50, 10);
        titleConstraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, titleConstraints);

        levelSelectButton = new JButton("Start Game");
        howToPlayButton = new JButton("How to Play");
        quitButton = new JButton("Quit");

        levelSelectButton.setPreferredSize(new Dimension(150, 50));
        howToPlayButton.setPreferredSize(new Dimension(150, 50));
        quitButton.setPreferredSize(new Dimension(150, 50));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(25, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;

        mainPanel.add(levelSelectButton, constraints);
        constraints.gridy = 2;
        mainPanel.add(howToPlayButton, constraints);
        constraints.gridy = 3;
        mainPanel.add(quitButton, constraints);

        currentScreen = mainPanel;
        frame.setVisible(true);
        returnToMenuTimer = new Timer(5000, e -> {
            if (gameOverOrVictory) {
                showPreviousScreen();
                gameOverOrVictory = false;
            }
        });

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

    public void showNewScreen(JPanel newScreen) {
        frame.getContentPane().remove(currentScreen);
        frame.getContentPane().add(newScreen);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
        currentScreen = newScreen;
    }

    public void showPreviousScreen() {
        frame.getContentPane().remove(currentScreen);
        frame.getContentPane().add(mainPanel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
        currentScreen = mainPanel;
    }

    protected void showGameOverScreen() {
        GameOverPanel gameOverPanel = new GameOverPanel();
        showNewScreen(gameOverPanel);
        returnToMenuTimer.start();
        gameOverOrVictory = true;
    }

    protected void showVictoryScreen() {
        VictoryPanel victoryPanel = new VictoryPanel();
        showNewScreen(victoryPanel);
        returnToMenuTimer.start();
        gameOverOrVictory = true;
    }
}
