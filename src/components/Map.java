package components;

import java.util.ArrayList;
import utility.Point;

/**
 * Represents the explored area of a world.
 * @author kole
 * @since Nov 29, 2018
 */
public class Map {
    static final int ESTIMATED_POINT = 0;
    static final int EXPLORED_POINT = 1;
    private int exploredPointsCount = 0;
    private boolean xMaxed = false;
    private boolean yMaxed = false;
    private ArrayList<Integer> xPoints;
    private ArrayList<Integer> yPoints;
    /**
     * Initialize a new map. Maps start as a single point in space.
     */
    public Map(){
        addPoint(new Point(1,1));
    }
    /**
     * Register a point on the map as explored. The size of the map will expand
     * as needed.
     * @param point
     */
    public void addPoint(Point point){
        if(point.getX() > getWidth()){
            setWidth(point.getX());
        }
        if(point.getY() > getHeight()){
            setHeight(point.getY());
        }
        xPoints.set(point.getX(), EXPLORED_POINT);
        yPoints.set(point.getY(), EXPLORED_POINT);
        exploredPointsCount++;
    }
    /**
     * Register a point on the left or right border.
     * @param point
     */
    public void addBorderPointX(Point point){
       addPoint(point);
       xMaxed = point.getX() > 0;
    }
    /**
     * Register a point on the top or bottom border.
     * @param point
     */
    public void addBorderPointY(Point point){
       addPoint(point);
       yMaxed = point.getY() > 0;
    }
    /**
     * Get the width of the map. Each point may not be explored, but this is
     * the minimum width the World can be considering the points that are explored.
     * @return width
     */
    public int getWidth(){
        return xPoints.size();
    }
    /**
     * Get the height of the map. Each point may not be explored, but this is
     * the minimum height the World can be considering the points that are explored.
     * @return height
     */
    public int getHeight(){
        return yPoints.size();
    }
    /**
     * Get the percentage of the current known World size that is explored.
     * @return double
     */
    public double percentExplored(){
        return (double) exploredPointsCount / (double) (getHeight()*getWidth());
    }
    /**
     * Expand the current x axis of potential explored points.
     * @param x 
     */
    private void setWidth(int x){
        int dx = x - getWidth();
        for(int i = 0; i < dx; i++){
            xPoints.add(ESTIMATED_POINT);
        }
    }
    /**
     * Expand the current y axis of potential explored points.
     * @param y 
     */
    private void setHeight(int y){
        int dx = y - getHeight();
        for(int i = 0; i < dx; i++){
            yPoints.add(ESTIMATED_POINT);
        }
    }
}
