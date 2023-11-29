package Model;

import Controller.PlayerController;
import View.CoinView;
import View.EnemyView;
import View.PlayerView;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


public class Level1 extends Level {

    private ArrayList<Coin> coins;
    private Platform platform;
    private Enemy enemy;
    private CoinView coinView;
    private EnemyView enemyView;

    private ArrayList<Platform> platforms; // Declare the ArrayList for platforms




    public Level1() {
        super();
        platform = new Platform(90, 500, 400, 50);
        platforms = new ArrayList<>();

        platforms.add(platform);
        coins = Coin.populateCoins();
        coinView = new CoinView();
        enemy = new Enemy(100, 100, 1, 30,50,50);
        enemyView = new EnemyView(enemy);
    }

    @Override
    protected void updateLevel() {
        // Level-specific update logic TICK
        ProjectModel.platformCollision(player, platforms);
        Coin.collectCoins(player, coins);
        enemy.move();
    }

    protected void drawLevel(Graphics g) {
        // Level-specific drawing code
        for (Coin coin : coins) {
            coinView.drawCoin(g, coin); // Draw each coin
        }
        playerView.draw(g);
        drawPlayerInfo(g);
        enemyView.draw(g);
    }

    private void drawPlayerInfo(Graphics g) {

    }

    @Override
    public int getXAxisLimit() {
        return XAXIS;
    }

    @Override
    public int getYAxisLimit() {
        return YAXIS;
    }
}