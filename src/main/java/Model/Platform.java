package Model;

import java.awt.*;
import java.lang.reflect.Array;

public class Platform {

    //private Point pos;
    private final int width;
    private final int height;
    private final int xPos;
    private final int yPos;



    /**
     * A platform with specified position and dimensions.
     *
     * @param xPos The x-coordinate of the platform's top-left corner.
     * @param yPos The y-coordinate of the platform's top-left corner.
     * @param width The width of the platform.
     * @param height The height of the platform.
     */
    public Platform(int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;

    }

    /**
     * Retrieves coordinates that represents the area covered by the platform
     * The array contains the x and y-coordinates in the top left corner
     * @return An array with positions
     */
    public int[] getArea() {
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
