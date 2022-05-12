import java.awt.Color;
import java.util.ArrayList;

public class Ball
{
    private static final int radius = 12;
    private int speedX;
    private int speedY;
    private int x;
    private int y;
    private static Color color = new Color(255, 255, 255);
    
    public Ball(int angle, int posX, int posY)
    {
        speedX = (int)(5 * Math.cos(Math.atan(angle)));
        speedX = (int)(5 * Math.sin(Math.atan(angle)));
        x = posX;
        y = posY;
    }

    public void updateStart(int x)
    {
        this.x = x;
        //ballY = ?
    }

    /**
     * moves to the next position and changes the direction of  
     * the speed if it collides with a Block or the walls
     * @return 
     */
    public boolean move(ArrayList<Block[]> blocks)
    {
        x += speedX;
        y += speedY;

        if (speedX < 0)
            if (x - radius + speedX < 0)
                speedX *= -1;
        if (speedX > 0)
            if (x + radius + speedX > 730)
                speedX *= -1;

        if (speedY < 0)
            if (y - radius + speedY < 0)
                speedX *= -1;
        if (speedY > 0)
            if (y + radius + speedY > 730)
                speedX *= -1;
            
        return false;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void changeColor(Color c)
    {
       color = c; 
    }

    public Color getColor()
    {
        return color;
    }
}
