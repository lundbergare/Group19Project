package Model;

public class ProjectModel{


    public static void groundCollision(Player smurf, Platform platform){
        if (smurf.pos.y >= platform.pos.y-50) {smurf.GRAVITY = 0;}
            else {smurf.GRAVITY = 6;}
    }}
