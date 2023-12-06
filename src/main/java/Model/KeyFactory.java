package Model;

import java.awt.*;
import java.util.ArrayList;

public class KeyFactory {
    public static ArrayList<Key> createKeys(ArrayList<Point> keyPositions) {
        ArrayList<Key> keyList = new ArrayList<>();

        for (Point position : keyPositions) {
            keyList.add(new Key(position.x, position.y));
        }

        return keyList;
    }
}
