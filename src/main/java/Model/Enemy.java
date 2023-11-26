package Model;

public class Enemy {
    private final int maxXPosition;
    private int rectangleX;
    private int rectangleY;
    private int direction;

    private double speed;

    //TODO Not something that is supposed to be in the view: Move over to model.

    public Enemy(int initialX, int initialY, int initialDirection, int maxXPosition) {
        this.rectangleX = initialX;
        this.rectangleY = initialY;
        this.direction = initialDirection;
        this.maxXPosition = maxXPosition; // Set the maximum Y position
        this.speed = 6; // Set the speed

    }

    //TODO: Try and fix the speed of the Enemy, currently too fast I think
    public void move() {
        rectangleX += direction * speed;
        if (rectangleX >= maxXPosition || rectangleX <= 340) {
            reverseDirection(); // Invert the direction
        }
    }

    public void reverseDirection() {
        direction *= -1;
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
}