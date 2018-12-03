package components;
import bots.Bot;
import utility.Point;
import utility.Rotation;
import utility.SQLClient;
/**
 * @author Kole Nunley
 * @since Dec 2, 2018
 * Represents the shared map constructed by bots and stored in an external database. 
 */
public class ExternalMap {
    private final SQLClient client;
    private final int id;
    public ExternalMap(int id){
        this.id = id;
        this.client = new SQLClient();
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
        str.append(r.getY()); str.append(",");
        str.append(bump); 
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
        return false;
    }
}
