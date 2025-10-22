// 211699665 Ilai Pingle

package Geometry;

import java.util.List;

/**
 * Represents a line in 2D space defined by two points.
 */
public class Line {
    private final Point start;
    private final Point end;
    private static final double EPSILON = 0.0000001;

    /**
     * Constructs a line with the specified start and end points.
     *
     * @param start The starting point of the line.
     * @param end   The ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a line with the specified coordinates.
     *
     * @param x1 The x-coordinate of the starting point.
     * @param y1 The y-coordinate of the starting point.
     * @param x2 The x-coordinate of the ending point.
     * @param y2 The y-coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    /**
     * Calculates the length of the line.
     *
     * @return The length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Calculates the middle point of the line.
     *
     * @return The middle point of the line.
     */
    public Point middle() {
        double midX = (start.getX() + end().getX()) / 2;
        double midY = (start.getY() + end().getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Returns the starting point of the line.
     *
     * @return The starting point of the line.
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the ending point of the line.
     *
     * @return The ending point of the line.
     */
    public Point end() {
        return end;
    }

    /**
     * Calculates the slope of the line.
     *
     * @return The slope of the line.
     */
    public double slope() {
        return ((start.getY() - end.getY()) / (start.getX() - end.getX()));
    }

    /**
     * Determines if the line has a slope (is not vertical).
     *
     * @return true if the line has a slope, false if it is vertical.
     */
    public boolean isSlopeExist() {
        return !(Math.abs(start.getX() - end.getX()) < EPSILON);
    }

    /**
     * Returns the minimum Y coordinate of the line's start and end points.
     *
     * @return The minimum Y value.
     */
    public double minY() {
        return Math.min(this.start.getY(), this.end.getY());
    }

    /**
     * Returns the maximum Y coordinate of the line's start and end points.
     *
     * @return The maximum Y value.
     */
    public double maxY() {
        return Math.max(this.start.getY(), this.end.getY());
    }

    /**
     * Returns the minimum X coordinate of the line's start and end points.
     *
     * @return The minimum X value.
     */
    public double minX() {
        return Math.min(this.start.getX(), this.end.getX());
    }

    /**
     * Returns the maximum X coordinate of the line's start and end points.
     *
     * @return The maximum X value.
     */
    public double maxX() {
        return Math.max(this.start.getX(), this.end.getX());
    }

    /**
     * Determines if a given point lies on the line segment.
     *
     * @param p The point to check.
     * @return true if the point lies on the line segment, false otherwise.
     */
    public boolean isPointOnLine(Point p) {
        return this.minX() - EPSILON <= p.getX() && this.maxX() + EPSILON >= p.getX()
                && this.minY() - EPSILON <= p.getY() && this.maxY() + EPSILON >= p.getY();
    }

    /**
     * Determines if this line intersects with another line.
     *
     * @param other The line to check for intersection with.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        }
        // case 1: both lines are vertical
        if (!this.isSlopeExist() && !other.isSlopeExist()) {
            // checks if the lines are overlapping.
            if (Math.abs(start.getX() - other.start.getX()) < EPSILON) {
                if ((this.maxY() + EPSILON >= other.maxY() && this.minY() - EPSILON <= other.maxY())) {
                    return true;
                }
                return this.maxY() - EPSILON <= other.maxY() && this.maxY() + EPSILON >= other.minY();
            }
            return false;
        }
        double b = this.start.getY() - this.slope() * this.start.getX();
        double d = other.start.getY() - other.slope() * other.start.getX();
        //case 2: the lines have the same slope.
        if (this.slope() == other.slope()) {
            if (Math.abs(b - d) < EPSILON) {
                return (this.maxX() + EPSILON >= other.maxX() && this.minX() - EPSILON <= other.maxX())
                        || (other.maxX() + EPSILON >= this.maxX() && other.minX() - EPSILON <= this.maxX());
            }
        }
        return false;
    }

    /**
     * Determines if this line intersects with two other lines.
     *
     * @param other1 The first line to check for intersection with.
     * @param other2 The second line to check for intersection with.
     * @return true if this line intersects with both other lines, false otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }

    /**
     * Calculates the intersection point with another line, if it exists.
     *
     * @param other The line to calculate the intersection with.
     * @return The intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        double cutPointY;
        double cutPointX;
        //case 1: both lines are vertical to X.
        if (!this.isSlopeExist() && !other.isSlopeExist()
                && Math.abs(this.start.getX() - other.start.getX()) < EPSILON) {
            if (Math.abs(this.minY() - other.maxY()) < EPSILON) {
                return new Point(this.start.getX(), this.minY());
            } else if (Math.abs(this.maxY() - other.minY()) < EPSILON) {
                return new Point(this.start.getX(), this.maxY());
            }
            return null;
        } else if (this.isSlopeExist() && !other.isSlopeExist()) {
            // case 2: our line has slope and the other is vertical line.
            double b = this.start.getY() - this.slope() * this.start.getX();
            cutPointY = this.slope() * other.start.getX() + b;
            Point cutPoint = new Point(other.start.getX(), cutPointY);
            if (this.isPointOnLine(cutPoint) && other.isPointOnLine(cutPoint)) {
                return cutPoint;
            }
            return null;
        } else if (!this.isSlopeExist() && other.isSlopeExist()) {
            // case 3: our line is vertical and the other line has slope.
            double d = other.start.getY() - other.slope() * other.start.getX();
            cutPointY = other.slope() * this.start.getX() + d;
            Point cutPoint = new Point(this.start.getX(), cutPointY);
            if (this.isPointOnLine(cutPoint) && other.isPointOnLine(cutPoint)) {
                return cutPoint;
            }
            return null;
        }
        double b = this.start.getY() - this.slope() * this.start.getX();
        double d = other.start.getY() - other.slope() * other.start.getX();
        // case 4: both lines have slope which is different.
        if (Math.abs(this.slope() - other.slope()) > EPSILON) {
            cutPointX = (d - b) / (this.slope() - other.slope());
            cutPointY = this.slope() * cutPointX + b;
            Point cutPoint = new Point(cutPointX, cutPointY);
            if (this.isPointOnLine(cutPoint) && other.isPointOnLine(cutPoint)) {
                return cutPoint;
            }
            return null;
        }
        //case 5: the lines have the same slope.
        if (Math.abs(b - d) < EPSILON) {
            if (Math.abs(this.maxX() - other.minX()) < EPSILON) {
                cutPointY = this.maxX() * this.slope() + b;
                return new Point(this.maxX(), cutPointY);
            }
            if (Math.abs(this.minX() - other.maxX()) < EPSILON) {
                cutPointY = (this.minX() * this.slope() + b);
                return new Point(this.minX(), cutPointY);
            }
        }
        return null;
    }

    /**
     * Compares this line to another object for equality.
     *
     * @param other The object to compare this line against.
     * @return true if the other object is a Line with the same start and end points; false otherwise.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * this function get a rectangle and returns the closest intersection point to the start of the line.
     * if there is no intersection point, it returns null.
     *
     * @param rect - the rectangle to check intersection with.
     * @return - the closest intersection point to the start of the line.
     */

    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints == null || intersectionPoints.isEmpty()) {
            return null;
        }
        Point closestPoint = intersectionPoints.get(0);
        double closestDistance = start.distance(closestPoint);
        for (Point p : intersectionPoints) {
            double distance = start.distance(p);
            if (distance < closestDistance) {
                closestPoint = p;
                closestDistance = distance;
            }
        }
        return closestPoint;
    }
}

