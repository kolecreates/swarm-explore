package utility;
/**
 * Basic immutable 2 dimension point representation.
 * @author kole
 * @since November 29, 2018
 */
public class Point {
    private int x;
    private int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Point subtract(Point point){
        return new Point(x - point.getX(), y - point.getY());
    }
    public Point add(Point point){
        return new Point(x + point.getX(), y + point.getY());
    }
    public Point distance(Point point){
        return this.subtract(point).absolute();
    }
    public Point absolute(){
        return new Point(Math.abs(x), Math.abs(y));
    }
    public Point negative(){
        return new Point(-x, -y);
    }
    public Point copy(){
        return new Point(x, y);
    }
    public boolean isOrigin(){
        return x == 0 && y == 0;
    }
    public boolean onAxis(){
        return x == 0 || y == 0;
    }
}
