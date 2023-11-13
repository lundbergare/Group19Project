package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LevelSelectorView extends JPanel {
    private JButton backButton;

    public LevelSelectorView(ActionListener backButtonListener) {

        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 650));

        backButton = new JButton("<- Back");
        backButton.addActionListener(backButtonListener);
        add(backButton);
    }
}
