package Model;

import java.awt.Point;


public class Player implements interfacekill {

    public Point pos;
    //TODO not properly implemented score, needs reworking
    private int score;
    private int numLives;
    private int keyScore;
    public void setNumLives(int numLives) {
        this.numLives = numLives;
    }
    private int width = 50;
    private int height = 50;

    private boolean movingRight = false;
    private boolean isSpeedPoweredUp = false;
    private boolean movingLeft = false;

    private boolean isPoweredUp = false;
    private long powerUpEndTime;

    private double speedMultiplier = 1.0;
    private long speedPowerUpEndTime;

    //private boolean isImmune = false;
    private long immunityEndTime;

    //Player will fall down at 2 px/tick gravity by default
    public int GRAVITY = 2;

    //Velocity used when calculating falling/jumping
    private int verticalVelocity;
    private boolean canJump = true;

    private boolean facingRight = true;
    private IBoundary boundary;


    private boolean hasDoubleJumped = false;

    // The maximum jump height when initially jumping, decreases while in air.
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

    public void activateSpeedBoost(long duration) {
        this.isSpeedPoweredUp = true;
        this.speedMultiplier = 1.8;
        this.speedPowerUpEndTime = System.currentTimeMillis() + duration;
    }

    //While there is remaining jump height, the player will keep going up.
    // Jump height decreases by adding vertical (downwards) velocity for each tick.

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

    //TODO: Must also fix the moving right and left: seem to "pixellike"
    //Moves the player right ways while right direction true
    //Moves the player right ways while right direction true
    public void moveRightTick() {
        if (movingRight) {
            pos.translate(1, 0);
            facingRight = true; // Player is moving right
        }
        /*if (movingRight && isSpeedPoweredUp) {
            pos.translate((int)(6 * speedMultiplier), 0);
            facingRight = true;
        }*/
    }

    //Moves the player left ways while left direction true
    //Moves the player left ways while left direction true
    public void moveLeftTick() {
        if (movingLeft) {
            pos.translate(-5, 0);
            facingRight = false; // Player is moving left
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

    public void activateShield(long duration) {
        Enemy.isImmune = true;
        this.immunityEndTime = System.currentTimeMillis() + duration;
    }


    public void tick() {
        // this gets called once every tick, before the repainting process happens.
        jumpTick();
        levelBordersTick();
        /*if (isPoweredUp && System.currentTimeMillis() > powerUpEndTime) {
            isPoweredUp = false;

            width /= 1.8;
            height /= 1.8;
        }

        if (System.currentTimeMillis() > speedPowerUpEndTime) {
            speedMultiplier = 1.0;
        }

        if (System.currentTimeMillis() > immunityEndTime) {
            Enemy.isImmune = false;
        }*/

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
        public void addScorekey(int amount) {
        score += amount;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    // TODO implement properly
    public void addLives() {

    }

    public void removeLives() {

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

    /*public boolean isImmune() {
        return isImmune; // 'isImmune' is a boolean field in the Player class
    }*/

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
        if (playerBottom >= yEnemyTop && playerBottom <= yEnemyBottom
                && smurf.getPos().x >= enemy.getRectangleX() && smurf.getPos().x <= enemy.getRectangleX() + enemy.getWidth()) {
            return true;
        }
        return false;
    }

//    public void applyPowerUp(PowerUpModel powerUp) {
//        if (powerUp.isEffectActive()) {
//        }
//        isPoweredUp = true;
//        powerUpEndTime = System.currentTimeMillis() + 5000; // 5 seconds from now
//        int oldHeight = height;
//        // Double the size
//        width *= 1.8;
//        height *= 1.8;
//    }

//    public void applySpeedPowerUp(SpeedPowerUpModel powerUp) {
//        if (powerUp.isEffectActive()) {
//            speedMultiplier = 2.0;
//            speedPowerUpEndTime = System.currentTimeMillis() + 5000; // 5 seconds
//        }
//    }
//
//    public void applyShieldPowerUp(ShieldPowerUpModel powerUp) {
//        if (powerUp.isEffectActive()) {
//            Enemy.isImmune = true;
//            immunityEndTime = System.currentTimeMillis() + 5000; // 5 seconds
//        }
//    }

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
