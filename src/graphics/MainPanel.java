package graphics;

import components.Environment;
import components.ExternalMap;
import components.Swarm;
import components.World;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import utility.Point;

/**
 * @author kole
 * @since Nov 29, 2018
 * The root level graphics panel that manages all sub panels.
 */
public class MainPanel extends JPanel {
    private final MapPanel mapPanel;
    private final StatsPanel statsPanel;
    public MainPanel(Point dimensions, MovingPointsInterface moving, ExploredPointsInterface explored, StatInterface stats){
        super.setBackground(Color.WHITE);
        mapPanel = new MapPanel(dimensions);
        mapPanel.display(explored, Color.WHITE);
        mapPanel.display(moving, Color.RED);
        statsPanel = new StatsPanel(stats);
        
        super.add(mapPanel, BorderLayout.CENTER);
        super.add(statsPanel, BorderLayout.NORTH);
    }
    public void refresh(){
        statsPanel.updateStats();
        super.revalidate();
        super.repaint();
    }
}
