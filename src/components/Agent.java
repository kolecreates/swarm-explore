package components;

import java.util.Random;
import utility.Point;

/**
 * @author kole
 * @since Nov 29, 2018
 * Represents an autonomous unit in a swarm. Explores a world.
 */
public class Agent {
    private final int ADJUST_VELOCITY_CHANCE = 5;
    private final World world;
    private final Map map;
    private final Point origin;
    private Point position;
    private Point velocity;
    private final Random rng;
    /**
     * Create an instance of an Agent as part of a swarm.
     * @param world to be explored
     * @param map to log explored points on and model the world.
     * @param position initial starting point of the agent
     */
    public Agent(World world, Map map, Point position, Point velocity){
        this.world = world;
        this.map = map;
        this.origin = position;
        this.position = position;
        this.velocity = velocity;
        this.rng = new Random();
    }
    /**
     * Cause the agent to adjust its position by 1 unit. 
     * The agent will automatically log its findings on the map after
     * each movement.
     * @return its new position
     */
    public Point move(){
        adjustVelocity();
        position = position.add(velocity);
        log();
        return position;
    }
    /**
     * Change agents velocity if a border is detected. Otherwise,
     * there's a 5% chance the velocity will be mixed up.
     */
    private void adjustVelocity(){
        int chance = rng.nextInt(100);
        if(chance < ADJUST_VELOCITY_CHANCE){
            velocity = velocity.mixup();
        }
        Point temp = position.add(velocity);
        while(world.outOfBounds(temp)){
            velocity = velocity.mixup();
            temp = position.add(velocity);
        }
    }
    /**
     * Update the map with the agents latest position and border detection.
     */
    private void log(){
        if(world.onBorder(position)){
            if(isCloserToXAxis()){
                map.addBorderPointX(position);
            }else{
                map.addBorderPointY(position);
            }
        }else{
            map.addPoint(position);
        }
    }
    /**
     * Determine if the agent is closer to a x axis border or y axis border.
     * @return true if x axis, false if y axis
     */
    private boolean isCloserToXAxis(){
        Point dist = position.distance(origin);
        return dist.getX() > dist.getY();
    }
}
