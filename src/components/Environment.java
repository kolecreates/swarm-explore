package components;

import bots.Bot;
import java.util.ArrayList;
import utility.Point;
import utility.Rotation;

/**
 * @author Kole Nunley
 * @since Dec 2, 2018
 * Represents a real-world environment for bots to explore. It differs from World 
 * for Agents in that there are less explicit functions for determining 
 * borders/boundaries & exploration completeness.
 */
public class Environment {
    private final int width;
    private final int height;
    private final ArrayList<Bot> bots;
    private final ArrayList<Point> origins;
    /**
     * initialize a rectangular environment with a height and width
     * @param width
     * @param height 
     * @parma converter for properly detecting bumps
     */
    public Environment(int width, int height){
        this.width = width; 
        this.height = height;
        this.bots = new ArrayList<>();
        this.origins = new ArrayList<>();
    }
    /**
     * Register a bot within the environment.
     * @param bot
     * @param origin 
     */
    public void addBot(Bot bot, Point origin){
        this.bots.add(bot);
        this.origins.add(origin);
    }
    /**
     * Determine if the bot has bumped into an environment element such as 
     * the border.
     * @param bot
     * @return 
     */
    public boolean bump(Bot bot){
        Point position = getBotPosition(bot.id, bot.getPosition());
        final Rotation rot = bot.getRotation();
        final int x = position.getX();
        final int y = position.getY();
        final int a = bot.getBumperAngle()/2;
        return    (x <= 0 && rot.within(a,Rotation.EAST)) 
               || (x >= width && rot.within(a, Rotation.WEST))
               || (y <= 0 && rot.within(a, Rotation.NORTH))
               || (y >= height && rot.within(a, Rotation.SOUTH));
    }
    /**
     * Convert a position received from a bot instance to an absolute position
     * usable by graphics/Environment
     * @param botIndex
     * @param positionFromBot
     * @return 
     */
    public Point getBotPosition(int botIndex, Point positionFromBot){
        Point origin = origins.get(botIndex);
        return positionFromBot.add(origin);
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public Point getDimensions(){
        return new Point(width, height);
    }
}
