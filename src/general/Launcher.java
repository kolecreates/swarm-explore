package general;

import components.Swarm;
import components.World;

/**
 * @author kole
 * @since Nov 29, 2018
 * Acts as the entry point to the program. 
 */
public class Launcher {
    static final int STEP_SPEED_MS = 50;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        World world = new World(25, 25);
        Swarm swarm = new Swarm(world, 2);
        int steps = 0;
        while(!swarm.finished()){
            swarm.explore();
            if(steps % 100 == 0){ System.out.println(swarm.toString()); }
            steps++;
            try{
                Thread.sleep(STEP_SPEED_MS);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
        
    }
}
