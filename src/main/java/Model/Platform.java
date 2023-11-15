package Model;

import java.awt.*;

public class Platform {

    public Point pos;

    public Platform(int x, int y) {
        pos = new Point(x, y);
    }


    public void drawPlatform(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(pos.x, pos.y, 600, 50);
    }

    public Point getPos() {
        return pos;
    }

}
