package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.Point;
import java.util.ArrayList;

class ProjectModelTest {

    @Test
    void testPlatformCollision() {
        Player player = new Player(); // Replace with actual constructor
        ArrayList<Platform> platforms = new ArrayList<>();

        // Set up the player and platform positions for collision
        player.setPos(new Point(50, 50)); // Example position

        Platform platform = new Platform(50, 100, 100); // xPos, yPos, width
        platforms.add(platform);

        // Test collision
        ProjectModel.platformCollision(player, platforms);
        assertEquals(0, player.GRAVITY); // Gravity should be 0 when on platform

        // Test no collision
        player.setPos(new Point(200, 200)); // Move player away from the platform
        ProjectModel.platformCollision(player, platforms);
        assertEquals(6, player.GRAVITY); // Gravity should be 6 when not on platform
    }
}

