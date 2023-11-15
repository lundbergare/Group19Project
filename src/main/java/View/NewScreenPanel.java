package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class NewScreenPanel extends JPanel {
    private JButton backButton;
    private JButton level1Button;

    public NewScreenPanel(ActionListener backButtonListener) {
        setLayout(null);

        level1Button = new JButton("Level 1");
        level1Button.setBounds(400, 350, 120, 120); // Adjust the position and size here
        add(level1Button);

        backButton = new JButton("<- Back");
        backButton.setBounds(20,650,120, 45);
        backButton.addActionListener(backButtonListener);
        add(backButton);
    }
}
