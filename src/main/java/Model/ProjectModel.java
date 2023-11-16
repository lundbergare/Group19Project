package Model;

import java.util.ArrayList;

public class ProjectModel{


    public static void platformCollision(Player smurf, Platform platform){
        int[] platformArea = platform.getArea();
        int ySmurf = smurf.getPos().y;
        int xSmurf = smurf.getCenterX();
        int surface = platform.pos.y-50;
        int under = platform.pos.y;
        int rightEdge = platformArea[2];
        int leftEdge = platformArea[0];
        //Make sure the player doesn't fall through platforms
        if ((ySmurf > surface)  && (xSmurf < rightEdge) && (xSmurf > leftEdge)) {smurf.GRAVITY = 0;}
            else {smurf.GRAVITY = 6;}
    }
    public static boolean coinCollision(Player smurf, Coin coin){
        int[] coinArea = coin.getArea();
        int ySmurf = smurf.getPos().y-25;
        int xSmurf = smurf.getCenterX();
        int topSide = coin.getPos().y-50;
        int underSide = coin.getPos().y;
        int rightSide = coinArea[2];
        int leftSide = coinArea[0];

        if ((ySmurf >= topSide+5 && ySmurf <= underSide-5) && (xSmurf > leftSide && xSmurf < rightSide)){
                return true;
        }
        return false;
    }

    public static void collectCoins(Player player, ArrayList<Coin> coins){
        // allow player to pickup coins
        ArrayList<Coin> collectedCoins = new ArrayList<>();
        // if the player is within coin reach, collect it
        for (Coin coin: coins) {
            if (coinCollision(player, coin)) {
                // give the player some points for picking this up
                player.addScore(5);
                collectedCoins.add(coin);
            }
        }
        // remove collected coins from the level
        coins.removeAll(collectedCoins);
    }
}

