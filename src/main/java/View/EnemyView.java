package View;

import Model.Enemy;

import java.awt.*;

public class EnemyView {
    private final Enemy enemy;
  public EnemyView(Enemy enemy){
      this.enemy = enemy;
  }
  public void draw(Graphics g){
      g.setColor(Color.RED);
      g.fillRect(enemy.getRectangleX(), enemy.getRectangleY(), 50,50);
      g.drawRect(enemy.getRectangleX(), enemy.getRectangleY(), 50,50);
  }
}