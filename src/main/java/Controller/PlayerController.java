package Controller;

import Model.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener {
    private final Player player;

    public PlayerController(Player player) {
        this.player = player;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> player.jump();
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> player.setMovingRight(true);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> player.setMovingLeft(true);

            // Handle other cases for different keys if needed
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> player.setMovingRight(false);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> player.setMovingLeft(false);

            // Handle other cases for different keys if needed
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Implementation not necessary for your case, can be left empty
    }
}
