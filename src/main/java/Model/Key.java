package Model;

import java.awt.*;
import java.util.ArrayList;


public class Key implements ICollectable {

    private final Point pos;

    public static final int NUM_KEYS = 3;

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
    @Override
    public void collect(Player player) {
        player.addKeys(1);
    }

    public static void collectKeys(Player player, ArrayList<Key> keys) {
        ArrayList<Key> collectedKeys = new ArrayList<>();
        for (Key key : keys) {
            if (key.checkCollision(player)) {
                key.collect(player);
                collectedKeys.add(key);
            }
        }
        keys.removeAll(collectedKeys);
    }

    public int[] getArea() {
        int WIDTH = 50;
        int HEIGHT = 50;
        return new int[]{pos.x, pos.y, pos.x + WIDTH, pos.y + HEIGHT};
    }
}
