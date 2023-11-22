package Model;

import java.awt.*;
import java.util.ArrayList;

public class Coin {

    private Point pos;

    // This coin type should only have one final size
    private final int WIDTH = 50;
    private final int HEIGHT = 50;

    // controls how many coins appear on the board, probably unnecessary since each coin will be uniquely placed
    public static final int NUM_COINS = 4;

    //Only xy-coordinates necessary when initalizing
    public Coin(int x, int y) {
        pos = new Point(x, y);
    }


    public Point getPos() {
        return pos;
    }

    public static ArrayList populateCoins() { //TODO separate drawing coins and adding to coin list
        ArrayList coinList = new ArrayList<>();

        for (int i = 0; i < NUM_COINS; i++) {
            int coinX = (i + 1) * 60;
            int coinY = 450;
            coinList.add(new Coin(coinX, coinY));
        }

        return coinList;
    }

    // Collect a coin when it is within reach (coinCollision), add the coin to array of collected coins
    public static void collectCoins(Player player, ArrayList<Coin> coins){
        // Array of collected coins
        ArrayList<Coin> collectedCoins = new ArrayList<>();
        // if the player is within coin reach, collect it
        for (Coin coin: coins) {
            if (coinCollision(player, coin)) {
                player.addScore(1); //TODO needs new implementation
                collectedCoins.add(coin);
            }
        }
        // remove collected coins from the level
        coins.removeAll(collectedCoins);
    }

    // Returns true if player is within reach of collecting coin, otherwise false
    public static boolean coinCollision(Player smurf, Coin coin){
        int[] coinArea = coin.getArea();
        int ySmurf = smurf.getPos().y-25;
        int xSmurf = smurf.getCenterX();
        int topSide = coin.getPos().y-50;
        int underSide = coin.getPos().y;
        int rightSide = coinArea[2];
        int leftSide = coinArea[0];
        // Player has to be inside the coin (below topside, above underside, inside left and right) to collect
        if ((ySmurf >= topSide+5 && ySmurf <= underSide-5) && (xSmurf > leftSide && xSmurf < rightSide)){
            return true;
        }
        return false;
    }

    public int getCenterX(){
        return this.pos.x+(this.WIDTH/2);
    }

    //returns a list of the coin's corners //TODO refactor
    public int[] getArea(){
        return new int[] {pos.x, pos.y, pos.x+WIDTH, pos.y+HEIGHT};
    }


}