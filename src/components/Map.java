package components;

import java.util.ArrayList;
import utility.Point;

/**
 * Represents the explored area of a world.
 * @author kole
 * @since Nov 29, 2018
 */
public class Map {
    public static final int ESTIMATED_POINT = 0;
    public static final int EXPLORED_POINT = 1;
    private int exploredPointsCount = 0;
    private int width = 0;
    private int height = 0;
    private boolean xMaxed = false;
    private boolean yMaxed = false;
    private ArrayList<ArrayList<Integer>> points;
    /**
     * Initialize a new map. Maps start as a single point in space.
     */
    public Map(){
        points = new ArrayList<ArrayList<Integer>>();
    }
    /**
     * Register a point on the map as explored. The size of the map will expand
     * as needed.
     * @param point
     */
    public void addPoint(Point point){
        if(point.getX() >= width){
            expandX(point.getX()+1);
        }
        if(point.getY() >= height){
            expandY(point.getY()+1);
        }
        if(points.get(point.getX()).get(point.getY()) == ESTIMATED_POINT){
           ArrayList<Integer> temp = points.get(point.getX());
           temp.set(point.getY(), EXPLORED_POINT);
           points.set(point.getX(), temp);
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
        return width;
    }
    /**
     * Get the height of the map. Each point may not be explored, but this is
     * the minimum height the World can be considering the points that are explored.
     * @return height
     */
    public int getHeight(){
        return height;
    }
    /**
     * Access the raw points of this map
     * @return 
     */
    public ArrayList<ArrayList<Integer>> getPoints(){
        return points;
    } 
    /**
     * Get the percentage of the current known World size that is explored.
     * @return double
     */
    public double percentExplored(){
        if(exploredPointsCount == 0){ return 0.0; }
        return (double) exploredPointsCount / (double) (width*height);
    }
    /**
     * Determine if the number of explored points is the same as the number of 
     * current points on the map.
     * @return 
     */
    public boolean isExplored(){
        return exploredPointsCount == (getHeight()*getWidth());
    }
    /**
     * Increase the width of the map to the specified integer value.
     * @param x width to expand to 
     */
    private void expandX(int x){
        for(int i = 0; i < x-width; i++){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for(int j = 0; j < height; j++){
                temp.add(ESTIMATED_POINT);
            }
            points.add(temp);
        }
        width = x;
    }
    /**
     * Increase the height of the map to the specified integer value.
     * @param y height to expand to 
     */
    private void expandY(int y){
        for(int i = 0; i < points.size(); i++){
            ArrayList<Integer> temp = points.get(i);
            for(int j = 0; j < y - height; j++){
                temp.add(ESTIMATED_POINT);
            }
            points.set(i, temp);
        }
        height = y;
    }
}
