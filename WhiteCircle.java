public class WhiteCircle 
{
    private int x;
    private int y;

    /**
     * 
     * @param x the col array index
     * @param y the row array index
     */
    public WhiteCircle(int x, int y)
    {
        this.x = x * 110;
        this.y = y * 110 + 100;
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
}
