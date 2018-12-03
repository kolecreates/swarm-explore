package general;

import components.Swarm;
import components.World;
import graphics.MainPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * @author kole
 * @since Nov 29, 2018
 * Acts as the entry point to the program. 
 */
public class Launcher {
    static final int FRAME_RATE = 50;
    static final int STEPS_PER_FRAME = 2;
    static final int AGENT_COUNT = 5;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        World world = new World(100, 100);
        Swarm swarm = new Swarm(world, AGENT_COUNT, true);
        int steps = 0;
        MainPanel mainPanel = createUI(world, swarm);
        while(!swarm.finished()){
            for(int i = 0; i < STEPS_PER_FRAME; i++){    
                swarm.explore();
                mainPanel.refresh();
                steps++;   
            }
            try{
                Thread.sleep(FRAME_RATE);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    private static MainPanel createUI(World world, Swarm swarm){
        JFrame mainFrame = new JFrame("SwarmExplore");
        mainFrame.setBackground(Color.WHITE);
        mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(600,600));
        MainPanel mainPanel = new MainPanel(world, swarm);
        mainFrame.getContentPane().add(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
        return mainPanel;
    }
}
