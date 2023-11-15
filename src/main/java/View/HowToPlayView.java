package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HowToPlayView extends JPanel {

    private JButton backButton;

    public HowToPlayView(ActionListener backButtonListener) {
        setLayout(new BorderLayout());

        JLabel instructionsLabel = new JLabel("<html><div style='text-align: center; font-size: 20px;'>" +
                "The goal is to defeat an amount of enemies, which gives you a key that enables you to finish the level!<br>" +
                "Use A, D, and W to steer your character.</div></html>", SwingConstants.CENTER);
        add(instructionsLabel, BorderLayout.CENTER);

        // Back button at the bottom left
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("<- Back");
        backButton.addActionListener(backButtonListener);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
