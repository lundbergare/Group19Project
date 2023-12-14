package Model;

import java.awt.*;
import java.util.ArrayList;

public class CoinFactory {
    protected static ArrayList<Coin> createCoins(ArrayList<Point> coinPositions) {
        ArrayList<Coin> coinList = new ArrayList<>();

        for (Point position : coinPositions) {
            coinList.add(new Coin(position.x, position.y));
        }

        return coinList;
    }


}
