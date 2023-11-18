package Model;

import java.awt.*;

public class Coin {

    private Point pos;

    // This coin type should only have one final size
    private final int WIDTH = 50;
    private final int HEIGHT = 50;

    //Only xy-coordinates necessary when initalizing
    public Coin(int x, int y) {
        pos = new Point(x, y);
    }


    // Draws the visual representation of the coin
    public void drawCoin(Graphics g){
        g.setColor(Color.yellow);
        g.fillOval(pos.x, pos.y, WIDTH, HEIGHT);
        g.setColor(Color.gray);
        g.drawString("5", pos.x + 17, pos.y + 33);
    }


    public Point getPos() {
        return pos;
    }

    public int getCenterX(){
        return this.pos.x+(this.WIDTH/2);
    }

    //returns a list of the coin's corners //TODO refactor
    public int[] getArea(){
        return new int[] {pos.x, pos.y, pos.x+WIDTH, pos.y+HEIGHT};
    }


}