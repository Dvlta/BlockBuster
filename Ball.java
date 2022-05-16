import java.awt.Color;
import java.util.ArrayList;

/**
 * 
 */
public class Ball
{
    private static final int radius = 12;
    private static int stopped = 0;
    private int speedX;
    private int speedY;
    private int x; 
    private int y;
    private int nextX;
    private int nextY;
    private static Color color = new Color(255, 255, 255);
    
    /**
     * creates a Ball object with a launch angle and a center
     * @param angle launch angle
     * @param posX x-coordinate of the center
     * @param posY y-coordinate of the center
     */
    public Ball(double angle, int posX)
    {
        speedX = (int)(5 * Math.cos(Math.atan(angle)));
        speedY = (int)(5 * Math.sin(Math.atan(angle)));
        x = posX;
        y = 870;
    }

    /**
     * updates the x and y components of speed 
     * based on the launch angle
     * @param angle launch angle
     */
    public void changeAngle(double angle)
    {
        speedX = (int)(5 * Math.cos(Math.atan(angle)));
        speedY = (int)(5 * Math.sin(Math.atan(angle)));
    }

    /**
     * called at the start of each round. updates 
     * the x-coordinate and sets the y-coordinate to 870
     * @param x the new x-coordinate
     */
    public void updateStart(int x)
    {
        this.x = x;
        y = 870;
    }

    /**
     * 
     * @return if the ball is in motion
     */
    public boolean inMotion()
    {
        return nextY <= 760;
    }

    /**
     * 
     * @return number of stopped balls
     */
    public int stopped()
    {
        return stopped;
    }

    /**
     * sets the number of stopped balls to 0
     */
    public void reset()
    {
        stopped = 0;
    }
    /**
     * moves to the next position and changes the direction of  
     * the speed if it collides with the walls
     * @return an array of with the grid coordinates of the next move
     */
    public int[] move(ArrayList<Block[]> blocks)
    {
        if (nextY > 760)
            return null;
            
        x += speedX;
        y += speedY;

        if (speedX < 0)
            if (x - radius + speedX < 0)
                speedX *= -1;
        if (speedX > 0)
            if (x + radius + speedX > 730)
                speedX *= -1;

        if (speedY < 0)
            nextY = y - radius + speedY;
        else if (speedY > 0)
            nextY = y + radius + speedY;

        if (speedX < 0)
            nextX = x - radius + speedX;
        else if (speedX > 0)
            nextX = x + radius + speedY;

        if (nextX < 0 || nextX > 760)
            speedX *= -1;
        if (nextY < 0)
            speedY *= -1;


        if (nextY > 760)
            stopped++;

        
        int arrayX = -1;
        int arrayY = -1;
        if (!(nextX / 110 < 110 && nextX / 110 > 100) && !(nextY / 110 < 110 && nextY / 110 > 100))
            {
                arrayX = (nextX - nextX / 110 * 10) / 100;
                arrayY = (nextY - nextY / 110 * 10) / 100;
            }

        return new int[]{arrayX, arrayY};
    }

    /**
     * 
     * @return x-coord
     */
    public int getX()
    {
        return x;
    }

    /**
     * 
     * @return y-coord
     */
    public int getY()
    {
        return y;
    }

    /**
     * 
     * @param c - the color to change to
     */
    public void changeColor(Color c)
    {
       color = c; 
    }

    /**
     * 
     * @return the color
     */
    public Color getColor()
    {
        return color;
    }
}
