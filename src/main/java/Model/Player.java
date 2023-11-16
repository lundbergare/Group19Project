package Model;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.*;
import java.awt.event.KeyEvent;


public class Player implements ActionListener{

    private Point pos;
    private int score;

    public int GRAVITY = 2;
    private int verticalVelocity;

    private final int JUMP_VELOCITY = -10;
    private int jumpHeightRemaining;

    private int width = 50;
    private int height = 50;

    private boolean movingRight = false;
    private boolean movingLeft = false;




    public Player() {

        // initialize the state
        pos = new Point(10, 0);
        score = 0;
        verticalVelocity = 0;
        jumpHeightRemaining = 0;

    }


    public void drawPlayer(Graphics g){
        g.setColor(Color.cyan);
        g.fillRect(pos.x, pos.y, width, height);
    }

    private void jump(){
        verticalVelocity = JUMP_VELOCITY;
        jumpHeightRemaining = 120; // Set the maximum jump height
    }

    private void jumpTick(){
        if (jumpHeightRemaining > 0) {
        pos.translate(0, verticalVelocity);
        jumpHeightRemaining += verticalVelocity;
         }

        else {
        // Apply gravity
        verticalVelocity = GRAVITY;
        pos.translate(0, verticalVelocity);

         }

    }


    private void levelBordersTick(){
        // prevent the player from moving off the edge of the board sideways
        if (pos.x < 0) {
            pos.x = 0;
        } else if (pos.x >= TestingLevel.YAXIS) {
            pos.x = TestingLevel.YAXIS - 1;
        }

        // prevent the player from moving off the edge of the board vertically
        if (pos.y < 0) {
            pos.y = 0;
        } else if (pos.y >= TestingLevel.XAXIS) {
            pos.y = TestingLevel.XAXIS - 1;
        }
    }

    private void moveRightTick(){
        if (movingRight){
        pos.translate(3, 0);}
    }

    private void moveLeftTick(){
        if (movingLeft){
            pos.translate(-3, 0);}
    }



    public void tick() {
        // this gets called once every tick, before the repainting process happens.

        jumpTick();
        levelBordersTick();
        moveRightTick();
        moveLeftTick();

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

    public int getCenterX(){
        return this.pos.x+(this.width/2);
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


        component.getInputMap(condition).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "pressedMoveRight");
        component.getActionMap().put("pressedMoveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingRight = true;
            }
        });


        component.getInputMap(condition).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "releasedMoveRight");
        component.getActionMap().put("releasedMoveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingRight = false;
            }
        });


        // Binding for A key
        component.getInputMap(condition).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "pressedMoveLeft");
        component.getActionMap().put("pressedMoveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingLeft = true;
            }
        });

        component.getInputMap(condition).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "releasedMoveLeft");
        component.getActionMap().put("releasedMoveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingLeft = false;
            }
        });
    }
    }
