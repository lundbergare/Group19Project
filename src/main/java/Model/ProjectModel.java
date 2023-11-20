package Model;

import java.util.ArrayList;

public class ProjectModel{

    // Make the player's gravity stop when touching a specific platform (does not work when jumping UP to a platform)
    public static void platformCollision(Player smurf, Platform platform){
        int[] platformArea = platform.getArea();
        int ySmurf = smurf.getPos().y;
        int xSmurf = smurf.getCenterX();
        int surface = platform.getYPos()-50;
        int under = platform.getYPos();
        int rightEdge = platformArea[2];
        int leftEdge = platformArea[0];
        //Make sure the player doesn't fall through platforms when on top and within left/right edges
        if ((ySmurf > surface)  && (xSmurf < rightEdge) && (xSmurf > leftEdge)) {smurf.GRAVITY = 0;}
            else {smurf.GRAVITY = 6;}
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
    // Collect a coin when it is within reach (coinCollision), add the coin to array of collected coins
    public static void collectCoins(Player player, ArrayList<Coin> coins){
        // Array of collected coins
        ArrayList<Coin> collectedCoins = new ArrayList<>();
        // if the player is within coin reach, collect it
        for (Coin coin: coins) {
            if (coinCollision(player, coin)) {
                player.addScore(5); //TODO needs new implementation
                collectedCoins.add(coin);
            }
        }
        // remove collected coins from the level
        coins.removeAll(collectedCoins);
    }
}

