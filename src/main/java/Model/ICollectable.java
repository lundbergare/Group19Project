package Model;

/**
 * Represents behavior for objects that can be collected in the game.
 */
public interface ICollectable {
    boolean checkCollision(Player player);

    void collect(Player player);
}
