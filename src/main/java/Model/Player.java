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
    //TODO not properly implemented score, needs reworking
    private int score;
    private int width = 50;
    private int height = 50;


    //Player will fall down at 2 px/tick gravity by default
    public int GRAVITY = 2;

    //Velocity used when calculating falling/jumping
    private int verticalVelocity;

    //Velocity when initially jumping
    private final int JUMP_VELOCITY = -10;

    // The maximum jump height when initially jumping, decreases while in air.
    private int jumpHeightRemaining;

    //Decides which direction the Player is moving
    private boolean movingRight = false;
    private boolean movingLeft = false;




    public Player() {

        // initialize the state
        pos = new Point(10, 0);
        score = 0;
        verticalVelocity = 0;
        jumpHeightRemaining = 0;

    }

    //Draw the Smurf
    public void drawPlayer(Graphics g){
        g.setColor(Color.cyan);
        g.fillRect(pos.x, pos.y, width, height);
    }


    private void jump(){
        verticalVelocity = JUMP_VELOCITY;
        jumpHeightRemaining = 120; // Set the maximum jump height
    }

    //While there is remaining jump height, the player will keep going up.
    // Jump height decreases by adding vertical (downwards) velocity for each tick.
    private void jumpTick(){
        if (jumpHeightRemaining > 0) {
        pos.translate(0, verticalVelocity);
        jumpHeightRemaining += verticalVelocity;
         }

        else {
        // else Apply gravity (player falls down)
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

    //Moves the player right ways while right direction true
    private void moveRightTick(){
        if (movingRight){
        pos.translate(3, 0);}
    }

    //Moves the player left ways while left direction true
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

    //TODO score not working
    public String getScore() {
        return String.valueOf(score);
    }

    public void addScore(int amount) {
        score += amount;
    }



    public Point getPos() {
        return pos;
    }

    //Used for collisions
    public int getCenterX(){
        return this.pos.x+(this.width/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //The player controls. W key activates the jump() method,
    // and A and D turn their respective booleans true until the keys are released, where they are returned to false.
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
