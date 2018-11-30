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
 */
public class MainPanel extends JPanel {
    public MainPanel(World world, Swarm swarm){
        super.setBackground(Color.WHITE);
        MapPanel mapPanel = new MapPanel(swarm.getMap());
        mapPanel.setPreferredSize(new Dimension(world.getWidth(), world.getHeight()));
        super.add(mapPanel, BorderLayout.CENTER);
    }
}
