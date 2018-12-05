package components;
import bots.Bot;
import graphics.StatInterface;
import java.util.ArrayList;
import utility.Point;
import utility.Rotation;
import utility.SQLClient;
import graphics.ExploredPointsInterface;
import graphics.MovingPointsInterface;
/**
 * @author Kole Nunley
 * @since Dec 2, 2018
 * Represents the shared map constructed by bots and stored in an external database. 
 */
public class ExternalMap implements StatInterface, ExploredPointsInterface, MovingPointsInterface {
    private final SQLClient client;
    private final int id;
    private int width = 0;
    private int height = 0;
    private int exploredPoints = 0;
    private int totalSteps = 0;
    public ExternalMap(int id){
        this.id = id;
        this.client = new SQLClient();
        this.client.write("INSERT INTO map VALUES(" + id + "," + width + "," + height + ")");
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return width;
    }
    @Override
    public double percentExplored(){
        return (double) exploredPoints / (double)(width*height);
    }
    @Override
    public double getEfficiency(){
        return (double) exploredPoints / (double) totalSteps;
    }
    @Override
    public ArrayList<Point> getExploredPoints(){
        ArrayList<ArrayList<Integer>> list =  client.readInts("SELECT * FROM points WHERE mapId="+id, 2, 3);
        ArrayList<Point> points = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            ArrayList<Integer> row = list.get(i);
            points.add(new Point(row.get(0), row.get(1)));
        }
        return points;
    }
    @Override
    public ArrayList<Point> getMovingPoints() {
        ArrayList<ArrayList<Integer>> list =  client.readInts("SELECT * FROM bots", 2, 3);
        ArrayList<Point> points = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            ArrayList<Integer> row = list.get(i);
            points.add(new Point(row.get(0), row.get(1)));
        }
        return points;
    }
    /**
     * Record a bots current state on the external map. 
     * @param bot instance of the bot state
     * @param bump is true if bot detected a bump in the environment
     */
    public void log(Bot bot, boolean bump){
        Point p = bot.getPosition();
        Point v = bot.getVelocity();
        Rotation r = bot.getRotation();
        StringBuilder str = new StringBuilder();
        str.append("INSERT INTO bots VALUES (");
        str.append(bot.id); str.append(",");
        str.append(p.getX()); str.append(",");
        str.append(p.getY()); str.append(",");
        str.append(v.getX()); str.append(",");
        str.append(v.getY()); str.append(",");
        str.append(r.getX()); str.append(",");
        str.append(r.getY()); 
        str.append(")");
        str.append("ON DUPLICATE KEY UPDATE");
        str.append("posx="); str.append(p.getX()); str.append(",");
        str.append("posy="); str.append(p.getY()); str.append(",");
        str.append("velx="); str.append(v.getX()); str.append(",");
        str.append("vely="); str.append(v.getY()); str.append(",");
        str.append("rotx="); str.append(r.getX()); str.append(",");
        str.append("roty="); str.append(r.getY());
        client.write(str.toString());
        client.write("UPDATE bots set step = step + 1 WHERE id=" + bot.id);
        
        str = new StringBuilder();
        str.append("INSERT INTO points VALUES (");
        str.append(id); str.append(",");
        str.append(p.getX()); str.append(",");
        str.append(p.getY());
        str.append(bump);
        str.append(")");
        str.append("WHERE not exists (SELECT * FROM points WHERE x=");
        str.append(p.getX());
        str.append(" AND y=");
        str.append(p.getY());
        str.append(")");
        client.write(str.toString());
    }
    /**
     * Determine if the known map space has been completely explored.
     * This is done by checking if all predicted environment space has been explored,
     * and the predicted map space is no longer expanding because no new space was found.
     * @return boolean
     */
    public boolean isExplored(){
        exploredPoints = client.readInt("SELECT COUNT(*) FROM points WHERE mapId="+id);
        totalSteps = client.readInt("SELECT SUM(step) FROM bots");
        int xMax = client.readInt("SELECT MAX(x) FROM points");
        int xMin = client.readInt("SELECT MIN(x) FROM points");
        int yMax = client.readInt("SELECT MAX(y) FROM points");
        int yMin = client.readInt("SELECT MIN(y) FROM points");
        width = xMax - xMin;
        height = yMax - yMin;
        StringBuilder str = new StringBuilder();
        str.append("UPDATE map width=");
        str.append(width);
        str.append(", height=");
        str.append(height);
        str.append(" WHERE id=");
        str.append(id);
        client.write(str.toString());
        return exploredPoints == (width*height);
    }
}
