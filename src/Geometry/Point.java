// 211699665 Ilai Pingle

package Geometry;

import java.util.Random;

/**
 * Represents a point in a 2D space.
 **/
public class Point {
    private static final double EPSILON = 0.0000001;
    private final double x;
    private final double y;

    /**
     * Constructs a new point with the specified x and y coordinates.
     *
     * @param x The x-coordinate of the new point.
     * @param y The y-coordinate of the new point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other The point to calculate the distance to.
     * @return The distance between this point and the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * Compares this point to another object for equality.
     *
     * @param other The object to compare this point against.
     * @return true if the other object is a Point with the same x and y coordinates; false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return (Math.abs(this.x - other.getX()) < EPSILON && Math.abs(this.y - other.getY()) < EPSILON);
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return The x-coordinate of this point.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return The y-coordinate of this point.
     */
    public double getY() {
        return y;
    }
}