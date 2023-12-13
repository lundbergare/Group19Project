package Model;


public interface ICollectable {
    boolean checkCollision(Player player);

    void collect(Player player);
}
