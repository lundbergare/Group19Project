package Model;

import java.util.ArrayList;

public class ProjectModel {

    public static void platformCollision(Player smurf, Platform platform) {
        int[] platformArea = platform.getArea();
        int ySmurf = smurf.getPos().y;
        int xSmurf = smurf.getCenterX();
        int surface = platform.getYPos() - 50;
        int under = platform.getYPos() + 10;
        int rightEdge = platformArea[2];
        int leftEdge = platformArea[0];

        // Make sure the player doesn't fall through platforms when on top and within left/right edges
        if ((ySmurf > surface) && (xSmurf < rightEdge) && (xSmurf > leftEdge) && ySmurf < under) {
            smurf.GRAVITY = 0;
            smurf.land(); // Reset the canJump flag when the player lands on a platform
        } else {
            smurf.GRAVITY = 6;
        }
    }
}

