import java.awt.Color;

public class Ball
{
    private int speedX;
    private int speedY;
    private int ballX;
    private int ballY;
    Color color;
    private static int radius;
    
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
        //bally
    }

    public void collide(){

    }

    public void collideBottom(){
        
    }
}
