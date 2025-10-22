// 211699665 Ilai Pingle

package Sprites;

import Geometry.Velocity;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.Collidable;
import Interfaces.HitListener;
import Interfaces.HitNotifier;
import Interfaces.Sprite;
import GameSystem.Game;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * A Block class.
 * A Block has a rectangle and a color.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rectangle;
    private final Color color;
    private static final double EPSILON = 0.0000001;
    private final List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param rectangle - the block's rectangle.
     * @param color     - the block's color.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * get the color of the block.
     *
     * @return the color of the block.
     */
    public Color getColor() {
        return this.color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null || currentVelocity == null) {
            return null;
        }
        double collisionPointX = collisionPoint.getX();
        double collisionPointY = collisionPoint.getY();
        double upperLeftX = this.rectangle.getUpperLeft().getX();
        double upperLeftY = this.rectangle.getUpperLeft().getY();
        double downRightX = upperLeftX + this.rectangle.getWidth();
        double downRightY = upperLeftY + this.rectangle.getHeight();
        boolean collisionLeft = Math.abs(collisionPointX - upperLeftX) < EPSILON;
        boolean collisionRight = Math.abs(collisionPointX - downRightX) < EPSILON;
        boolean collisionTop = Math.abs(collisionPointY - upperLeftY) < EPSILON;
        boolean collisionBottom = Math.abs(collisionPointY - downRightY) < EPSILON;

        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Velocity newVelocity;
        if ((collisionLeft || collisionRight) && (collisionTop || collisionBottom)) {
            newVelocity = new Velocity(-dx, -dy);
        } else if (collisionTop || collisionBottom) {
            newVelocity = new Velocity(dx, -dy);

        } else {
            newVelocity = new Velocity(-dx, dy);
        }
        if (!this.ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        return newVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int upperLeftX = (int) this.rectangle.getUpperLeft().getX();
        int upperLeftY = (int) this.rectangle.getUpperLeft().getY();
        int height = (int) this.rectangle.getHeight();
        int width = (int) this.rectangle.getWidth();

        d.setColor(this.color);
        d.fillRectangle(upperLeftX, upperLeftY, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(upperLeftX, upperLeftY, width, height);
    }

    @Override
    public void timePassed() {
    }

    /**
     * Add this block to the game.
     *
     * @param game - the game to participate in.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * Check if the ball's color matches the block's color.
     *
     * @param ball - the ball to check.
     * @return true if the colors match, false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }

    /**
     * Remove this block from the game.
     *
     * @param game - the game to remove the block from.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        if (hl != null) {
            hitListeners.add(hl);
        }
    }

    @Override
    public void removeHitListener(HitListener hl) {
        if (hl != null) {
            this.hitListeners.remove(hl);
        }
    }

}
