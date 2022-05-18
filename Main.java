import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Main extends JPanel implements Runnable
{
    private ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Block[]> blocks = new ArrayList<Block[]>(7);
    private ArrayList<WhiteCircle[]> whiteCircles = new ArrayList<WhiteCircle[]>(7);
    private boolean gameOver = false;
    private final static int ballRadius = 12;
    private int round;
    private boolean inPlay = false;
    private boolean colorScreen = false;
    private ColorNode node;

    public Main()
    {
        for (int i = 0; i < 8; i++)
        {
            blocks.add(new Block[7]);
            whiteCircles.add(new WhiteCircle[7]);
        }
        round = 0;

        ColorNode w = new ColorNode(Color.white, null, null);
        ColorNode r = new ColorNode(Color.red, w, null);
        ColorNode o = new ColorNode(Color.orange, r, null);
        ColorNode y = new ColorNode(Color.yellow, o, null);
        ColorNode g = new ColorNode(Color.green, y, null);
        ColorNode b = new ColorNode(Color.blue, g, null);
        ColorNode p = new ColorNode(Color.pink, b, w);

        w.setNext(r);
        w.setPrevious(p);
        r.setNext(o);
        o.setNext(y);
        y.setNext(g);
        g.setNext(b);
        b.setNext(p);
        
        node = w;
    }

    public boolean inPlay()
    {
        return inPlay;
    }

    public void changePlay()
    {
        if (inPlay())
            inPlay = false;
        else
            inPlay = true;
    }

    public void changeColorScreen()
    {
        if (colorScreen)
            colorScreen = false;
        else
            colorScreen = true;
    }

    public ArrayList<Ball> getBalls()
    {
        return balls;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (colorScreen)
        {
            //add in color list later
            g.drawRect(100,100,100,100);
        }
        else if (!gameOver)
        {
            g.setColor(Color.blue);
            for(int i=0;i<blocks.size();i++){
                for(int j = 0;j<blocks.get(i).length;j++){
                    if (blocks.get(i)[j] != null)
                    {
                        g.fillRect(blocks.get(i)[j].paintCoord(j), blocks.get(i)[j].paintCoord(i), 
                            Block.getSide(), Block.getSide());
                    }
                }
            }

            g.setColor(Color.white);
            for(int i=0;i<whiteCircles.size();i++){
                for(int j = 0;j<whiteCircles.get(i).length;j++){
                    if (whiteCircles.get(i)[j] != null)
                    {
                        int d = WhiteCircle.getDiameter();
                        g.fillOval(whiteCircles.get(i)[j].getX(), whiteCircles.get(i)[j].getY(), d, d);
                    }
                }
            }

            g.setColor(Ball.getColor());
            for (Ball b: balls)
            {
                g.fillOval(b.getX() - ballRadius, b.getY() - ballRadius, ballRadius*2, ballRadius*2);
                System.out.println("Draw ball at "+ b.getX() + " " + b.getY());
            }
            
            
            g.setColor(Color.white);
            g.fillRect(740, 820, 10, 10);
        }
    }

    public void run()
    {
        while (true)
        {
            //if not over, begin a new round
            if (!gameOver)
            {
                //add an empty new row of blocks and whiteCircles to the arrays
                blocks.add(new Block[7]);
                whiteCircles.add(new WhiteCircle[7]);

                //end game if the last row is not empty
                if (blocks.size() == 8)
                {
                    Block[] blks = blocks.remove(7);
                    for (Block block: blks)
                        if (block != null)
                            gameOver = true;
                }
                if (whiteCircles.size() == 8)
                {
                    WhiteCircle[] circles = whiteCircles.remove(7);
                    for (WhiteCircle w: circles)
                        if (w != null)
                            gameOver = true;
                }
                if (gameOver)
                    break;

                //begins the new round, generates new blocks and a new whiteCircle
                round++;
                int start = (int)(Math.random() * 751) + 5;
                if (round == 1)
                {
                    balls.add(new Ball(0, start));
                }
                for (int i = 0; i < 7; i++)
                {
                    int n = (int)(Math.random() * 7);
                    if (blocks.get(0)[n] == null)
                    {
                        blocks.get(0)[n] = new Block((int)(Math.random() * round * 2) + 1);
                    }
                }
                while (true)
                {
                    int n = (int)(Math.random() * 7);
                    if (blocks.get(0)[n] == null)
                    {
                        whiteCircles.get(0)[n] = new WhiteCircle(0, n);
                        break;
                    }
                }

                //random starting position of balls
                for (Ball b: balls)
                    b.updateStart(start);

                //repaint with new blocks/whiteCircles
                System.out.println("Repainted");
                repaint();
                
                //waits until the mouse has been clicked and the game is in play
                while (!inPlay) {
                    try {
                        Thread.sleep(4);
                    } catch (Exception e) {
                    }
                }

                //keeps going until all the balls have stopped
                int add = 0;
                while (balls.size() > balls.get(0).stopped())
                {
                    inPlay = true;
                    for(Ball b:balls){
                        if(b.inMotion())
                        {
                            Block blk;
                            int[] arr = b.move();
                            if (arr[0] != -1 && arr[0] != -1)
                            {
                                blk = blocks.get(arr[0])[arr[1]];
                                if (blk != null)
                                {
                                    if(blk.collide())
                                    {
                                        blocks.get(arr[0])[arr[1]] = null;
                                    }
                                }
                                else     
                                {
                                    WhiteCircle w = whiteCircles.get(arr[0])[arr[1]];
                                    if (w != null && Math.sqrt(Math.pow(b.getX() - w.getX(), 2) + 
                                        Math.pow(b.getY() - w.getY(), 2)) <=
                                        Ball.getRadius() + WhiteCircle.getDiameter() / 2)
                                    {
                                        whiteCircles.get(arr[0])[arr[1]] = null;
                                        add++;
                                    }
                                }                           
                            }
                        }
                        //repaint again to update all the balls, sets time delay
                        repaint();
                        try {
                            Thread.sleep(4);
                        } catch (Exception e) {
            
                        }
                    }
                }
                inPlay = false;
                for (int i = 0; i < add; i++)
                    balls.add(new Ball(0, 0 ));
            }
        }
    }
    public static void main(String[] args)
    {
        JFrame myFrame = new JFrame("BlockBuster");
        Main myPanel = new Main();
        myPanel.setPreferredSize(new Dimension(762,1000)); 
        // width of block = 100, width of spaces = 10
        myPanel.setBackground(Color.BLACK);
        myFrame.add(myPanel);
        MouseInputListener m = new MouseInputListener() {
            public void mouseDragged(MouseEvent e) {}

            public void mouseMoved(MouseEvent e) {}

            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (y < 870 && !myPanel.inPlay())
                {
                    double angle = Math.atan2(myPanel.getBalls().get(0).getY() - y, 
                        x - myPanel.getBalls().get(0).getX());
                    if (angle < 0)
                        angle += 180;
                    for (Ball b: myPanel.getBalls())
                    {
                        b.changeAngle(angle);
                    }
                    myPanel.changePlay();
                }
                else if (x <= 750 && x >= 740 && y <= 830 && y >= 820 && !myPanel.inPlay())
                {
                    myPanel.changeColorScreen();
                    myFrame.repaint();
                }
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseExited(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {}            
        };
        myFrame.addMouseListener(m);
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        new Thread(myPanel).start();


    }

}

