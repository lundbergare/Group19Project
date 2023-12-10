package Model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {

    private IBoundary boundary;
    private Player player;

    private Enemy enemy;

    @BeforeEach
    void setUp() {
        boundary = new IBoundary() {
            @Override
            public int getXAxisLimit() {
                return 1000;
            }

            @Override
            public int getYAxisLimit() {
                return 1000;
            }
        };

        player = new Player(boundary);
        enemy = new Enemy(10, 10, 1, 20, 10, 10, 0); // Customize parameters as needed

    }

    @Test
    void jump() {
        assertTrue(player.canJump);
        player.jump();
        assertFalse(player.canJump);
        assertEquals(-10, player.verticalVelocity);
        assertEquals(180, player.jumpHeightRemaining);
    }

    @Test
    void playerKillsEnemy(){
        player.kill(player, enemy);

        assertEquals(-100, enemy.getRectangleX());
        assertEquals(-100, enemy.getRectangleY());
    }

    @Test
    void bordersTick(){
        player.setPos(new Point(1005, 1005));


        player.levelBordersTick();


        assertEquals(950, player.getPos().x);
        assertEquals(950, player.getPos().y);
    }
    @Test
    void collisionDetection() {
        Player player = new Player(boundary);
        player.setPos(new Point(50, 50));
        Enemy enemy = new Enemy(50, 50, 1, 60, 10, 10,0);

        assertTrue(player.collision(player, enemy));
    }

    @Test
    void noCollisionDetection(){
        Player player = new Player(boundary);
        player.setPos(new Point(50, 50));
        Enemy enemy = new Enemy(65, 65, 1, 60, 10, 10,0);
        assertFalse(player.collision(player, enemy));
    }

    /*
    Sort of worthless lol
     */
    @Test
    void addScoreIncreasesPlayerScore() {
        int initialScore = Integer.parseInt(player.getScore());
        int amountToAdd = 50;

        player.addScore(amountToAdd);

        assertEquals(initialScore + amountToAdd, Integer.parseInt(player.getScore()));
    }

    @Test
    void sizeBoostIncAndDec() {
        int initialWidth = player.getWidth();
        int initialHeight = player.getHeight();
        player.activateSizeBoost(5000);
        assertEquals(initialWidth * 2, player.getWidth());
        assertEquals(initialHeight * 2, player.getHeight());

        player.deactivateSizeBoost();

        assertEquals(initialWidth, player.getWidth());
        assertEquals(initialHeight, player.getHeight());
    }


    @Test
    void playerMovesCorrectly() {
        Point initialPos = new Point(100, 100);
        player.setPos(initialPos);

        player.setMovingRight(true);
        player.tick();
        assertEquals(initialPos.x , player.getPos().x);


        Point newPos = new Point(player.getPos());
        player.setMovingLeft(true);

        assertEquals(newPos.x , player.getPos().x);

        player.setMovingRight(false);
        player.setMovingLeft(false);
        newPos = new Point(player.getPos());
        player.tick();
        assertEquals(newPos.x, player.getPos().x);
    }

    @Test
    void sizeBoostActivationAndDeactivation() {
        // Activate size boost
        long boostDuration = 1000;
        player.activateSizeBoost(boostDuration);

        assertTrue(player.isSizePoweredUp);
        assertEquals(100, player.getWidth());
        assertEquals(100, player.getHeight());
        try {
            Thread.sleep(boostDuration + 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        player.tick();
        assertFalse(player.isSizePoweredUp);
        assertEquals(50, player.getWidth());
        assertEquals(50, player.getHeight());
    }


}
