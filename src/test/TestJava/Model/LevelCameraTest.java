package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.awt.*;

public class LevelCameraTest {

    private LevelCamera camera;
    private final int cameraWidth = 1000;
    private final int cameraHeight = 750;

    @BeforeEach
    public void setUp() {
        camera = new LevelCamera(cameraWidth, cameraHeight);
    }

    @Test
    public void testCameraUpdateWithinBounds() {
        Point playerPosition = new Point(500, 400);
        int levelWidth = 2000;
        int levelHeight = 1500;

        camera.update(playerPosition, levelWidth, levelHeight);

        assertTrue(camera.getX() >= 0 && camera.getX() <= levelWidth - cameraWidth);
        assertTrue(camera.getY() >= 0 && camera.getY() <= levelHeight - cameraHeight);
    }

    @Test
    public void testCameraUpdateAtLevelBoundary() {
        Point playerPosition = new Point(1900, 1400);
        int levelWidth = 2000;
        int levelHeight = 1500;

        camera.update(playerPosition, levelWidth, levelHeight);

        assertEquals(levelWidth - cameraWidth, camera.getX());
        assertEquals(levelHeight - cameraHeight, camera.getY());
    }

    @Test
    public void testCameraUpdateNearLeftBoundary() {
        Point playerPosition = new Point(100, 400);
        int levelWidth = 2000;
        int levelHeight = 1500;

        camera.update(playerPosition, levelWidth, levelHeight);

        assertEquals(0, camera.getX());
        assertTrue(camera.getY() >= 0 && camera.getY() <= levelHeight - cameraHeight);
    }

    @Test
    public void testCameraUpdateBeyondLevelBounds() {
        Point playerPosition = new Point(2100, 1600);
        int levelWidth = 2000;
        int levelHeight = 1500;

        camera.update(playerPosition, levelWidth, levelHeight);

        assertEquals(levelWidth - cameraWidth, camera.getX());
        assertEquals(levelHeight - cameraHeight, camera.getY());
    }

}
