package Model;
import javax.swing.*;
import java.awt.*;
public class Enemy extends JPanel {
    private int rectangleX;
    private int rectangleY;
    private int direction;
    private int width;
    public Point pos;
    public void setWidth(int width) {
        this.width = width;
    }
    public Enemy(int rectangleX, int rectangleY, int direction, int width) {
        pos = new Point(rectangleX, rectangleY);
        this.direction = direction;
        this.width = width;
    }
    public void moveRectangle() {
        pos.translate(0, this.direction);
        if (this.rectangleX >= getWidth() - 50 || this.rectangleX <= 450) {
            this.direction *= -1;
        }
    }
    public void drawEnemy(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(getRectangleX(), getRectangleY(), 50, 100);
        g.drawRect(getRectangleX(), getRectangleY(), 50, 100);
    }
    public int getRectangleX() {
        return rectangleX;
    }
    public void setRectangleX(int rectangleX) {
        this.rectangleX = rectangleX;
    }
    public int getRectangleY() {
        return rectangleY;
    }
    public void setRectangleY(int rectangleY) {
        this.rectangleY = rectangleY;
    }
    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
    @Override
    public String toString() {
        return "Enemy []";
    }
}



