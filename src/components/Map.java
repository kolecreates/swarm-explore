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
        xPoints = new ArrayList<>();
        yPoints = new ArrayList<>();
        addPoint(new Point(0,0));
    }
    /**
     * Register a point on the map as explored. The size of the map will expand
     * as needed.
     * @param point
     */
    public void addPoint(Point point){
        if(point.getX() >= getWidth()){
            setWidth(point.getX());
        }
        if(point.getY() >= getHeight()){
            setHeight(point.getY());
        }
        if(xPoints.get(point.getX()) == ESTIMATED_POINT){
           xPoints.set(point.getX(), EXPLORED_POINT);
           yPoints.set(point.getY(), EXPLORED_POINT); 
           exploredPointsCount++;
        } 
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
        if(exploredPointsCount == 0){ return 0.0; }
        return (double) exploredPointsCount / (double) (getHeight()*getWidth());
    }
    public boolean isExplored(){
        return exploredPointsCount == (getHeight()*getWidth());
    }
    /**
     * Expand the current x axis of potential explored points.
     * @param x 
     */
    private void setWidth(int x){
        int dx = x + 1 - getWidth();
        for(int i = 0; i < dx; i++){
            xPoints.add(ESTIMATED_POINT);
        }
    }
    /**
     * Expand the current y axis of potential explored points.
     * @param y 
     */
    private void setHeight(int y){
        int dx = y + 1 - getHeight();
        for(int i = 0; i < dx; i++){
            yPoints.add(ESTIMATED_POINT);
        }
    }
}
