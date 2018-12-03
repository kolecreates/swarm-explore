package graphics;

import components.Swarm;
import components.World;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * @author kole
 * @since Nov 29, 2018
 * The root level graphics panel that manages all sub panels.
 */
public class MainPanel extends JPanel {
    private final MapPanel mapPanel;
    private final StatsPanel statsPanel;
    public MainPanel(World world, Swarm swarm){
        super.setBackground(Color.WHITE);
        mapPanel = new MapPanel(world.getWidth(), world.getHeight(), swarm.getMap());
        mapPanel.displaySwarm(swarm);
        statsPanel = new StatsPanel(swarm.getMap());
        
        super.add(mapPanel, BorderLayout.CENTER);
        super.add(statsPanel, BorderLayout.NORTH);
    }
    
    public void refresh(){
        statsPanel.updateStats();
        super.revalidate();
        super.repaint();
    }
}
