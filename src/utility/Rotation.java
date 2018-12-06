package utility;

/**
 * @author Kole Nunley
 * @since Dec 2, 2018
 * Represents immutable 2D rotation as a double type point and provides 
 * a suite of utility functions.
 */
public class Rotation {
    public static final Rotation EAST = new Rotation(1.0, 0.0 );
    public static final Rotation WEST = new Rotation(-1.0, 0.0 );
    public static final Rotation NORTH = new Rotation(0.0, -1.0 );
    public static final Rotation SOUTH = new Rotation(0.0, 1.0 );
    private final double x;
    private final double y;
    public Rotation(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    /**
     * Get a new rotation by rotating this one by the given angle in degrees.
     * Source: https://stackoverflow.com/a/17411276
     * @param angle
     * @return 
     */
    public Rotation rotate(double angle){
        double radians = (Math.PI / 180) * angle;
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double nx = (cos * x) + (sin * y);
        double ny = (cos * y) - (sin * x);
        return new Rotation(nx, ny);
    }
    public boolean equal(Rotation other){
        return x == other.getX() && y == other.getY();
    }
    /**
     * Is rotation within n degrees of rot
     * @param angle
     * @param rot
     * @return 
     */
    public boolean within(int angle, Rotation rot){
        return Math.abs(rot.getDegrees() - this.getDegrees()) <= angle;
    }
    /**
     * Get the angle representation of this rotation in degrees.
     * @return 
     */
    public double getDegrees(){
        return Math.toDegrees(Math.atan2(x, y)) % 360;
    }
    public Point toPoint(){
        return new Point((int) x, (int) y);
    }
}
