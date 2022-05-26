/**
 * @author Leo, Nicholas, Tristan
 * @version 05 - 23 - 2022
 * WhiteCircle represents a white circle in Blockbuster which,
 * when hit by a ball, gives the player an another ball
 */
public class WhiteCircle 
{
    private int x;
    private int y;
    private static final int diameter = 60;

    /**
     * 
     * @param row the row array index
     * @param col the col array index
     */
    public WhiteCircle(int row, int col)
    {
        this.x = col * 110 + 20;
        this.y = row * 110 + 20;
    }

    /**
     * updates y to the next row 
     * (increments y by 110)
     */
    public void incrementRow()
    {
        y += 110;
    }

    /**
     * returns the x-coordinate of its top-left corner
     * @return x
     */
    public int getX()
    {
        return x;
    }

    /**
     * returns the x-coordinate of its top-left corner
     * @return y
     */
    public int getY()
    {
        return y;
    }

    /**
     * returns its diameter
     * @return diameter
     */
    public static int getDiameter()
    {
        return diameter;
    }
}
