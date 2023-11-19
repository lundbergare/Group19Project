package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//We will probably not use this class.

//TODO: Should delete this, since it does not add anything right now.
public class NewScreenPanel extends JPanel {
    private EnemyView enemy;

    public NewScreenPanel() {


        Timer timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                repaint();
            }
        });
        timer.start();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


    }
}
