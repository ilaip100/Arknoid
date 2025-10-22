// 211699665 Ilai Pingle

package Interfaces;

import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Sprites.Ball;

/**
 * The Collidable interface.
 * This interface will be used by things that can be collided with.
 */
public interface Collidable {
    /**
     * Get the collision rectangle.
     *
     * @return Rectangle - the collision rectangle.
     */
    Rectangle getCollisionRectangle();


    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint  - the collision point.
     * @param currentVelocity - the current velocity.
     * @return Velocity - the new velocity after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}

