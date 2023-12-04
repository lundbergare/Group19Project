package Model;

import java.awt.*;
import java.util.ArrayList;


public class Key implements ICollectable {

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

    @Override
    public boolean checkCollision(Player player) {
        int[] keyArea = getArea();
        int yPlayer = player.getPos().y - 25;
        int xPlayer = player.getCenterX();
        int topSide = getPos().y - 50;
        int underSide = getPos().y;
        int rightSide = keyArea[2];
        int leftSide = keyArea[0];
        return (yPlayer >= topSide + 5 && yPlayer <= underSide - 5) && (xPlayer > leftSide && xPlayer < rightSide);
    }

    //TODO Does not do anything here :(
    @Override
    public void collect(Player player) {

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
            if (key.checkCollision(player)) {
                player.addKeys(NUM_KEYS);
                collectedKeys.add(key);
            }
        }
        keys.removeAll(collectedKeys);
    }


    public int getCenterX() {
        return this.pos.x + (this.WIDTH / 2);
    }

    public int[] getArea() {
        return new int[]{pos.x, pos.y, pos.x + WIDTH, pos.y + HEIGHT};
    }
}
