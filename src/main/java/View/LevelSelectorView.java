package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelSelectorView extends JPanel {
    private JButton backButton;

    public LevelSelectorView(ActionListener backButtonListener) {
        // Add components and content for the new screen
        JLabel label = new JLabel("This is the newer screen");
        add(label);

        // Add a "Back" button to return to the previous screen
        backButton = new JButton("Back");
        backButton.addActionListener(backButtonListener);
        add(backButton);
        System.out.print("New screen now");
    }
}
