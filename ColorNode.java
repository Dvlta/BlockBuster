import java.awt.Color;
public class ColorNode {
    private Color color;

    private ColorNode previous;
    private ColorNode next;
    /**
     * constructs a linkedlist of Color objects
     * @param initialColor - the initial color
     * @param initialPrevious - the previous of the initial color
     * @param intialNext - the next color of the initial color
     */
    public ColorNode (Color initialColor, ColorNode initialPrevious, ColorNode intialNext) {
        color = initialColor;
        previous = initialPrevious;
        next = intialNext;
    }
    /**
     * it gets the color of the ball object
     * @return color of the ball object
     */
    public Color getColor() {
        return color;
    }
    /**
     * it gets the color of the previous ball's object
     * @return color of the previous ball object
     */
    public ColorNode getPrevious() {
        return previous;
    }
    /**
     * it gets the color of the next ball object
     * @return color of the next ball object
     */
    public ColorNode getNext() {
        return next;
    }
    /**
     * it changes the color of the previous ball object to a new color
     * @param theNewPrev the new color the previous color is going to change to
     */
    public void setPrevious( ColorNode theNewPrev) {
        previous = theNewPrev;
    }

    /**
     * it changes the color of the next ball object to a new color
     * @param theNewNext the new color the next color is going to change to
     */
    public void setNext(ColorNode theNewNext) {
        next = theNewNext;
    }
}
