package Model;

public class EnemyModel {

    private int rectangleX;
    private int rectangleY;
    private int direction;

    public EnemyModel(int initialX, int initialY, int initialDirection) {
        this.rectangleX = initialX;
        this.rectangleY = initialY;
        this.direction = initialDirection;
    }

    public void move() {
        rectangleX += direction;
        reverseDirection();
    }


    //TODO: Quite hardcoded right now, must be fixed, but ohwell
    public void reverseDirection() {
        if (this.getRectangleX() >= 1000 || this.getRectangleX() <= 450) {
            direction *= -1;
        }
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
