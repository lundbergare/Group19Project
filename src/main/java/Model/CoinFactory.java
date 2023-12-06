package Model;

import java.awt.*;
import java.util.ArrayList;

public class CoinFactory {
    /**
     * A Factory method to create a list of Coins based on given coordinates.
     *
     * @param coinPositions A list of points specifying the specific Coins position
     * @return An Arraylist of the created Coins and its coordinates
     */
    public static ArrayList<Coin> createCoins(ArrayList<Point> coinPositions) {
        ArrayList<Coin> coinList = new ArrayList<>();

        for (Point position : coinPositions) {
            coinList.add(new Coin(position.x, position.y));
        }

        return coinList;
    }
}
