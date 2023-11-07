package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectView {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JPanel currentScreen; // The current screen

    public ProjectView() {
        frame = new JFrame("Super Mario Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        mainPanel = new JPanel(new GridBagLayout());
        frame.add(mainPanel);

        button1 = new JButton("Button 1");
        button2 = new JButton("Button 2");
        button3 = new JButton("Button 3");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;

        mainPanel.add(button1, constraints);
        constraints.gridy = 1;
        mainPanel.add(button2, constraints);
        constraints.gridy = 2;
        mainPanel.add(button3, constraints);

        // Does currently not adhere to MVC. Must move over this method into the controller. 
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNewScreen();
            }
        });

        // Initialize the currentScreen with the main content
        currentScreen = mainPanel;

        frame.setVisible(true);
    }

    // Change the screen
    private void showNewScreen() {
        NewScreenPanel newScreen = new NewScreenPanel();
        frame.getContentPane().remove(currentScreen);
        frame.getContentPane().add(newScreen);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
        currentScreen = newScreen;
    }
}
