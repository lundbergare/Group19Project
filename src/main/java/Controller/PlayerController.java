package Controller;

import Model.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener {
    private Player player;

    public PlayerController(Player player) {
        this.player = player;
    }


    //TODO: Look at accesser, like multiple functions in Player is public which it should not.
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                player.jump();
                break;
            case KeyEvent.VK_D:
                player.setMovingRight(true);
                break;
            case KeyEvent.VK_A:
                player.setMovingLeft(true);
                break;
            // Handle other cases for different keys if needed
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_D:
                player.setMovingRight(false);
                break;
            case KeyEvent.VK_A:
                player.setMovingLeft(false);
                break;
            // Handle other cases for different keys if needed
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Implementation not necessary for your case, can be left empty
    }
}
