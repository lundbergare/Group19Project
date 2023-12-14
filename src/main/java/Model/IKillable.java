package Model;

public interface IKillable {
    void kill(Player smurf, Enemy enemy);
    boolean collision(Player smurf, Enemy enemy);
}
