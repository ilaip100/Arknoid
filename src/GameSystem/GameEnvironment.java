// 211699665 Ilai Pingle

package GameSystem;

import Geometry.Line;
import Geometry.Point;
import Interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game environment.
 * contains all the collidables in the game
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * Constructor.
     * initializes the collidables list.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Add the given collidable to the environment.
     *
     * @param c the collidable to add.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * @return the collidables list.
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * get the balls trajectory and return the closest collision point:
     * between the trajectory and all the collidables in the environment.
     *
     * @param trajectory - the balls trajectory.
     * @return - the closest collisionInfo between trajectory and the environment.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (trajectory == null || this.collidables == null || this.collidables.isEmpty()) {
            return null;
        }
        List<Collidable> collidablesCopy = new ArrayList<>(this.collidables);
        double minDistance = Double.MAX_VALUE;
        Collidable closestCollidable = null;
        Point closestPoint = null;

        for (Collidable collidable : collidablesCopy) {
            Point intersectionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (intersectionPoint != null) {
                double distance = trajectory.start().distance(intersectionPoint);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestCollidable = collidable;
                    closestPoint = intersectionPoint;
                }
            }
        }
        if (closestCollidable == null) {
            return null;
        }
        return new CollisionInfo(closestPoint, closestCollidable);
    }
}
