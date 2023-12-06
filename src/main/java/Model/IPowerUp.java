package Model;

import java.awt.*;

/**
 * Defines behavior for power-up objects in the game.
 */
public interface IPowerUp {
    /**
     * Retrieves the position of the power-up.
     *
     * @return The position of the power-up as a Point object.
     */
    Point getPosition();

    /**
     * Checks if the power-up is currently active.
     *
     * @return true if the power-up is active, false otherwise.
     */
    boolean isActive();
    /**
     * Activates the power-up.
     */
    void activate();
    /**
     * Checks if the effect of the power-up is currently active.
     *
     * @return true if the effect of the power-up is active, false otherwise.
     */
    boolean isEffectActive();

}
