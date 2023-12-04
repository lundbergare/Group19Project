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

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    private ArrayList<Coin> coins;
    private Platform platform;
    private Enemy enemy;
    private CoinView coinView;
    private EnemyView enemyView;

    public Level1() {
        super();
        Platform platform1 = new Platform(0, 500, 300, 50);
        Platform platform2 = new Platform(370, 500, 200, 50);
        Platform platform3 = new Platform(400, 300, 200, 50);
        Platform platform4 = new Platform(670, 300, 200, 50);

        platforms.add(platform1);
        platforms.add(platform2);
        platforms.add(platform3);
        platforms.add(platform4);
        coins = Coin.populateCoins();
        enemy = new Enemy(500, 450, 1, 850);
    }

    @Override
    protected void updateLevel() {
        // Level-specific TICK

        ProjectModel.platformCollision(player, platforms);
        Coin.collectCoins(player, coins);
        enemy.move();
    }


    public Enemy getEnemy() {
        return enemy;
    }

}