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
    private ExploredPointsInterface exploredInterface;
    private MovingPointsInterface movingInterface;
    private Color exploredColor;
    private Color movingColor;
    private Point max;
    private Point min;
    /**
     * @param dimension point object that represents the wxh in scale units. pixels = units*MapPanel.SCALE
     */
    public MapPanel(Point dimension){
        super.setPreferredSize(new Dimension((dimension.getX()+1)*SCALE, (dimension.getY()+1)*SCALE));
        super.setBackground(Color.BLACK);
        max = new Point(0,0);
        min = new Point(dimension.getX(),dimension.getY());
    }
    public void display(ExploredPointsInterface pointInterface, Color color){
        exploredInterface = pointInterface;
        exploredColor = color;
    } 
    public void display(MovingPointsInterface pointInterface, Color color){
        movingInterface = pointInterface;
        movingColor = color;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        for(int i = min.getX(); i < max.getX(); i++){
            for(int j = min.getY(); j < max.getY(); j++){
                g.drawRect(i*SCALE, j*SCALE, SCALE, SCALE);
            }
        }
        g.setColor(exploredColor);
        ArrayList<Point> ePoints = exploredInterface.getExploredPoints();
        for(int j = 0; j < ePoints.size(); j++){
            Point p = ePoints.get(j);
            updateMinMax(p);
            g.fillRect(p.getX()*SCALE, p.getY()*SCALE, SCALE, SCALE);
        }
        g.setColor(movingColor);
        ArrayList<Point> mPoints = movingInterface.getMovingPoints();
        for(int j = 0; j < mPoints.size(); j++){
            Point p = mPoints.get(j);
            updateMinMax(p);
            g.fillRect(p.getX()*SCALE, p.getY()*SCALE, SCALE, SCALE);
        }
    }
    private void updateMinMax(Point point){
        if(point.getX() > max.getX()){ max = new Point(point.getX(), max.getY()); }
        if(point.getY() > max.getY()){ max = new Point(max.getX(), point.getY()); }
        if(point.getX() < min.getX()){ min = new Point(point.getX(), min.getY()); }
        if(point.getY() < min.getY()){ min = new Point(min.getX(), point.getY()); }
    }
}
