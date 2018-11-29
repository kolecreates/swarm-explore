package components;

import utility.Point;

/**
 * @author kole
 * @since Nov 29, 2018
 * Represents a two dimensional space that the Swarm will explore.
 */
public class World {
    private int width;
    private int height;
    /**
     * Create a new world instance.
     * @param width the length of the world x-axis
     * @param height the length of the world y-axis
     */
    public World(int width, int height){
        this.width = width;
        this.height = height;
    }
    /**
     * Checks if the passed point is a border point in the world.
     * @param x axis coordinate 
     * @param y axis coordinate
     * @return boolean
     */
    public boolean atBorder(Point point){
        return point.onAxis() || point.getX() == width || point.getY() == height ;
    }
}
