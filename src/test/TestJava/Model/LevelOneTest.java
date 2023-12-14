package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

class LevelOneTest {

    @Test
    void constructorInitialization() {
        LevelOne levelOne = new LevelOne();

        assertNotNull(levelOne.getPlatforms());
        assertFalse(levelOne.getPlatforms().isEmpty());

        assertNotNull(levelOne.getKeys());
        assertFalse(levelOne.getKeys().isEmpty());

        assertNotNull(levelOne.getCoins());
        assertFalse(levelOne.getCoins().isEmpty());

        assertNotNull(levelOne.getEnemies());
        assertFalse(levelOne.getEnemies().isEmpty());

        assertNotNull(levelOne.isSpeedPowerUpActive());
        assertNotNull(levelOne.isShieldPowerUpActive());
        assertNotNull(levelOne.isSizePowerUpActive());

        assertNotNull(levelOne.player);
        assertNotNull(levelOne.getPlayerController());
    }

    /*@Test
    void powerUpActivation() {
        LevelOne levelOne = new LevelOne();
        Player player = levelOne.player;

        // Position the player near the speed power-up
        player.setPos(levelOne.getSpeedPowerUpPosition());
        levelOne.updateLevel();
        assertTrue(levelOne.isSpeedPowerUpActive());

        // Similar tests can be written for shieldPowerUp and sizePowerUp
    }*/

    /*@Test
    void powerUpActivation() {
        LevelOne levelOne = new LevelOne();
        Player player = levelOne.player;

        // Position the player near the speed power-up
        Point powerUpPosition = levelOne.getSpeedPowerUpPosition();
        player.setPos(new Point(powerUpPosition.x, powerUpPosition.y));

        // Optionally, add a logging statement or use a debugger to check positions
        System.out.println("Player Position: " + player.getPos());
        System.out.println("PowerUp Position: " + powerUpPosition);

        levelOne.updateLevel();

        // Check if the power-up is active
        assertTrue(levelOne.isSpeedPowerUpActive(), "Speed power-up should be active");
    }*/

}

