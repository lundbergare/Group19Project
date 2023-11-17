package View;

import Model.EnemyModel;

import java.awt.*;

public class Enemy {
    private EnemyModel enemy;

    public Enemy(EnemyModel enemy) {
        this.enemy = enemy;
        

    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(enemy.getRectangleX(), enemy.getRectangleY(), 50, 50);
        g.drawRect(enemy.getRectangleX(), enemy.getRectangleY(), 50, 50);
    }
}
