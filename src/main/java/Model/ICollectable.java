package Model;

import java.awt.*;

public interface ICollectable {
    Point getPos();
    boolean checkCollision(Player player);

    //TODO Implement collect() in all the classes that implements ICollectable
    void collect(Player player);
}
