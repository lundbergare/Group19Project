package Model;

import java.awt.Color;
import java.awt.Graphics;

//TODO We can delete this one, no one is using this
public class PlayerModel {
    public int x, y;
    public int width;
    public int height;

    public int velX, velY;

    public PlayerModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

}

