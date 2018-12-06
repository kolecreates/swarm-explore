package general;

import bots.Bot;
import components.Environment;
import components.ExternalMap;
import components.PointSystemConverter;
import components.Swarm;
import components.World;
import graphics.MainPanel;
import graphics.StatInterface;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import utility.Point;
import graphics.ExploredPointsInterface;
import graphics.MovingPointsInterface;

/**
 * @author kole
 * @since Nov 29, 2018
 * Acts as the entry point to the program. 
 */
public class Launcher {
    static final int FRAME_RATE = 50;
    static final int STEPS_PER_FRAME = 2;
    static final int AGENT_COUNT = 5;
    static final int BOT_COUNT = 5;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        botSwarm();
    }
    private static void botSwarm(){
        Environment env = new Environment(100,100);
        ExternalMap externalMap = new ExternalMap(1);
        PointSystemConverter conv = new PointSystemConverter(env, externalMap);
        Point origin = new Point(50,50);
        MainPanel mainPanel = createUI(env.getDimensions(),  conv, conv, externalMap);
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i < BOT_COUNT; i++){
            Bot bot = new Bot(i, env, externalMap, FRAME_RATE);
            env.addBot(bot, origin);            
            Thread thread = new Thread(bot);
            threads.add(thread);
            thread.start();
        }
        while(true){
            mainPanel.refresh();
            try{
                Thread.sleep(FRAME_RATE);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    private static void agentSwarm(){
        World world = new World(100, 100);
        Swarm swarm = new Swarm(world, AGENT_COUNT, true);
        int steps = 0;
        MainPanel mainPanel = createUI(world.getDimensions(), swarm, swarm.getMap(), swarm.getMap());
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
    /**
     * Setup the program graphics window with a JFrame. Initializes the MainPanel
     * with the passed world and swarm.
     * @param world
     * @param swarm
     * @return 
     */
    private static MainPanel createUI(Point innerDimension, MovingPointsInterface moving, ExploredPointsInterface explored, StatInterface stats){
        JFrame mainFrame = new JFrame("SwarmExplore");
        mainFrame.setBackground(Color.WHITE);
        mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(600,600));
        MainPanel mainPanel = new MainPanel(innerDimension, moving, explored, stats);
        mainFrame.getContentPane().add(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
        return mainPanel;
    }
}
