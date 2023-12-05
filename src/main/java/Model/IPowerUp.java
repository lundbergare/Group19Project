package Model;

import java.awt.*;

public interface IPowerUp {
    Point getPosition();
    boolean isActive();
    void activate();
    boolean isEffectActive();
    void reset();

}
