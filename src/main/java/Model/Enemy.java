package Model;
import java.awt.Point;
import java.util.Observable;
import View.EnemyView;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Enemy extends Observable implements interfacekill {
    private final int maxXPosition;
    private int rectangleX;
    private int rectangleY;
    private int direction;
    private int width;
    private int height;
    private double speed;
    private Player player;
    private boolean isFacingRight;
    //TODO Not something that is supposed to be in the view: Move over to model.
    public Enemy(int initialX, int initialY, int initialDirection, int maxXPosition, int WIDTH, int HIGHT) {
        this.rectangleX = initialX;
        this.rectangleY = initialY;
        this.direction = initialDirection;
        this.maxXPosition = maxXPosition; // Set the maximum Y position
        this.speed = 6; // Set the speed
        this.width= WIDTH;
        this.height= HIGHT;
        this.isFacingRight = isFacingRight;

    }
    //TODO: Try and fix the speed of the Enemy, currently too fast I think
    public void move() {
        rectangleX += direction * speed;
        if (rectangleX >= maxXPosition || rectangleX <= 340) {
            reverseDirection(); // Invert the direction
        }
        notifyObservers();

    }
    public void reverseDirection() {
        direction *= -1;
    }
    public int getRectangleX() {
        return rectangleX;
    }
    public void setRectangleX(int rectangleX) {
        this.rectangleX = rectangleX;
        notifyObservers();
    }
    public int getRectangleY() {
        return rectangleY;
    }
     public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public void setRectangleY(int rectangleY) {
        this.rectangleY = rectangleY;
            notifyObservers();

    }
    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
                notifyObservers();

    }
    @Override
    public boolean collision(Player smurf, Enemy enemy) {
        int[] enemyArea = enemy.getArea();
        int ySmurfTop = smurf.getPos().y - 25;
        int xSmurf = smurf.getCenterX();
        int topSide = enemy.getRectangleY() - 50;
        int underSide = enemy.getRectangleY();
        int rightSide = enemyArea[2];
        int leftSide = enemyArea[0];
        // Player has to be inside the enemy (above underside, inside left and right) to collide
        if (ySmurfTop <= underSide-5 &&ySmurfTop >= topSide + 5 && xSmurf > leftSide && xSmurf < rightSide) {
            return true;
        }
        return false;
    }
         //returns a list of the coin's corners //TODO refactor
         public int[] getArea(){
             return new int[] {rectangleX,rectangleY, rectangleX+ width,rectangleY+height};
         }
         @Override
         public void kill(Player smurf, Enemy enemy) {
             if (collision(smurf, enemy)){

                smurf.setPos(new Point(50,50));
                smurf.die();
            };
                notifyObservers();

         }

    public boolean isFacingRight() {
            return isFacingRight;
        }
    
    public void setFacingRight(boolean isFacingRight) {
        this.isFacingRight = isFacingRight;
        notifyObservers();
        }
}