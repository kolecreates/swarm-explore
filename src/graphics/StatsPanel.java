package graphics;

import components.Map;
import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author kole
 * @since Nov 30, 2019
 * Displays various statistics about the simulation.
 */
public class StatsPanel extends JPanel {
    private final Map map;
    private final JTextArea exploredStat;
    private final JTextArea efficiencyStat;
    public StatsPanel(Map map){
        this.map = map;
        exploredStat = new JTextArea();
        efficiencyStat = new JTextArea();
        super.add(exploredStat, BorderLayout.EAST);
        super.add(efficiencyStat, BorderLayout.WEST);
    }
    public void updateStats(){
        String formattedExp = Double.toString(map.percentExplored()*100).substring(0, 3);
        String formattedEff = Double.toString(map.getEfficiency()*100).substring(0, 3);
        exploredStat.setText("Explored: " + formattedExp + "%");
        efficiencyStat.setText("Effficiency: " + formattedEff + "%");
    }
    
}
