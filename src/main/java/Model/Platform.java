package Model;

import java.awt.*;
import java.lang.reflect.Array;

public class Platform {

    //private Point pos;
    private int width;
    private int height;
    private int xPos;
    private int yPos;



    //Only xy-coordinates necessary when initalizing
    public Platform(int xPos, int yPos, int width, int height) {

        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;

    }
    //TODO: Should have no drawing in the model!
    // Draws the visual representation of the platform
    // Return a list of each corner of the platform
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
