// 211699665 Ilai Pingle

package Geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a rectangle in 2D space defined by an upper left point, width and height.
 */
public class Rectangle {
    private Point upperLeft;
    private final double width;
    private final double height;
    private static final double EPSILON = 0.0000001;

    /**
     * constructor.
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }


    /**
     * returns the width of the rectangle.
     *
     * @return width : the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * returns the height of the rectangle.
     *
     * @return height : the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * returns the upper left point of the rectangle.
     *
     * @return upperLeft : the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Sets the upper left point of the rectangle.
     *
     * @param newUpperLeft The new upper left point of the rectangle.
     */
    public void setUpperLeft(Point newUpperLeft) {
        this.upperLeft = newUpperLeft;
    }

    /**
     * Returns a list of intersection points with a given line.
     *
     * @param line The line to check for intersection points with.
     * @return A list of intersection points with the given line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> cutPoints = new ArrayList<>();
        Line[] rectangleSides = getLines();

        for (Line l : rectangleSides) {
            if (l.isIntersecting(line)) {
                Point p = l.intersectionWith(line);
                if (p != null) { // if the lines are not overlapping
                    cutPoints.add(p);
                }
            }
        }
        if (cutPoints.isEmpty()) {
            return null;
        }
        return cutPoints;
    }

    private Line[] getLines() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point lowerRight = new Point(upperRight.getX(), lowerLeft.getY());

        return new Line[]{new Line(upperLeft, upperRight),  // Top side
                new Line(upperLeft, lowerLeft),   // Left side
                new Line(lowerLeft, lowerRight),  // Bottom side
                new Line(upperRight, lowerRight)  // Right side
        };
    }

    /**
     * Compares this rectangle to another rectangle for equality.
     *
     * @param other The object to compare this rectangle against.
     * @return true if the other object is a Rectangle with the same upper left point, width and height.
     * false otherwise.
     */
    public boolean equals(Rectangle other) {
        boolean upperLeftEquals = this.upperLeft.equals(other.getUpperLeft());
        boolean widthEquals = Math.abs(this.width - other.getWidth()) < EPSILON;
        boolean heightEquals = Math.abs(this.height - other.getHeight()) < EPSILON;
        return upperLeftEquals && widthEquals && heightEquals;
    }
}

