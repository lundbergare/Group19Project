package View;

public class Enemy { private int rectangleX;
    private int rectangleY;
    private int direction;

    public Enemy(int initialX, int initialY, int initialDirection) {
        this.rectangleX = initialX;
        this.rectangleY = initialY;
        this.direction = initialDirection;
    }

    public void move() {
        rectangleX += direction;
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