package Model;

import java.awt.*;
import java.util.ArrayList;


public class KeyFactory {
    /**
     * A Factory method to create a list of Keys based on given coordinates.
     *
     * @param keyPositions A list of points specifying the specific Keys position.
     * @return An Arraylist of the created Keys and its coordinates
     */
    public static ArrayList<Key> createKeys(ArrayList<Point> keyPositions) {
        ArrayList<Key> keyList = new ArrayList<>();

        for (Point position : keyPositions) {
            keyList.add(new Key(position.x, position.y));
        }

        return keyList;
    }
}
