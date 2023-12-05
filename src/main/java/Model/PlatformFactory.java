package Model;

public class PlatformFactory {
    public static Platform createPlatform(int x, int y, int width, int height) {
        return new Platform(x, y, width, height);
    }
}
