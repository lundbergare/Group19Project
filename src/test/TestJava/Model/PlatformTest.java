package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlatformTest {

    @Test
    void getArea() {
        int xPos = 100;
        int yPos = 200;
        int width = 150;

        Platform platform = new Platform(xPos, yPos, width);

        int[] expectedArea = {xPos, yPos, xPos + width, yPos + 50};

        int[] actualArea = platform.getArea();

        assertArrayEquals(expectedArea, actualArea);
    }

    @Test
    void gettersShouldBeGOOD() {
        int xPos = 100;
        int yPos = 200;
        int width = 150;
        int height = 50;

        Platform platform = new Platform(xPos, yPos, width);

        assertEquals(xPos, platform.getXPos());
        assertEquals(yPos, platform.getYPos());
        assertEquals(width, platform.getWidth());
        assertEquals(height, platform.getHeight());
    }

    @Test
    void createPlatform_ShouldAddPlatformToList() {

        int x = 100;
        int y = 200;
        int width = 150;
        PlatformFactory.createPlatform(x, y, width);

        ArrayList<Platform> platforms = PlatformFactory.getPlatforms();


        Platform platform = platforms.get(0);
        assertEquals(x, platform.getXPos());
        assertEquals(y, platform.getYPos());
        assertEquals(width, platform.getWidth());
        assertEquals(50, platform.getHeight());
    }

    @Test
    void getPlatforms_ShouldReturnCorrectPlatforms() {
        assertTrue(PlatformFactory.getPlatforms().isEmpty());

        PlatformFactory.createPlatform(100, 200, 150);
        PlatformFactory.createPlatform(300, 400, 200);

        ArrayList<Platform> platforms = PlatformFactory.getPlatforms();

        assertEquals(2, platforms.size());

        assertEquals(100, platforms.get(0).getXPos());
        assertEquals(200, platforms.get(0).getYPos());
        assertEquals(150, platforms.get(0).getWidth());
        assertEquals(50, platforms.get(0).getHeight());

        assertEquals(300, platforms.get(1).getXPos());
        assertEquals(400, platforms.get(1).getYPos());
        assertEquals(200, platforms.get(1).getWidth());
        assertEquals(50, platforms.get(1).getHeight());
    }
}
