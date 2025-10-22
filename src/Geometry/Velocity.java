// 211699665 Ilai Pingle

package Geometry;

/**
 * Velocity class.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     *
     * @param dx - the change in position on the x-axis.
     * @param dy - the change in position on the y-axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return dx- the change in position on the x-axis.
     */
    public double getDx() {
        return dx;
    }

    /**
     * @return dy- the change in position on the y-axis.
     */
    public double getDy() {
        return dy;
    }
    /**
     * Set the change in position on the x-axis.
     * @param dx - the change in position on the x-axis.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Set the change in position on the y-axis.
     * @param dy - the change in position on the y-axis.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Take an angle and a speed and returns the velocity.
     *
     * @param angle - the angle of the velocity.
     * @param speed - the speed of the velocity.
     * @return Velocity - the velocity by the given angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleInRadians = Math.toRadians(angle);
        double dx = speed * Math.sin(angleInRadians);
        double dy = -speed * Math.cos(angleInRadians);
        return new Velocity(dx, dy);
    }

    /**
     * Apply the velocity to a given point.
     *
     * @param p - the point to apply the velocity to.
     * @return Point - the new point after the velocity applied.
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + dx;
        double newY = p.getY() + dy;
        return new Point(newX, newY);
    }

    /**
     * Returns the length of the trajectory.
     *
     * @return double - the length of the trajectory.
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }
}
