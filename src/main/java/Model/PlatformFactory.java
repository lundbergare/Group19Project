package Model;

public class PlatformFactory {
    /**
     * A Factory method for creating of Platforms
     *
     * @param x Specific X-coordinate
     * @param y Specific Y-coordinate
     * @param width
     * @param height
     * @return
     */
    public static Platform createPlatform(int x, int y, int width, int height) {
        return new Platform(x, y, width, height);
    }
}
