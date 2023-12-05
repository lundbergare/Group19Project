package Model;

import java.awt.*;
import java.util.ArrayList;

public class Coin implements ICollectable {

    private Point pos;

    // This coin type should only have one final size
    private final int WIDTH = 50;
    private final int HEIGHT = 50;

    // controls how many coins appear on the board, probably unnecessary since each coin will be uniquely placed


    //TODO REMOVE GLOBAL VARIABLE ARGHHH
    public static final int NUM_COINS = 4;

    //Only xy-coordinates necessary when initalizing
    public Coin(int x, int y) {
        pos = new Point(x, y);
    }

    public Point getPos() {
        return pos;
    }

    @Override
    public boolean checkCollision(Player smurf) {
        int[] coinArea = getArea();
        int ySmurf = smurf.getPos().y-25;
        int xSmurf = smurf.getCenterX();
        int topSide = getPos().y-50;
        int underSide = getPos().y;
        int rightSide = coinArea[2];
        int leftSide = coinArea[0];
        // Player has to be inside the coin (below topside, above underside, inside left and right) to collect
        if ((ySmurf >= topSide+5 && ySmurf <= underSide-5) && (xSmurf > leftSide && xSmurf < rightSide)){
            return true;
        }
        return false;
    }

    @Override
    public void collect(Player player) {
        player.addScore(1);
    }

    public static ArrayList<Coin> populateCoins(ArrayList<Point> coinPositions) {
        ArrayList<Coin> coinList = new ArrayList<>();

        for (Point position : coinPositions) {
            coinList.add(new Coin(position.x, position.y));
        }

        return coinList;
    }

    public static void collectCoins(Player player, ArrayList<Coin> coins){
        ArrayList<Coin> collectedCoins = new ArrayList<>();

        for (Coin coin: coins) {
            if (coin.checkCollision(player)) {
                coin.collect(player); // Call collect() when collision happens
                collectedCoins.add(coin);
            }
        }

        coins.removeAll(collectedCoins);
    }

    public int getCenterX(){
        return this.pos.x+(this.WIDTH/2);
    }

    //returns a list of the coin's corners //TODO refactor
    public int[] getArea(){
        return new int[] {pos.x, pos.y, pos.x+WIDTH, pos.y+HEIGHT};
    }


}