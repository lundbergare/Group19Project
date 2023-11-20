package View;

import Model.Player;

import java.awt.*;

public class PlayerView {


    private int playerWidth = 50;
    private int playerHeight = 50;
    private Player player;
    public PlayerView(Player player) {
        this.player = player;
    }
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(player.getPos().x, player.getPos().y, playerWidth, playerHeight);
    }
}
