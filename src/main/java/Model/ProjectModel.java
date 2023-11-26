package Model;

import java.util.ArrayList;

public class ProjectModel{

    // Make the player's gravity stop when touching a specific platform (does not work when jumping UP to a platform)
    public static void platformCollision(Player smurf, Platform platform){
        int[] platformArea = platform.getArea();
        int ySmurf = smurf.getPos().y;
        int xSmurf = smurf.getCenterX();
        int surface = platform.getYPos()-50;
        int under = platform.getYPos() +10;
        int rightEdge = platformArea[2];
        int leftEdge = platformArea[0];
        //Make sure the player doesn't fall through platforms when on top and within left/right edges
        if ((ySmurf > surface)  && (xSmurf < rightEdge) && (xSmurf > leftEdge) && ySmurf <under ) {smurf.GRAVITY = 0;}
            else {smurf.GRAVITY = 6;}
    }

}

