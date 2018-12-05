package components;

import agents.Agent;
import agents.AgentInterface;
import agents.SmartAgent;
import java.util.ArrayList;
import utility.Point;
import graphics.MovingPointsInterface;

/**
 * Represents a group of agents. Performs only light management such as initializing 
 * agents with a world, map, and position. 
 * @author kole
 * @since Nov 29, 2018
 */
public class Swarm implements MovingPointsInterface {
    private final World world;
    private final Map map;
    private ArrayList<AgentInterface> agents;
    /**
     * Create a new swarm with an existing world. A swarm will initialize an internal
     * map that will be used to model what it knows about the world it lives.
     * @param world for which the swarm shall explore
     * @param size the number of agents in the swarm
     */
    public Swarm(World world, int size, boolean smart){
        this.world = world;
        this.map = new Map();
        this.agents = new ArrayList<AgentInterface>();
        Point origin = new Point(0,0);
        if(smart){
          for(int i = 0; i < size; i++){
            agents.add(new SmartAgent(world, map, origin));
          }  
        }else {
            for(int i = 0; i < size; i++){
                agents.add(new Agent(world, map, origin));
            }
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
    /**
     * Get the agents of this swarm
     * @return arraylist of agent objects
     */
    public ArrayList<AgentInterface> getAgents(){
        return agents;
    }
    public ArrayList<Point> getAgentPositions(){
        ArrayList<Point> positions = new ArrayList<>();
        for(int i = 0; i < agents.size(); i++){
            positions.add(agents.get(i).getPosition());
        }
        return positions;
    }
    @Override
    public ArrayList<Point> getMovingPoints() {
        return getAgentPositions();
    }
    public String toString(){
        StringBuilder bldr = new StringBuilder();
        bldr.append("Size: ");
        bldr.append(agents.size());
        bldr.append(" Explored: ");
        bldr.append((map.percentExplored()*100));
        bldr.append("%");
        bldr.append(" Efficiency: ");
        bldr.append((map.getEfficiency()*100));
        bldr.append("%");
        return bldr.toString();
    }
    
    
}
