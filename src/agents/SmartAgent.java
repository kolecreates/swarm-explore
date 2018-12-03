package agents;

import components.Map;
import components.World;
import utility.Point;
/**
 *
 * @author kole
 * @since Dec 1, 2018
 * This agent extends previous agent by avoiding moves to already explored
 * areas of the map.
 */
public class SmartAgent extends Agent {
    /**
     * Initialize the SmartAgent just the same as standard Agent class.
     * @param world
     * @param map
     * @param position 
     */
    public SmartAgent(World world, Map map, Point position){
        super(world, map, position);
    }
    /**
     * Checks which velocity will lead to unexplored areas of the map, and
     * sets it. Picks the last tested velocity if none fit the requirement.
     */
    @Override
    protected void adjustVelocity(){
        int[] velVals = { -1, 0, 1 };
        for(int i = 0; i < velVals.length; i++){
            for(int j = 0; j < velVals.length; j++){
                Point vel = new Point(velVals[i], velVals[j]);
                Point temp = position.add(vel);
                if(world.outOfBounds(temp)){ continue; }
                velocity = vel;
                if(!map.isExplored(temp)){
                    return;
                }
            }
        }
    }
}
