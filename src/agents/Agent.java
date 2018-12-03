package agents;

import components.Map;
import components.World;
import java.util.Random;
import utility.Point;

/**
 * @author kole
 * @since Nov 29, 2018
 * Represents an autonomous unit in a swarm. Explores a world.
 */
public class Agent implements AgentInterface {
    protected final int ADJUST_VELOCITY_CHANCE = 5;
    protected final World world;
    protected final Map map;
    protected final Point origin;
    protected Point position;
    protected Point velocity;
    protected final Random rng;
    /**
     * Create an instance of an Agent as part of a swarm.
     * @param world to be explored
     * @param map to log explored points on and model the world.
     * @param position initial starting point of the agent
     */
    public Agent(World world, Map map, Point position){
        this.rng = new Random();
        this.world = world;
        this.map = map;
        this.origin = position;
        this.position = position;
        adjustVelocity();
    }
    /**
     * Cause the agent to adjust its position by 1 unit. 
     * The agent will automatically log its findings on the map after
     * each movement.
     * @return its new position
     */
    @Override
    public Point move(){
        adjustVelocity();
        position = position.add(velocity);
        log();
        return position;
    }
    /**
     * access this agent's current position as a Point object
     * @return 
     */
    @Override
    public Point getPosition(){
        return position;
    }
    /**
     * Change agents velocity after each move.
     */
    protected void adjustVelocity(){
        int chance = rng.nextInt(100);
        if(chance < ADJUST_VELOCITY_CHANCE){
            velocity = generateRandomVelocity();
        }
        Point temp = position.add(velocity);
        while(world.outOfBounds(temp)){
            velocity = generateRandomVelocity();
            temp = position.add(velocity);
        }
    }
    /**
     * Update the map with the agents latest position and border detection.
     */
    protected void log(){
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
    
    /**
     * Random velocity needed for random mixup and initialization.
     * Recursively generate to prevent 0,0 vectors.
     * @return point object representing velocity
     */
    protected Point generateRandomVelocity(){
        int[] v = { -1, 0, 1 };
        int x = v[rng.nextInt(v.length)];
        int y = v[rng.nextInt(v.length)];
        if(x == 0 && y == 0){ return generateRandomVelocity(); }
        return new Point(x,y);
    }
}
