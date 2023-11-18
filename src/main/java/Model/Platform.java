package Model;

import java.awt.*;
import java.lang.reflect.Array;

public class Platform {

    public Point pos;
    private int width = 600;
    private int height = 50;

    //Only xy-coordinates necessary when initalizing
    public Platform(int x, int y) {
        pos = new Point(x, y);
    }

    // Draws the visual representation of the platform
    public void drawPlatform(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(pos.x, pos.y, width, height);
    }
    // Return a list of each corner of the platform
    public int[] getArea(){
        return new int[] {pos.x, pos.y, pos.x+width, pos.y+height};
    }

    public Point getPos() {
        return pos;
    }

}
