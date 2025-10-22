// 211699665 Ilai Pingle

package Sprites;

import biuoop.DrawSurface;
import Interfaces.Collidable;
import Interfaces.Sprite;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import GameSystem.Game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a paddle in the game.
 * The paddle is a rectangle that is controlled by the arrow keys, and moves according to the player's input.
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private final Color color;
    private final int boardWidth;
    private static final int SPEED = 7;
    private static final double EPSILON = 0.0000001;
    private final List<Ball> balls = new ArrayList<>();


    /**
     * Constructor.
     *
     * @param rectangle  the paddle's rectangle
     * @param keyboard   the keyboard sensor
     * @param color      the paddle's color
     * @param boardWidth the width of the board
     */
    public Paddle(Rectangle rectangle, biuoop.KeyboardSensor keyboard, Color color, int boardWidth) {
        this.rectangle = rectangle;
        this.keyboard = keyboard;
        this.color = color;
        this.boardWidth = boardWidth;
    }

    /**
     * Move the paddle right by one step.
     * @param ball to add to the paddles' list.
     */
    public void addBall(Ball ball) {
        this.balls.add(ball);
    }

    /**
     * Remove a ball from the paddle.
     *
     * @param ball the ball to remove
     */
    public void removeBall(Ball ball) {
        this.balls.remove(ball);
    }

    /**
     * Move the paddle left by one step.
     */
    public void moveLeft() {
        double paddleX = this.rectangle.getUpperLeft().getX();
        double paddleY = this.rectangle.getUpperLeft().getY();
        double paddleWidth = this.rectangle.getWidth();
        double paddleHeight = this.rectangle.getHeight();
        Rectangle nextPosition = new Rectangle(new Point(paddleX - SPEED, paddleY), paddleWidth, paddleHeight);
        for (Ball ball : balls) {
            if (ball.isBallUnderBlock(nextPosition)) {
                return;
            }
        }
        if (paddleX + paddleWidth <= 25) {
            Point p = new Point(this.boardWidth - paddleWidth - 25, paddleY);
            this.rectangle = new Rectangle(p, paddleWidth, paddleHeight);
        } else {
            this.rectangle = new Rectangle(new Point(paddleX - SPEED, paddleY), paddleWidth, paddleHeight);
        }
    }

    /**
     * Move the paddle right by one step.
     */
    public void moveRight() {
        double paddleX = this.rectangle.getUpperLeft().getX();
        double paddleY = this.rectangle.getUpperLeft().getY();
        double paddleWidth = this.rectangle.getWidth();
        double paddleHeight = this.rectangle.getHeight();
        Rectangle nextPosition = new Rectangle(new Point(paddleX + SPEED, paddleY), paddleWidth, paddleHeight);
        for (Ball ball : balls) {
            if (ball.isBallUnderBlock(nextPosition)) {
                return;
            }
        }
        if (paddleX + 25 >= boardWidth) {
            this.rectangle = new Rectangle(new Point(25, paddleY), paddleWidth, paddleHeight);
        } else {
            this.rectangle = new Rectangle(new Point(paddleX + SPEED, paddleY), paddleWidth, paddleHeight);
        }
    }

    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
            this.moveRight();
        }
        if (this.keyboard.isPressed(this.keyboard.LEFT_KEY)) {
            this.moveLeft();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {

        int upperLeftX = (int) this.rectangle.getUpperLeft().getX();
        int upperLeftY = (int) this.rectangle.getUpperLeft().getY();
        int height = (int) this.rectangle.getHeight();
        int width = (int) this.rectangle.getWidth();
        d.setColor(Color.BLACK);
        d.drawRectangle(upperLeftX, upperLeftY, width, height);
        d.setColor(this.color);
        d.fillRectangle(upperLeftX, upperLeftY, width, height);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double collisionPointX = collisionPoint.getX();
        double upperLeftX = this.rectangle.getUpperLeft().getX();
        double width = this.rectangle.getWidth();
        double paddleSection = width / 5;
        double speed = currentVelocity.getSpeed();
        if (collisionPointX + EPSILON >= upperLeftX && collisionPointX < upperLeftX + paddleSection) {
            return Velocity.fromAngleAndSpeed(300, speed);
        }
        if (collisionPointX >= upperLeftX + paddleSection && collisionPointX < upperLeftX + paddleSection * 2) {
            return Velocity.fromAngleAndSpeed(330, speed);
        }
        if (collisionPointX >= upperLeftX + paddleSection * 2 && collisionPointX < upperLeftX + paddleSection * 3) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (collisionPointX >= upperLeftX + paddleSection * 3 && collisionPointX < upperLeftX + paddleSection * 4) {
            return Velocity.fromAngleAndSpeed(30, speed);
        }
        if (collisionPointX >= upperLeftX + paddleSection * 4 && collisionPointX - EPSILON <= upperLeftX + width) {
            return Velocity.fromAngleAndSpeed(60, speed);
        }
        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
    }

    /**
     * Adds the paddle to the game.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}