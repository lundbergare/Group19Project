package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HowToPlayView extends JPanel {

    public HowToPlayView(ActionListener backButtonListener) {
        setLayout(new BorderLayout());

        JLabel instructionsLabel = new JLabel("<html><div style='text-align: center; font-size: 20px;'>" +
                "The goal is to collect all of the keys, avoid the enemies, and reach the end of the level to be victorious!<br><br>" + // Added an extra <br>
                "Either use 'A, D, and W' or the arrows on your keyboard to steer your character!.</div></html>", SwingConstants.CENTER);
        add(instructionsLabel, BorderLayout.CENTER);

        // Back button at the bottom left
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("<- Back");
        backButton.addActionListener(backButtonListener);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
