public class ChangeColor<Color> {
    private Color color;

    private ChangeColor<Color> previous;

    private ChangeColor<Color> next;

    public ChangeColor (Color initialColor, ChangeColor<Color> initialPrevious, ChangeColor<Color> intialNext) {
        color = initialColor;
        previous = initialPrevious;
        next = intialNext;
    }

    public Color getColor() {
        return color;
    }

    public ChangeColor<Color> getPrevious() {
        return previous;
    }

    public ChangeColor<Color> getNext() {
        return next;
    }

    public void setPrevious( ChangeColor<Color> theNewPrev) {
        previous = theNewPrev;
    }

    public void setNext(ChangeColor<Color> theNewNext) {
        next = theNewNext;
    }
}
