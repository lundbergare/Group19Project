package Model;

import org.junit.jupiter.api.Test;
import java.awt.Point;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class KeyTest {

    @Test
    void yesCollision() {
        Player player = new Player(null);
        player.setPos(new Point(100, 50));
        Key key = new Key(100, 50);

        boolean collisionResult = key.checkCollision(player);

        assertTrue(collisionResult);
    }

    @Test
    void noCollision() {
        Player player = new Player(null);
        player.setPos(new Point(200, 200));
        Key key = new Key(100, 100);


        boolean collisionResult = key.checkCollision(player);


        assertFalse(collisionResult);
    }

    @Test
    void collectKeys() {

        Player player = new Player(null);
        Key key = new Key(100, 50);
        int initialKeys = player.getKeys();


        key.collect(player);


        assertEquals(initialKeys , player.getKeys());
    }

    @Test
    void collectKeysIncrease() {
        Player player = new Player(null);
        Key key1 = new Key(100, 50);
        Key key2 = new Key(200, 100);
        ArrayList<Key> keys = new ArrayList<>();
        keys.add(key1);
        keys.add(key2);
        int initialKeyCount = keys.size();


        Key.collectKeys(player, keys);

        assertEquals(initialKeyCount , keys.size());
    }

    @Test
    void createKeys() {
        ArrayList<Point> keyPositions = new ArrayList<>();
        keyPositions.add(new Point(100, 100));
        keyPositions.add(new Point(200, 200));
        keyPositions.add(new Point(300, 300));

        ArrayList<Key> keys = KeyFactory.createKeys(keyPositions);

        assertNotNull(keys);
        assertEquals(keyPositions.size(), keys.size());
        for (int i = 0; i < keyPositions.size(); i++) {
            assertEquals(keyPositions.get(i), keys.get(i).getPos());
        }
    }

    @Test
    void keysNoPosition() {
        ArrayList<Point> emptyKeyPositions = new ArrayList<>();

        ArrayList<Key> keys = KeyFactory.createKeys(emptyKeyPositions);

        assertNotNull(keys);
        assertEquals(0, keys.size());
    }
}
