/**
 * @author Tristan, Nicholas, Leo
 * @version 05 - 23 - 2022
 * Block is the primary obstacle in the game which
 * balls reflect off of and destroy
 */

public class Block 
{
    private static final int side = 100;
    private int number;

    /**
     * constructs a Block using an input number
     * which represents the number of times a Ball has to 
     * hit it before the Block disappears from the game grid
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
     * converts an array coordinate into its GUI coordinate 
     * @param c -  array coordinate
     * @return coordinate
     */
    public int paintCoord(int c)
    {
        return c * 110;
    }

    /**
     * returns the side length
     * @return the side length
     */
    public static int getSide()
    {
        return side;
    }

    /**
     * returns the block number
     * @return the number on the block
     */
    public int getNum()
    {
        return number;
    }
}
