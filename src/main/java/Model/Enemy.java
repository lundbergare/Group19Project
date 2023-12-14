package Model;
import java.awt.Point;
import java.util.Observable;

public class Enemy extends Observable implements IKillable {

    private final int maxXPosition;
    private int rectangleX;
    private int rectangleY;
    private int direction;
    private final int width;
    private final int height;
    private final double speed;
    protected static boolean isImmune;
    private final int startXPosition;

    protected Enemy(int initialX, int initialY, int initialDirection, int maxXPosition, int width, int height, int speed) {
        this.rectangleX = initialX;
        this.startXPosition = initialX;
        this.rectangleY = initialY;
        this.direction = initialDirection;
        this.maxXPosition = maxXPosition;
        this.speed = speed;
        this.width= width;
        this.height= height;

    }
    protected void move() {
        rectangleX += direction * speed;

        if (rectangleX >= maxXPosition || rectangleX <= startXPosition) {
            reverseDirection();
            if (rectangleX >= maxXPosition) {
                direction = -1;
            } else {
                direction = 1;
            }
        }
        notifyObservers();
    }
    protected void reverseDirection() {
        direction *= -1;
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

}