public class Block 
{
    private static final int side = 100;
    private int number;

    /**
     * constructs a Block using an input number
     * @param num
     */
    public Block(int num) 
    {
        number = num;
    }

    /**
     * decrements the number by 1
     * @return if the number is <= 0
     */
    public boolean collide()
    {
        number--;
        return number <= 0;
    }

    /**
     * converts column number into x-coordinate for painting
     * @param c - column array coord
     * @return x - coordinate
     */
    public int paintCoordX(int c)
    {
        return c * 110;
    }

    /**
     * converts row number into y-coordinate for painting
     * @param r row array coord
     * @return y coordinate
     */
    public int paintCoordY(int r)
    {
        return r * 110 + 100;
    }

    
    /**
     * 
     * @return the side length
     */
    public int getSide()
    {
        return side;
    }
}
