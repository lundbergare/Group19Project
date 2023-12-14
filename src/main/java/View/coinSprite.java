package View;

import Model.Coin;
import java.awt.*;

public class coinSprite {

    protected void drawCoin(Graphics g, Coin coin){
        Point pos = coin.getPos();
        g.setColor(Color.yellow);
        int WIDTH = 50;
        int HEIGHT = 50;
        g.fillOval(pos.x, pos.y, WIDTH, HEIGHT);
        g.setColor(Color.gray);
        g.drawString("5", pos.x + 17, pos.y + 33);
    }
}

