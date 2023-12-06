package Model;

/**
 * Represents an interface that deines boundaries for a level.
 * Implementing Levels must provide methods to retrieve
 * limits for the X and Y axes.
 */
public interface IBoundary {
    /**
     * Retrieves the limit of the X-axis.
     *
     * @return The limit of the X-axis.
     */
    int getXAxisLimit();
    /**
     * Retrieves the limit of the Y-axis.
     *
     * @return The limit of the Y-axis.
     */
    int getYAxisLimit();
}
