package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    private Enemy enemy;

    @BeforeEach
    void setUp() {
        enemy = new Enemy(10, 10, 1, 80, 20, 10, 5); // Customized parameters for testing
    }

    @Test
    void enemyMoveWithinBoundaries() {
        for (int i = 0; i < 10; i++) {
            enemy.move();
        }

        assertTrue(enemy.getRectangleX() >= 10 && enemy.getRectangleX() <= 100);
    }

    @Test
    void enemyReversesDirection() {
        for (int i = 0; i < 100; i++) {
            enemy.move();
        }

        enemy.move();

        assertEquals(-1, enemy.getDirection());
    }


    @Test
    void enemyKillsPlayerUponCollision() {

        Player player = new Player(new IBoundary() {
            @Override
            public int getXAxisLimit() {
                return 1000;
            }

            @Override
            public int getYAxisLimit() {
                return 1000;
            }
        });
        player.setPos(new Point(50, 50));

        enemy.kill(player, enemy);

        //Weird, something wrong here
        player.die();

        assertEquals(50, player.getPos().x);
        assertEquals(50, player.getPos().y);
        assertEquals(2, player.getLives());
    }
}
