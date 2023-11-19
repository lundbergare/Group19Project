package View;

import Model.Player;

import java.awt.*;

public class PlayerView {

    private Player player;
    public PlayerView(Player player) {
        this.player = player;
    }
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(player.getPos().x, player.getPos().y, player.getWidth(), player.getHeight());
    }
}
