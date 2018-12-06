package components;

import graphics.ExploredPointsInterface;
import graphics.MovingPointsInterface;
import java.util.ArrayList;
import utility.Point;

public class PointSystemConverter implements ExploredPointsInterface, MovingPointsInterface {
    Environment env;
    ExternalMap map;
    public PointSystemConverter(Environment env, ExternalMap map){
        this.env = env;
        this.map = map;
    }

    @Override
    public ArrayList<Point> getExploredPoints() {
        ArrayList<ArrayList<Integer>> data = map.getExploredPoints();
        ArrayList<Point> points = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            ArrayList<Integer> ints = data.get(i);
            Point point = env.getBotPosition(ints.get(0), new Point(ints.get(1), ints.get(2)));
            points.add(point);
        }
        return points;
    }

    @Override
    public ArrayList<Point> getMovingPoints() {
        ArrayList<Point> points = map.getMovingPoints();
        for(int i = 0; i < points.size(); i++){
            points.set(i, env.getBotPosition(i, points.get(i)));
        }
        return points;
    }
}
