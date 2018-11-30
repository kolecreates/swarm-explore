package general;

import components.Map;
import components.Swarm;
import components.World;
import graphics.MainPanel;
import graphics.MapPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * @author kole
 * @since Nov 29, 2018
 * Acts as the entry point to the program. 
 */
public class Launcher {
    static final int LOOP_SPEED_MS = 18;
    static final int STEPS_PER_LOOP = 3;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        World world = new World(100, 100);
        Swarm swarm = new Swarm(world, 10);
        int steps = 0;
        MainPanel mainPanel = createUI(world, swarm);
        while(!swarm.finished()){
            for(int i = 0; i < STEPS_PER_LOOP; i++){    
                swarm.explore();
                mainPanel.revalidate();
                mainPanel.repaint();
                if(steps % 200 == 0){ System.out.println(swarm.toString()); }
                steps++;   
            }
            try{
                Thread.sleep(LOOP_SPEED_MS);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    private static MainPanel createUI(World world, Swarm swarm){
        JFrame mainFrame = new JFrame("Group Project - CS410");
        mainFrame.setBackground(Color.GRAY);
        mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(200,200));
        MainPanel mainPanel = new MainPanel(world, swarm);
        mainFrame.getContentPane().add(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
        return mainPanel;
    }
}
