package graphics;

import agents.AgentInterface;
import agents.SmartAgent;
import components.Map;
import components.Swarm;
import components.World;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import utility.Point;

/**
 * Graphically representation of a swarms internal map of the world.
 * @author kole
 * @since Nov 29, 2018
 */
public class MapPanel extends JPanel {
    public static final int SCALE = 5;
    private final Map map;
    private Swarm swarm;
    private ArrayList<AgentInterface> agents;
    private boolean drawAgents = false;
    /**
     * 
     * @param width in scale units. pixels = units*MapPanel.SCALE
     * @param height in scale units.
     * @param map 
     */
    public MapPanel(int width, int height, Map map){
        this.map = map;
        super.setPreferredSize(new Dimension(width*SCALE, height*SCALE));
        super.setBackground(Color.BLACK);
    }
    public void displaySwarm(Swarm swarm){
        this.swarm = swarm;
        this.agents = swarm.getAgents();
        drawAgents = true;
    } 
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ArrayList<ArrayList<Integer>> points = map.getPoints();
        for(int i = 0; i < points.size(); i++){
            for(int j = 0; j < points.get(i).size(); j++){
                if(points.get(i).get(j) == Map.EXPLORED_POINT){
                    g.setColor(Color.WHITE);
                    g.fillRect(i*SCALE, j*SCALE, SCALE, SCALE);
                }else {
                    g.setColor(Color.BLUE);
                    g.drawRect(i*SCALE, j*SCALE, SCALE, SCALE);
                }
                
            }
        }
        if(drawAgents){
            g.setColor(Color.red);
            for(int i = 0; i < agents.size(); i++){
                Point pos = agents.get(i).getPosition();
                g.fillOval(pos.getX()*SCALE, pos.getY()*SCALE, SCALE, SCALE);
            }
        }
    }
}
