package Model;

import java.awt.Point;


public class Player implements interfacekill {

    public Point pos;
    //TODO not properly implemented score, needs reworking
    private int score;
    private int numLives;
    private int keyScore;
    private final int width = 50;
    private int height = 50;

    private boolean movingRight = false;
    private boolean isSpeedPoweredUp = false;
    private boolean movingLeft = false;

    private double speedMultiplier = 1.0;
    private long speedPowerUpEndTime;

    private long immunityEndTime;

    public int GRAVITY = 2;

    private int verticalVelocity;
    private boolean canJump = true;

    private boolean facingRight = true;
    private IBoundary boundary;
    private int jumpHeightRemaining;

    public Player(IBoundary boundary) {
        // initialize the state
        pos = new Point(10, 0);
        score = 0;
        keyScore = 0;
        verticalVelocity = 0;
        jumpHeightRemaining = 0;
        numLives = 3;
        this.boundary = boundary;

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void activateSpeedBoost(long duration) {
        this.isSpeedPoweredUp = true;
        this.speedMultiplier = 1.8;
        this.speedPowerUpEndTime = System.currentTimeMillis() + duration;
    }

    private void levelBordersTick() {
        // prevent the player from moving off the edge of the board sideways
        if (pos.x < 0) {
            pos.x = 0;
        } else if (pos.x >= boundary.getXAxisLimit()) {
            pos.x = boundary.getXAxisLimit() - 50;
        }
        // prevent the player from moving off the edge of the board vertically
        if (pos.y < 0) {
            pos.y = 0;
        } else if (pos.y >= boundary.getYAxisLimit()) {
            pos.y = boundary.getYAxisLimit() - 50;
        }
    }

    public boolean isStandingStill() {
        return !movingLeft && !movingRight;
    }


    //TODO: fix the Jump function: jumps in a very weird way.
    public void jump() {
        if (canJump) {
            //Velocity when initially jumping
            verticalVelocity = -10;
            jumpHeightRemaining = 180;
            canJump = false;// Set the maximum jump height
        }
    }

    public void jumpTick() {
        if (jumpHeightRemaining > 0) {
            pos.translate(0, verticalVelocity);
            jumpHeightRemaining += verticalVelocity;
        } else {
            // else Apply gravity (player falls down)
            verticalVelocity = GRAVITY;
            pos.translate(0, verticalVelocity);
        }
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public void land() {
        canJump = true;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public void activateShield(long duration) {
        Enemy.isImmune = true;
        this.immunityEndTime = System.currentTimeMillis() + duration;
    }


    public void tick() {
        jumpTick();
        levelBordersTick();

        if (isSpeedPoweredUp && System.currentTimeMillis() > speedPowerUpEndTime) {
            isSpeedPoweredUp = false;
            speedMultiplier = 1.0;
        }

        if (Enemy.isImmune && System.currentTimeMillis() > immunityEndTime) {
            Enemy.isImmune = false;
        }

        if (movingRight) {
            pos.translate((int)(6 * speedMultiplier), 0);
        }
        if (movingLeft) {
            pos.translate((int)(-6 * speedMultiplier), 0);
        }
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public void addScore(int amount) {
        score += amount;
    }
    public void die () {
        numLives -= 1;
    }
    public boolean isFacingRight() {
        return facingRight;
    }
    public int getLives() {
        return numLives;
    }

    public Point getPos() {
        return pos;
    }

    //Used for collisions
    public int getCenterX() {
        return this.pos.x + (this.width / 2);
    }
    @Override
    public void kill(Player player, Enemy enemy) {
        if (!Enemy.isImmune && collision(player, enemy)) {
            enemy.setRectangleY(-100);
            enemy.setRectangleX(-100);
        }
    }
    @Override
    public boolean collision(Player smurf, Enemy enemy) {
        int yEnemyTop = enemy.getRectangleY() - 50;  // Top of the enemy
        int yEnemyBottom = enemy.getRectangleY();     // Bottom of the enemy
        int playerBottom = smurf.getPos().y;          // Bottom of the player
        return playerBottom >= yEnemyTop && playerBottom <= yEnemyBottom
                && smurf.getPos().x >= enemy.getRectangleX() && smurf.getPos().x <= enemy.getRectangleX() + enemy.getWidth();
    }

    public void addKeys(int i) {
    keyScore += 1;

    }

    public int getKeyScore() {
        return keyScore;
    }

    public int getKeys() {
        return 0;
    }

}
