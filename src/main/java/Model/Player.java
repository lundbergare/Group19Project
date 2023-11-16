package Model;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.*;


public class Player implements ActionListener{

    public Point pos;
    private int score;

    private Timer timer;
    public int GRAVITY = 2;
    private int verticalVelocity;

    private final int JUMP_VELOCITY = -10;
    private int jumpHeightRemaining;




    public Player() {

        // initialize the state
        pos = new Point(10, 0);
        score = 0;
        verticalVelocity = 0;
        jumpHeightRemaining = 0;
        timer = new Timer(0, e -> tick());


    }


    public void drawPlayer(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(pos.x, pos.y, 50, 50);
    }

    private void jump(){
        verticalVelocity = JUMP_VELOCITY;
        jumpHeightRemaining = 100; // Set the maximum jump height
    }


    public void tick() {
        // this gets called once every tick, before the repainting process happens.
        if (jumpHeightRemaining > 0) {
            pos.translate(0, verticalVelocity);
            jumpHeightRemaining += verticalVelocity;
        }
        else {
            // Apply gravity
                verticalVelocity = GRAVITY;
                pos.translate(0, verticalVelocity);

            }


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


    public void configureKeyBindings(JComponent component) {
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;

        component.getInputMap(condition).put(KeyStroke.getKeyStroke("W"), "jump");
        component.getActionMap().put("jump", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jump();
            }
        });


        component.getInputMap(condition).put(KeyStroke.getKeyStroke("D"), "moveRight");
        component.getActionMap().put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pos.translate(5, 0);
            }
        });

        // Binding for A key
        component.getInputMap(condition).put(KeyStroke.getKeyStroke("A"), "moveLeft");
        component.getActionMap().put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pos.translate(-5, 0);
            }
        });
    }
}