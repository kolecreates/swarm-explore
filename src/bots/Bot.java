package bots;

import components.ExternalMap;
import components.Environment;
import utility.Point;
import utility.Rotation;

/**
 * @author Kole Nunley
 * @since Dec 2, 2018
 * Attempts to represent a physical robot for potential future transfer from
 * simulation to real world test. Main differences: the robot runs its own 
 * clock, adjusting its movement without a higher swarm controller telling it to;
 * The robot shares a map with other robots via an external database;
 * Robots determine their position based on compass readings and measuring
 * distance traveled in each direction.
 */
public class Bot implements Runnable  {
    public final int id;
    private final Environment env;
    private final ExternalMap map;
    private final int STEP_TIMEOUT_MS;
    private Point position;
    private Rotation rotation;
    private Point velocity;
    /**
     * Initialize the bot with reference to the environment and external map.
     * Bots start with rotation toward the south and position of 0,0. Note that
     * the bots internal position variable will always be initialized at a 
     * relative origin. In other words, the origin from the bots perspective is 
     * wherever in the environment it started.
     * @param id based on the order from which it was initialized in the swarm
     * @param env
     * @param map 
     */
    public Bot(int id, Environment env, ExternalMap map, int STEP_TIMEOUT_MS){
        this.id = id;
        this.env = env;
        this.map = map;
        this.rotation = Rotation.SOUTH;
        this.position = new Point(0,0);
        this.velocity = new Point(1,1);
        this.STEP_TIMEOUT_MS = STEP_TIMEOUT_MS;
    }
    /**
     * Begin moving around and exploring the environment. Overrides
     * Runnable run function. Meaning that this class can be passed as an
     * argument to a new Thread object.
     */
    @Override
    public void run(){
        while(!map.isExplored()){
            while(env.bump(this)){
                map.log(this, true);
                rotation = rotation.rotate(45);
            }
            position = position.add(velocity.mult(rotation.toPoint()));
            map.log(this, false);
            try{
                Thread.sleep(STEP_TIMEOUT_MS);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
    /**
     * The bumper angle is the degrees of which the arc of the bots bumper
     * spans.
     * @return 
     */
    public int getBumperAngle(){
        return 90;
    }
    public Point getPosition(){
        return position;
    }
    public Rotation getRotation(){
        return rotation;
    }
    public Point getVelocity(){
        return velocity;
    }
    
}
