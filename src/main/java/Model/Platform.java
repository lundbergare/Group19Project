package Model;

public class Platform {
    private final int width;
    private final int height;
    private final int xPos;
    private final int yPos;

    protected Platform(int xPos, int yPos, int width) {

        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = 50;

    }

    protected int[] getArea() {
        return new int[] { xPos, yPos, xPos + width, yPos + height };
    }

    public int getXPos(){
        return xPos;
    }
    public int getYPos(){
        return yPos;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
