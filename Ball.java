import java.awt.Color;

/**
 * 
 */
public class Ball
{
    private static final int radius = 12;
    private static int stopped = 0;
    private double speedX;
    private double speedY;
    private double x; 
    private double y;
    private double nextX;
    private double nextY;
    private static Color color = Color.white;
    
    /**
     * creates a Ball object with a launch angle and a center
     * @param angle launch angle
     * @param posX x-coordinate of the center
     * @param posY y-coordinate of the center
     */
    public Ball(double angle, int posX)
    {
        speedX = (2 * Math.cos(Math.toRadians(angle)));
        speedY = -(2 * Math.sin(Math.toRadians(angle)));
        x = posX;
        y = 820;
    }

    public void nextRow()
    {
        y += 110;
    }

    public static int getRadius()
    {
        return radius;
    }

    /**
     * updates the x and y components of speed 
     * based on the launch angle
     * @param angle launch angle
     */
    public void changeAngle(double angle)
    {
        speedX = 2 * Math.cos(Math.toRadians(angle));
        speedY = -2 * Math.sin(Math.toRadians(angle));
        System.out.println(angle + " " + speedX + " " + speedY);
    }

    /**
     * called at the start of each round. updates 
     * the x-coordinate and sets the y-coordinate to 820
     * @param x the new x-coordinate
     */
    public void updateStart(int x)
    {
        this.x = x;
        y = 820;
    }

    /**
     * 
     * @return if the ball is in motion
     */
    public boolean inMotion()
    {
        return nextY <= 820;
    }

    public void reverseX()
    {
        speedX *= -1;
    }

    public void reverseY()
    {
        speedY *= -1;
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
    public static void reset()
    {
        stopped = 0;
    }
    /**
     * moves to the next position and changes the direction of  
     * the speed if it collides with the walls
     * @return an array of with the grid coordinates of the next move
     */
    public int[] move()
    {   
        //System.out.println("move" + x + " " + y);
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


        if (nextY > 820)
            stopped++;

        
        int arrayX = -1;
        int arrayY = -1;
        if (!(nextX / 110 < 110 && nextX / 110 > 100) && !(nextY / 110 < 110 && nextY / 110 > 100))
            {
                arrayX = ((int)nextX - (int)nextX / 110 * 10) / 100;
                arrayY = ((int)nextY - (int)nextY / 110 * 10) / 100;
                //System.out.println("ball position" + nextX + " " + nextY +" " + arrayX + " " + arrayY);
            }

        return new int[]{arrayX, arrayY};
    }

    /**
     * 
     * @return x-coord
     */
    public double getX()
    {
        return x;
    }

    /**
     * 
     * @return y-coord
     */
    public double getY()
    {
        return y;
    }

    public double getNextX()
    {
        return nextX;
    }
    
    public double getNextY()
    {
        return nextY;
    }

    /**
     * 
     * @param c - the color to change to
     */
    public static void changeColor(Color c)
    {
       color = c; 
    }

    /**
     * 
     * @return the color
     */
    public static Color getColor()
    {
        return color;
    }
}
