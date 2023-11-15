package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewScreenPanel extends JPanel {
    private Enemy enemy;

    public NewScreenPanel() {
        enemy = new Enemy(450, 500, 1);

        Timer timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveEnemy();
                repaint();
            }
        });
        timer.start();
    }

    private void moveEnemy() {
        enemy.move();
        if (enemy.getRectangleX() >= getWidth() - 50 || enemy.getRectangleX() <= 450) {
            enemy.reverseDirection();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        g.fillRect(enemy.getRectangleX(), enemy.getRectangleY(), 50, 100);
        g.drawRect(enemy.getRectangleX(), enemy.getRectangleY(), 50, 100);

    }
}
