// 211699665 Ilai Pingle

package GameSystem;

import Geometry.Point;
import Interfaces.Collidable;

/**
 * CollisionInfo class.
 * holds information about the collision.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Constructor.
     *
     * @param collisionPoint  - the point at which the collision occurs.
     * @param collisionObject - the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return collisionPoint - the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return collisionObject - the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}
