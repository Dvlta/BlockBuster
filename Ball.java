import java.awt.Color;
/**
 * @author Leo, Nicholas, Tristan
 * @version 05 - 23 - 2022
 * Represents a ball in the game which the player shoots
 * to destroy Blocks and White Circles
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
    private int moveNum;
    
    /**
     * Creates a Ball object with a given launch angle and a center at posX.
     * Sets y to 820, the bottom of the screen
     * @param angle launch angle
     * @param posX x-coordinate of the center
     * @param posY y-coordinate of the center
     */
    public Ball(double angle, int posX)
    {
        speedX = (12 * Math.cos(Math.toRadians(angle)));
        speedY = -(12 * Math.sin(Math.toRadians(angle)));
        x = posX;
        y = 820;
        moveNum = 0;
    }

    /**
     * returns the move number
     * @return move number
     */
    public int getMoveNum()
    {
        return moveNum;
    }

    /**
     * sets move number to 0
     */
    public void resetMoveNum()
    {
        moveNum = 0;
    }

    /**
     * increments y (by 110) to the next row 
     */
    public void nextRow()
    {
        y += 110;
    }

    /**
     * returns the radius
     * @return radius
     */
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
        speedX = 12 * Math.cos(Math.toRadians(angle));
        speedY = -12 * Math.sin(Math.toRadians(angle));
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
     * if the ball has not finished moving 
     * @return if the ball has not finished moving 
     *  (aka when it is still in bounds)
     */
    public boolean inMotion()
    {
        return y <= 820;
    }

    /**
     * reverses the x direction of speed
     */
    public void reverseX()
    {
        speedX *= -1;
    }

    /**
     * reverses the y direction of speed
     */
    public void reverseY()
    {
        speedY *= -1;
    }
    /**
     * return number of stopped balls
     * @return number of stopped balls
     */
    public static int stopped()
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
        x += speedX;
        y += speedY;
        moveNum++;

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

        if (y > 820)
        {
            stopped++;
        }
        int arrayX = -1;
        int arrayY = -1;
        if (!(nextX / 110 < 110 && nextX / 110 > 100) && !(nextY / 110 < 110 && nextY / 110 > 100) && nextX > 0 && 
            nextX < 760 && nextY >= 0 && nextY <= 760)
            {
                arrayX = (((int)nextX - (int)nextX / 110 * 10) - 1)/ 100;
                arrayY = (((int)nextY - (int)nextY / 110 * 10) -1)/ 100;
            }
        return new int[]{arrayX, arrayY};
    }

    /**
     * returns the x-coordinate of its center
     * @return x-coordinate
     */
    public double getX()
    {
        return x;
    }

    /**
     * returns the y-coordinate of its center
     * @return y-coordinate
     */
    public double getY()
    {
        return y;
    }

    /**
     * returns the x-coordinate of next position
     * @return x-coordinate of next position
     */
    public double getNextX()
    {
        return nextX;
    }
    
    /**
     * returns the y-coordinate of next position
     * @return y-coordinate of next position
     */
    public double getNextY()
    {
        return nextY;
    }

    /**
     * sets the value of the static variable color
     * @param c the new color
     */
    public static void changeColor(Color c)
    {
       color = c; 
    }

    /**
     * returns the color of the balls
     * @return the color of the balls
     */
    public static Color getColor()
    {
        return color;
    }
}
