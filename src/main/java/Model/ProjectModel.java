package Model;

public class ProjectModel{


    public static void groundCollision(Player smurf, Platform platform){
        int[] platformArea = platform.getArea();
        if ((smurf.pos.y > platform.pos.y-50)  && (smurf.pos.x < platformArea[2])) {smurf.GRAVITY = 0;}
            else {smurf.GRAVITY = 6;}
    }}
