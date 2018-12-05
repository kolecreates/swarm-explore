package graphics;

import components.Environment;
import components.ExternalMap;
import components.Swarm;
import components.World;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
    public MainPanel(Point dim, Points moving, Points explored, Statistics stats){
        super.setBackground(Color.WHITE);
        mapPanel = new MapPanel(dim);
        map.track(moving, Color.RED);
        map.track(explored, Color.WHITE);
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
