package graphics;

import components.Map;
import components.World;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Graphically representation of a swarms internal map of the world.
 * @author kole
 * @since Nov 29, 2018
 */
public class MapPanel extends JPanel {
    private final Map map;
    public MapPanel(Map map){
        this.map = map;
        super.setBackground(Color.BLACK);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ArrayList<ArrayList<Integer>> points = map.getPoints();
        for(int i = 0; i < points.size(); i++){
            for(int j = 0; j < points.get(i).size(); j++){
                if(points.get(i).get(j) == Map.EXPLORED_POINT){
                    g.setColor(Color.WHITE);
                }else {
                    g.setColor(Color.BLUE);
                }
                g.drawRect(i, j, 1, 1);
            }
        }

    }
}
