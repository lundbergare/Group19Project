package Model;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Player implements ActionListener, KeyListener {

    private Point pos;
    //TODO not properly implemented score, needs reworking
    private int score;
    private int width = 50;
    private int height = 50;

    private boolean movingRight = false;
    private boolean movingLeft = false;

    //Player will fall down at 2 px/tick gravity by default
    public int GRAVITY = 2;

    //Velocity used when calculating falling/jumping
    private int verticalVelocity;

    //Velocity when initially jumping
    private final int JUMP_VELOCITY = -1;

    // The maximum jump height when initially jumping, decreases while in air.
    private int jumpHeightRemaining;
    public Player() {
        // initialize the state
        pos = new Point(10, 0);
        score = 0;
        verticalVelocity = 0;
        jumpHeightRemaining = 0;

    }
    //Draw the Smurf
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }



    //While there is remaining jump height, the player will keep going up.
    // Jump height decreases by adding vertical (downwards) velocity for each tick.

    //TODO: The player should not depend on the TestingLevel class.

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

    //TODO: Must also fix the moving right and left: seem to "pixellike"
    //Moves the player right ways while right direction true
    //Moves the player right ways while right direction true
    private void moveRightTick(){
        if (movingRight){
            pos.translate(1, 0);}
    }

    //Moves the player left ways while left direction true
    //Moves the player left ways while left direction true
    private void moveLeftTick(){
        if (movingLeft){
            pos.translate(-1, 0);}
    }


    //TODO: fix the Jump function: jumps in a very weird way.
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


    //TODO: Everything below here must be moved to a seperate controller, PlayerController. Does not adhere to MVC.
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                jump();

                break;
            case KeyEvent.VK_D:
                movingRight = true;

                moveRightTick();
                break;
            case KeyEvent.VK_A:
                movingLeft = true;
                moveLeftTick();
                break;
            // Handle other cases for different keys if needed
        }
        }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_D:
                movingRight = false;
                break;
            case KeyEvent.VK_A:
                movingLeft = false;
                break;
            // Handle other cases for different keys if needed
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // Implementation not necessary for your case, can be left empty
    }





}

