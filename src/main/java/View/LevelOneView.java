package View;

import Model.*;

import javax.swing.*;
import java.awt.*;

/*import java.awt.geom.AffineTransform;

public class LevelOneView extends LevelView{
    protected EnemyView enemyView;
    private final EnemyView enemyView2;
    private final EnemyView enemyView3;
    private final Image speedPowerUpImage;
    private final Image shieldPowerUpImage;
    private final Image sizePowerUpImage;

    private final LevelOne level;


    public LevelOneView(LevelOne level){
        super(level);
        this.level = level;
        coins = level.getCoins();
        platforms = level.getPlatforms();
        keys = level.getKeys();
        super.playerView = new PlayerView(level.player);
        enemyView = new EnemyView(level.getEnemy());
        enemyView2 = new EnemyView(level.getEnemy2());
        enemyView3 = new EnemyView(level.getEnemy3());


        ImageIcon icon = new ImageIcon("src/main/java/View/ImagesForView/raspberry.png");
        speedPowerUpImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon("src/main/java/View/ImagesForView/shield2.png");
        shieldPowerUpImage = icon2.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon icon3 = new ImageIcon("src/main/java/View/ImagesForView/mushroom.png");
        sizePowerUpImage = icon3.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);




        // Make sure the view is focusable and has focus for key events to work

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCoins(g);
        drawKeys(g);
        paintUserInterface(g);
        //drawLevel(g);

    }


}


 */