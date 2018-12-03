
package agents;

import utility.Point;

/**
 * 
 * @author kole
 * @since Dec 1, 2018
 * Allows for code to more easily use agents of different types.
 */
public interface AgentInterface {
    
    public Point getPosition();
    public Point move();
}
