package Model;

import Controller.PlayerController;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


public class Level1 extends Level {

    private ArrayList<Coin> coins;
    private Platform platform;
    private Enemy enemy;


    public Level1() {
        super();
        enemy = new Enemy(500, 450, 1, 850);
        platform = new Platform(90, 500, 400, 50);
        coins = Coin.populateCoins();
    }

    @Override
    protected void updateLevel() {
        // Level-specific update logic
        ProjectModel.platformCollision(player, platform);
        Coin.collectCoins(player, coins);
        enemy.move();
    }

    protected void drawLevel(Graphics g) {
        // Level-specific drawing code
        for (Coin coin : coins) {
       //     drawCoin(g, coin);
        }
        playerView.draw(g);
        drawPlayerInfo(g);
    }

    private void drawPlayerInfo(Graphics g) {

    }
}