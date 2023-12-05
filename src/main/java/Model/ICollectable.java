package Model;

import java.awt.*;

public interface ICollectable {
    boolean checkCollision(Player player);

    void collect(Player player);
}
