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

    public void incrementRow()
    {
        x += 110;
    }

    /**
     * 
     * @return x
     */
    public int getX()
    {
        return x;
    }

    /**
     * 
     * @return y
     */
    public int getY()
    {
        return y;
    }

    public static int getDiameter()
    {
        return diameter;
    }
}
