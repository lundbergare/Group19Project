package View;


import Model.EnemyModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewScreenPanel extends JPanel {
    private EnemyModel enemy;
    private Enemy en;

    public NewScreenPanel() {
        enemy = new EnemyModel(450, 650, 1);
        en = new Enemy(enemy);

        Timer timer = new Timer(3, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enemy.move();
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        en.draw(g);
    }
}

