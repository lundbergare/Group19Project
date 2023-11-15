package Model;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.*;


public class Player implements ActionListener{

    public Point pos;
    private int score;

    private Timer timer;
    public int GRAVITY = 4;


    public Player() {

        // initialize the state
        pos = new Point(10, 0);
        score = 0;
        Timer timer = new Timer(100 ,this);

    }

    ActionListener jumpFunction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            jump();
            timer.setRepeats(false);
            timer.start();}
    };




    public void drawPlayer(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(pos.x, pos.y, 50, 50);
    }

    private void jump(){
        GRAVITY = -5;
        pos.translate(75, -200);
    }
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            jump();
        }

        if (key == KeyEvent.VK_D) {
            pos.translate(5, 0);
        }

        if (key == KeyEvent.VK_A) {
            pos.translate(-5, 0);
        }
    }

    public void tick() {
        // this gets called once every tick, before the repainting process happens.

        // Gravitational force
        gravity();

        // prevent the player from moving off the edge of the board sideways
        if (pos.x < 0) {
            pos.x = 0;
        } else if (pos.x >= Board.YAXIS) {
            pos.x = Board.YAXIS - 1;
        }

        // prevent the player from moving off the edge of the board vertically
        if (pos.y < 0) {
            pos.y = 0;
        } else if (pos.y >= Board.XAXIS) {
            pos.y = Board.XAXIS - 1;
        }

    }

    public void gravity(){
        pos.translate(0, GRAVITY);
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public void addScore(int amount) {
        score += amount;
    }

    public Point getPos() {
        return pos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}