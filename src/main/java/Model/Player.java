package Model;

import java.awt.Point;


public class Player implements interfacekill {

    public Point pos;
    private int score;
    private int numLives;
    private int keyScore;
    private int width = 50;
    private int height = 50;

    private boolean movingRight = false;
    private boolean movingLeft = false;

    private boolean isPoweredUp = false;
    private long powerUpEndTime;

    private double speedMultiplier = 1.0;
    private long speedPowerUpEndTime;

    private long immunityEndTime;

    //Player will fall down at 2 px/tick gravity by default
    public int GRAVITY = 2;

    //Velocity used when calculating falling/jumping
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

    //Draw the Smurf
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public void jump() {
        if (canJump) {
            //Velocity when initially jumping
            verticalVelocity = -10;
            jumpHeightRemaining = 180;
            canJump = false;// Set the maximum jump height
        }
    }

    //While there is remaining jump height, the player will keep going up.
    // Jump height decreases by adding vertical (downwards) velocity for each tick.
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
        canJump = true; // Allow jumping when the player lands
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }


    public void tick() {
        // this gets called once every tick, before the repainting process happens.
        jumpTick();
        levelBordersTick();
        if (isPoweredUp && System.currentTimeMillis() > powerUpEndTime) {
            isPoweredUp = false;

            width /= 1.8;
            height /= 1.8;
        }

        if (System.currentTimeMillis() > speedPowerUpEndTime) {
            speedMultiplier = 1.0;
        }

        if (System.currentTimeMillis() > immunityEndTime) {
            Enemy.isImmune = false;
        }

        if (movingRight) {
            pos.translate((int)(6 * speedMultiplier), 0);
        }
        if (movingLeft) {
            pos.translate((int)(-6 * speedMultiplier), 0);
        }
    }

    //TODO score not working
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

    public void applySpeedPowerUp(SpeedPowerUpModel powerUp) {
        if (powerUp.isEffectActive()) {
            speedMultiplier = 2.0;
            speedPowerUpEndTime = System.currentTimeMillis() + 5000; // 5 seconds
        }
    }
    public void applyShieldPowerUp(ShieldPowerUpModel powerUp) {
        if (powerUp.isEffectActive()) {
            Enemy.isImmune = true;
            immunityEndTime = System.currentTimeMillis() + 5000; // 5 seconds
        }
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
