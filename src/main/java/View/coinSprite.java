package View;

import Model.Coin;
import java.awt.*;

public class coinSprite {
    // This coin type should only have one final size
    private final int WIDTH = 50;
    private final int HEIGHT = 50;

    //public CoinView() {
    //}

    // Draws the visual representation of the coin
    public void drawCoin(Graphics g, Coin coin){
        Point pos = coin.getPos();
        g.setColor(Color.yellow);
        g.fillOval(pos.x, pos.y, WIDTH, HEIGHT);
        g.setColor(Color.gray);
        g.drawString("5", pos.x + 17, pos.y + 33);
    }
}

