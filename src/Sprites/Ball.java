// 211699665 Ilai Pingle

package Sprites;

import GameSystem.CollisionInfo;
import GameSystem.Game;
import GameSystem.GameEnvironment;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;


/**
 * The Ball class represents a ball with a position, size, color, and velocity.
 * It can move within specified boundaries and bounce off the walls.
 */
public class Ball implements Sprite {
    private final double r;
    private Point center;
    private Color color;
    private Velocity velocity;
    private GameEnvironment environment;
    private static final double EPSILON = 0.0000001;


    /**
     * Constructor to create a Ball with a given center point, radius, and color.
     *
     * @param center      the center point of the ball
     * @param r           the radius of the ball
     * @param color       the color of the ball
     * @param environment the game environment
     */
    public Ball(Point center, int r, Color color, GameEnvironment environment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.environment = environment;
    }

    /**
     * Gets the x-coordinate of the center of the ball.
     *
     * @return the x-coordinate
     */
    public double getX() {
        return this.center.getX();
    }

    /**
     * Gets the y-coordinate of the center of the ball.
     *
     * @return the y-coordinate
     */
    public double getY() {
        return this.center.getY();
    }

    /**
     * Gets the radius of the ball.
     *
     * @return the radius
     */
    public double getSize() {
        return this.r;
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of the ball.
     *
     * @param color the new color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the environment of the ball.
     *
     * @param environment - the environment for the ball.
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;

    }

    /**
     * Sets the velocity of the ball.
     *
     * @param dx the change in x-direction
     * @param dy the change in y-direction
     */
    public void setVelocity(double dx, double dy) {
        velocity = new Velocity(dx, dy);
    }

    /**
     * Gets the velocity of the ball.
     *
     * @return the current velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Moves the ball one step according to its velocity, adjusting for boundary collisions.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center, this.velocity.applyToPoint(this.center));
        if (this.environment == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        CollisionInfo collision = this.environment.getClosestCollision(trajectory);
        if (collision == null) {
            this.center = this.velocity.applyToPoint(this.center);
        } else if (collision.collisionPoint() != null) {
            makeBallAlmostCollide(collision.collisionPoint());
            this.setVelocity(collision.collisionObject().hit(this, collision.collisionPoint(), this.velocity));
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    private void makeBallAlmostCollide(Point collisionPoint) {
        double dx = collisionPoint.getX() - this.center.getX();
        double dy = collisionPoint.getY() - this.center.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        double adjustDistance = this.r - distance + EPSILON;
        double adjustX = dx / distance * adjustDistance;
        double adjustY = dy / distance * adjustDistance;
        this.center = new Point(this.center.getX() - adjustX, this.center.getY() - adjustY);
    }

    /**
     * Generates a random color in RGB format.
     *
     * @return a random color.
     */
    public static Color generateRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the surface to draw the ball on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.getX(), (int) this.getY(), (int) this.getSize());
        surface.setColor(this.color);
        surface.fillCircle((int) this.getX(), (int) this.getY(), (int) this.getSize());
    }

    /**
     * adds the Ball to the game.
     *
     * @param game the game to add the Ball to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * Removes the ball from the game.
     *
     * @param game the game to remove the ball from.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
    /**
     * checks if the ball is under a block.
     *
     * @param rectangle the rectangle to check if the ball is under.
     * @return true if the ball is under the block, false otherwise.
     */
    public boolean isBallUnderBlock(Rectangle rectangle) {
        double centerX = this.center.getX();
        double centerY = this.center.getY();
        double blockLeftX = rectangle.getUpperLeft().getX();
        double blockRightX = blockLeftX + rectangle.getWidth();
        double blockTopY = rectangle.getUpperLeft().getY();
        double blockBottomY = blockTopY + rectangle.getHeight();

        boolean isHorizontallyAligned = (centerX - EPSILON > blockLeftX && centerX + EPSILON < blockRightX);
        boolean isVerticallyAligned = (centerY - EPSILON > blockTopY && centerY + EPSILON < blockBottomY);

        return isHorizontallyAligned && isVerticallyAligned;
    }
}