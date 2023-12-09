package Model;

import java.util.ArrayList;

public class PlatformFactory {
    private static final ArrayList<Platform> platforms = new ArrayList<>();

    public static Platform createPlatform(int x, int y, int width, int height) {
        Platform platform = new Platform(x, y, width, height);
        platforms.add(platform); // Automatically add the created platform to the list
        return platform;
    }

    public static ArrayList<Platform> getPlatforms() {
        return platforms;
    }
}
