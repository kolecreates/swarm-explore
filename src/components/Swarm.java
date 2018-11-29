package components;

import java.util.ArrayList;
import java.util.Random;
import utility.Point;

/**
 * Represents a group of agents. Performs only light management such as initializing 
 * agents with a world, map, and position. 
 * @author kole
 * @since Nov 29, 2018
 */
public class Swarm {
    private final World world;
    private final Map map;
    private final Random rng = new Random();
    private ArrayList<Agent> agents;
    /**
     * Create a new swarm with an existing world. A swarm will initialize an internal
     * map that will be used to model what it knows about the world it lives.
     * @param world for which the swarm shall explore
     * @param size the number of agents in the swarm
     */
    public Swarm(World world, int size){
        this.world = world;
        this.map = new Map();
        this.agents = new ArrayList<Agent>();
        Point origin = new Point(0,0);
        for(int i = 0; i < size; i++){
            Point velocity = generateRandomVelocity();
            agents.add(new Agent(world, map, origin, velocity));
        }
    }
    /**
     * Step forward with agent movement and mapping. 
     */
    public void explore(){
        for(int i = 0; i < agents.size(); i++){
            agents.get(i).move();
        }
    }
    /**
     * Decide whether or not the swarm is done exploring.
     * return true if it is
     */
    public boolean finished(){
        return world.isExplored(map);
    }
    /**
     * Get the swarm's internal model of the world.
     * @return map
     */
    public Map getMap(){
        return map;
    }
    public String toString(){
        return "Size: " + agents.size() + " Explored: " + (map.percentExplored()*100) + "%";
    }
    /**
     * Random velocity needed for initializing agents in the swarm.
     * @return point object representing velocity
     */
    private Point generateRandomVelocity(){
        int[] v = { -1, 0, 1 };
        int x = v[rng.nextInt(v.length)];
        int y = v[rng.nextInt(v.length)];
        return new Point(x,y);
    }
}
