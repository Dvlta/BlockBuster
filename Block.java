public class Block {
    private int length;
    private int width;
    private int number;

    public Block(int num) {
        length = 5;
        width = 5;
        number = num;
    }

    public int getBlockLength() {
        return length;
    }

    public int getBlockWidth() {
        return width;
    }

    public boolean collide(){
        number--;
        return number <= 0;
    }
}
