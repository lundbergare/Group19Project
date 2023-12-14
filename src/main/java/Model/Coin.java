package Model;

import java.awt.*;
import java.util.ArrayList;

public class Coin implements ICollectable {

    private final Point pos;
    public static final int NUM_COINS = 4;

    protected Coin(int x, int y) {
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
        return (ySmurf >= topSide + 5 && ySmurf <= underSide - 5) && (xSmurf > leftSide && xSmurf < rightSide);
    }

    @Override
    public void collect(Player player) {
        player.addScore(1);
    }

    protected static void collectCoins(Player player, ArrayList<Coin> coins){
        ArrayList<Coin> collectedCoins = new ArrayList<>();

        for (Coin coin: coins) {
            if (coin.checkCollision(player)) {
                coin.collect(player); // Call collect() when collision happens
                collectedCoins.add(coin);
            }
        }

        coins.removeAll(collectedCoins);
    }

    protected int[] getArea(){
        // This coin type should only have one final size
        int WIDTH = 50;
        int HEIGHT = 50;
        return new int[] {pos.x, pos.y, pos.x+ WIDTH, pos.y+ HEIGHT};
    }


}