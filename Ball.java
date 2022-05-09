import java.awt.Color;
import java.util.ArrayList;

public class Ball
{
    private static final int radius; // = ?
    private int speedX;
    private int speedY;
    private int ballX;
    private int ballY;
    private static Color color = new Color(255, 255, 255);
    
    public Ball(int angle, int posX, int posY)
    {
        speedX = (int)(5 * Math.cos(Math.atan(angle)));
        speedX = (int)(5 * Math.sin(Math.atan(angle)));
        ballX = posX;
        ballY = posY;
    }

    public void updateStart(int x)
    {
        ballX = x;
        //ballY = ?
    }

    /**
     * moves to the next position and changes the direction of  
     * the speed if it collides with a Block
     * @return 
     */
    public boolean move()
    {
        ballX += speedX;
        ballY += speedY;

        ArrayList<Block[]> blocks = Main.blocks;
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
