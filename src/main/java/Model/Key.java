package Model;

import java.awt.*;
import java.util.ArrayList;


public class Key {

    private Point pos;

    private final int WIDTH = 50;
    private final int HEIGHT = 50;
    public static final int NUM_KEYS = 2;

    public Key(int x, int y) {
        pos = new Point(x, y);
    }

    public Point getPos() {
        return pos;
    }
    public static ArrayList<Key> populateKeys() {
        ArrayList<Key> keyList = new ArrayList<>();
    
        keyList.add(new Key(580, 270));
    
        keyList.add(new Key(500, 460));
    
        return keyList;
    }
    
    

    public static void collectKeys(Player player, ArrayList<Key> keys) {
        ArrayList<Key> collectedKeys = new ArrayList<>();
        for (Key key : keys) {
            if (keyCollision(player, key)) {
                player.addKeys(NUM_KEYS);
                collectedKeys.add(key);
            }
        }
        keys.removeAll(collectedKeys);
    }

    public static boolean keyCollision(Player player, Key key) {
        int[] keyArea = key.getArea();
        int yPlayer = player.getPos().y - 25;
        int xPlayer = player.getCenterX();
        int topSide = key.getPos().y - 50;
        int underSide = key.getPos().y;
        int rightSide = keyArea[2];
        int leftSide = keyArea[0];
        return (yPlayer >= topSide + 5 && yPlayer <= underSide - 5) && (xPlayer > leftSide && xPlayer < rightSide);
    }

    public int getCenterX() {
        return this.pos.x + (this.WIDTH / 2);
    }

    public int[] getArea() {
        return new int[]{pos.x, pos.y, pos.x + WIDTH, pos.y + HEIGHT};
    }
}
