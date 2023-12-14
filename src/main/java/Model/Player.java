package Model;

import java.awt.Point;


public class Player implements IKillable {

    private Point pos;
    private int score;
    private int numLives;
    private int keyScore;
    private int width = 50;
    private int height = 50;
    private boolean movingRight = false;
    private boolean isSpeedPoweredUp = false;
    private boolean movingLeft = false;
    private double speedMultiplier = 1.0;
    private long speedPowerUpEndTime;
    private long immunityEndTime;
    protected boolean isSizePoweredUp = false;
    private long sizePowerUpEndTime;
    protected int GRAVITY = 2;
    protected int verticalVelocity;
    protected boolean canJump = true;
    private final IBoundary boundary;
    protected int jumpHeightRemaining;

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



    protected void activateSpeedBoost() {
        this.isSpeedPoweredUp = true;
        this.speedMultiplier = 1.8;
        this.speedPowerUpEndTime = System.currentTimeMillis() + (long) 5000;
    }

    protected void activateSizeBoost(long duration) {
        this.isSizePoweredUp = true;
        this.sizePowerUpEndTime = System.currentTimeMillis() + duration;
        this.width *= 2; // Double the width
        this.height *= 2; // Double the height
    }

    protected void deactivateSizeBoost() {
        this.width /= 2; // Revert to original width
        this.height /= 2; // Revert to original height
        this.isSizePoweredUp = false;
    }

    protected void levelBordersTick() {
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
    //TODO change access
    public boolean isStandingStill() {
        return !movingLeft && !movingRight;
    }

    public void jump() {
        if (canJump) {
            //Velocity when initially jumping
            verticalVelocity = -10;
            jumpHeightRemaining = 180;
            canJump = false;// Set the maximum jump height
        }
    }

    protected void jumpTick() {
        if (jumpHeightRemaining > 0) {
            pos.translate(0, verticalVelocity);
            jumpHeightRemaining += verticalVelocity;
        } else {
            // else Apply gravity (player falls down)
            verticalVelocity = GRAVITY;
            pos.translate(0, verticalVelocity);
        }
    }



    protected void land() {
        canJump = true;
    }



    protected void tick() {
        jumpTick();
        levelBordersTick();

        if (isSpeedPoweredUp && System.currentTimeMillis() > speedPowerUpEndTime) {
            isSpeedPoweredUp = false;
            speedMultiplier = 1.0;
        }

        if (Enemy.isImmune && System.currentTimeMillis() > immunityEndTime) {
            Enemy.isImmune = false;
        }

        if (isSizePoweredUp && System.currentTimeMillis() > sizePowerUpEndTime) {
            deactivateSizeBoost();
        }

        if (movingRight) {
            pos.translate((int)(6 * speedMultiplier), 0);
        }
        if (movingLeft) {
            pos.translate((int)(-6 * speedMultiplier), 0);
        }
    }
    protected void addScore(int amount) {
        score += amount;
    }
    protected void die () {
        numLives -= 1;
    }

    @Override
    public void kill(Player player, Enemy enemy) {
        int smurfY = player.getPos().y;
        int platform5Y = 700;
        if (!Enemy.isImmune && collision(player, enemy)) {
            enemy.setRectangleY(-100);
            enemy.setRectangleX(-100);
        }
        if (smurfY > platform5Y) {
            player.setPos(new Point(50, 50)); // Reset player position
            player.die(); // Decrease player's life or handle death logic
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

    protected int getCenterX() {
        return this.pos.x + (this.width / 2);
    }

    protected void addKeys() {
    keyScore += 1;

    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public void activateShield(long duration) {
        Enemy.isImmune = true;
        this.immunityEndTime = System.currentTimeMillis() + duration;
    }

    public int getKeyScore() {
        return keyScore;
    }

    public int getKeys() {
        return 0;
    }
    public boolean isFacingRight() {
        return true;
    }
    public int getLives() {
        return numLives;
    }

    public Point getPos() {
        return pos;
    }


    public String getScore() {
        return String.valueOf(score);
    }
}
