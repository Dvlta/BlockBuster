public class Block {
    private int length;
    private int width;
    private int number;

    public Block(int num) {
        length = 5;
        width = 5;
        number = num;
    }

    public boolean collide(){
        number--;
        return number <= 0;
    }

    
}
