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
     * converts array number into a coordinate for painting
     * @param c -  array coord
     * @return coordinate
     */
    public int paintCoord(int c)
    {
        return c * 110;
    }

    /**
     * 
     * @return the side length
     */
    public static int getSide()
    {
        return side;
    }
}
