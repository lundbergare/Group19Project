package Model;

import org.junit.jupiter.api.Test;
import java.awt.Point;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CoinTest {

    @Test
    void collisionShouldBeTrue() {

        Coin coin = new Coin(100, 100);
        Player player = new Player(null);
        player.setPos(new Point(100, 100));

        boolean collisionResult = coin.checkCollision(player);

        assertTrue(collisionResult);
    }

    @Test
    void collisionShouldBeFalse() {
        Coin coin = new Coin(100, 100);
        Player player = new Player(null);
        player.setPos(new Point(90, 75));

        boolean collisionResult = coin.checkCollision(player);

        assertFalse(collisionResult);
    }

    @Test
    void createCoins() {ArrayList<Point> coinPositions = new ArrayList<>();
        coinPositions.add(new Point(100, 100));
        coinPositions.add(new Point(200, 200));
        coinPositions.add(new Point(300, 300));

        ArrayList<Coin> generatedCoins = CoinFactory.createCoins(coinPositions);

        assertNotNull(generatedCoins);


        assertEquals(coinPositions.size(), generatedCoins.size());

        for (int i = 0; i < coinPositions.size(); i++) {
            Point expectedPosition = coinPositions.get(i);
            Coin generatedCoin = generatedCoins.get(i);

            assertEquals(expectedPosition.x, generatedCoin.getPos().x);
            assertEquals(expectedPosition.y, generatedCoin.getPos().y);
        }
    }

    @Test
    void coinsNoPosition() {
        ArrayList<Point> emptyPositions = new ArrayList<>();
        ArrayList<Coin> generatedCoins = CoinFactory.createCoins(emptyPositions);
        assertTrue(generatedCoins.isEmpty());
    }

}
