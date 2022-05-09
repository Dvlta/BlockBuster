public class Block 
{
    private static final int side = 100;
    private int number;

    public Block(int num) {
        number = num;
    }

    public boolean collide()
    {
        number--;
        return number <= 0;
    }

    
}
