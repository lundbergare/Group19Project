package Model;

import java.util.ArrayList;

public class ProjectModel {

    public static void platformCollision(Player smurf, ArrayList<Platform> platforms) {
        boolean onPlatform = false;
        for (Platform platform : platforms) {
            int[] platformArea = platform.getArea();
            int ySmurf = smurf.getPos().y;
            int xSmurf = smurf.getCenterX();
            int surface = platform.getYPos() - 51;
            int under = platform.getYPos() -10;
            int rightEdge = platformArea[2];
            int leftEdge = platformArea[0];

            // Check if the player is on the current platform
            if ((ySmurf > surface) && (xSmurf < rightEdge) && (xSmurf > leftEdge) && (ySmurf < under)) {
                smurf.GRAVITY = 0;
                smurf.land();
                onPlatform = true;
                break;
            }
        }
        if (!onPlatform) {
            smurf.GRAVITY = 6;
        }
    }
}

