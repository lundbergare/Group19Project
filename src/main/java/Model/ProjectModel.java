package Model;

import java.util.ArrayList;

public class ProjectModel {

    public static void platformCollision(Player smurf, ArrayList<Platform> platforms) {
        boolean onPlatform = false;
        for (Platform platform : platforms) {
            int[] platformArea = platform.getArea();
            int bottomOfPlayer = smurf.getPos().y + smurf.getHeight();
            int centerXOfPlayer = smurf.getPos().x + (smurf.getWidth() / 2);
            int platformSurface = platform.getYPos();
            int platformLeftEdge = platformArea[0];
            int platformRightEdge = platformArea[2];  

            // Check if the bottom of the player is just above the platform surface
            if (bottomOfPlayer >= platformSurface && bottomOfPlayer <= platformSurface + 10 &&
                    centerXOfPlayer >= platformLeftEdge && centerXOfPlayer <= platformRightEdge) {
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

