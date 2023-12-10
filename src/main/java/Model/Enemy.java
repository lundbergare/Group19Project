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
    public static boolean isImmune;
    //public boolean isImmune;
    private final int maxXPosition;
    private int rectangleX;
    private int rectangleY;
    private int direction;
    private int width;
    private int height;
    private double speed;
    private boolean isFacingRight;

    private final int startXPosition; // Store the initial X position

    public Enemy(int initialX, int initialY, int initialDirection, int maxXPosition, int width, int height, int speed) {
        this.rectangleX = initialX;
        this.startXPosition = initialX;
        this.rectangleY = initialY;
        this.direction = initialDirection;
        this.maxXPosition = maxXPosition; // Set the maximum Y position
        this.speed = speed; // Set the speed
        this.width= width;
        this.height= height;
        this.isFacingRight = isFacingRight;

    }
    //TODO: Try and fix the speed of the Enemy, currently too fast I think
    public void move() {
        rectangleX += direction * speed;

        // Check if the enemy reaches the maxXPosition
        if (rectangleX >= maxXPosition || rectangleX <= startXPosition) {
            reverseDirection(); // Invert the direction

            // Update the direction to move towards the initial position
            if (rectangleX >= maxXPosition) {
                direction = -1; // Move left towards initial position
            } else {
                direction = 1; // Move right towards maxXPosition
            }
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
//    public void setDirection(int direction) {
//        this.direction = direction;
//                notifyObservers();
//
//    }
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
        return ySmurfTop <= underSide - 5 && ySmurfTop >= topSide + 5 && xSmurf > leftSide && xSmurf < rightSide;
    }
         public int[] getArea(){
             return new int[] {rectangleX,rectangleY, rectangleX+ width,rectangleY+height};
         }
         @Override
         public void kill(Player smurf, Enemy enemy) {
             if (!isImmune && collision(smurf, enemy)){
                smurf.setPos(new Point(50,50));
                smurf.die();
            };
                notifyObservers();
         }

}