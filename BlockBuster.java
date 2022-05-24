import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;
/**
 * @author Nicholas, Leo, Tristan
 * @version 05 - 23 - 2022
 */
public class BlockBuster extends JPanel implements Runnable
{
    private ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Block[]> blocks = new ArrayList<Block[]>(8);
    private ArrayList<WhiteCircle[]> whiteCircles = new ArrayList<WhiteCircle[]>(8);
    private static boolean gameOver = true;
    private final static int ballRadius = 12;
    private int round;
    private boolean inPlay = false;
    private boolean colorScreen = false;
    private ColorNode node; 
    private int highScore;
 
    public BlockBuster() 
    {
        for (int i = 0; i < 8; i++)
        {
            blocks.add(new Block[7]);
            whiteCircles.add(new WhiteCircle[7]);
        }
        round = 0;
        highScore = 0;

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

    public void reset()
    {
        System.out.println("reset");
        balls = new ArrayList<>();
        blocks = new ArrayList<Block[]>(8);
        whiteCircles = new ArrayList<WhiteCircle[]>(8); 
        for (int i = 0; i < 8; i++)
        {
            blocks.add(new Block[7]);
            whiteCircles.add(new WhiteCircle[7]);
        }
        round = 0;
        gameOver = false;
    }

    public void setNode(ColorNode n)
    {
        node = n;
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

    public boolean getColorScreen()
    {
        return colorScreen;
    }

    /**
     * 
     * @return the arraylist of balls
     */
    public ArrayList<Ball> getBalls()
    {
        return balls;
    }
    /**
     * 
     * @return the color node
     */
    public ColorNode getNode()
    {
        return node;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (colorScreen)
        {
            //add in color list later
            g.setColor(Color.white);
            g.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 40));
            g.drawString("Click on a Color to Choose", 160, 200);

            g.drawString("Exit to Main Menu:", 180, 700);
            g.fillRect(530, 665, 50, 50);

            g.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 20));
            g.drawString("Selected Color", 85, 320);
            g.setColor(node.getColor());
            g.fillRect(100,350,100,100);

            g.setColor(node.getNext().getColor());
            g.fillRect(340,350,100,100);

            g.setColor(node.getNext().getNext().getColor());
            g.fillRect(580,350,100,100);
        }
        else if (!gameOver)
        {
            g.setColor(Color.white);
            g.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 30));
            g.drawString("Score: " + round, 330, 840);

            for(int i = 0; i < blocks.size(); i++) {
                for(int j = 0; j < blocks.get(i).length; j++) {
                    if (blocks.get(i)[j] != null)
                    {
                        g.setColor(Color.blue);
                        g.fillRect(blocks.get(i)[j].paintCoord(j), blocks.get(i)[j].paintCoord(i), 
                            Block.getSide(), Block.getSide());
                        g.setColor(Color.white);
                        g.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 50));
                        int offset = -(int)Math.log10(blocks.get(i)[j].getNum()) * 15;
                        g.drawString(blocks.get(i)[j].getNum() + "", blocks.get(i)[j].paintCoord(j)+ 40 + offset,
                            blocks.get(i)[j].paintCoord(i)+65);
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
                g.setColor(Ball.getColor());
                g.fillOval((int)(b.getX() - ballRadius), (int)(b.getY() - ballRadius), ballRadius*2, ballRadius*2);
                if (!inPlay)
                {
                    g.setColor(Color.white);
                    g.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 20));
                    g.drawString("x" + balls.size(), (int)b.getX() - 10, (int)b.getY() + 28);
                }
            }
        }else if(gameOver){
            g.setColor(Color.white);
            g.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 100));
            g.drawString("BlockBuster", 140,400);
            g.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 30));
            g.drawString("Click anywhere to play!", 230,700);
            g.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 50));
            g.drawString("High Score: " + highScore, 230, 200);

            g.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 20));
            g.drawString("Access Color Menu:", 490, 790);
            g.setColor(Ball.getColor());
            g.fillRect(670, 770, 25, 25);
        } 
    }

    public void run()
    {
        System.out.println("run");
        while (true)
        {
            //if not over, begin a new round
            if (!gameOver)
            {
                //add an empty new row of blocks and whiteCircles to the arrays
                blocks.add(0, new Block[7]);
                whiteCircles.add(0, new WhiteCircle[7]);

                //end game if the last row is not empty
                Block[] blks = blocks.remove(7);
                for (Block block: blks)
                    if (block != null)
                        gameOver = true;

                WhiteCircle[] circles = whiteCircles.remove(7);
                for (WhiteCircle w: circles)
                    if (w != null)
                        gameOver = true;
                if (gameOver)
                {
                    if (round > highScore)
                        highScore = round;
                    continue;
                }
                System.out.println("playing");
                //begins the new round, generates new blocks and a new whiteCircle
                round++;
                int start = (int)(Math.random() * 751) + 5;
                if (round == 1)
                {
                    balls.add(new Ball(0, start));
                }
                else
                {
                    for (Ball b: balls)
                    {
                        b.nextRow();
                    }
                    for (int i = 0; i < blocks.size(); i++)
                        for (int j = 0; j < blocks.get(i).length; j++)
                            if (whiteCircles.get(i)[j] != null)
                                whiteCircles.get(i)[j].incrementRow();
                }

                for (int i = 0; i < 4; i++)
                {
                    int n = (int)(Math.random() * 7);
                    if (blocks.get(0)[n] == null)
                    {
                        blocks.get(0)[n] = new Block((int)(Math.random() * round + 1) + round);
                    }
                }
                if (Math.random() < 0.7)
                {
                    while (true)
                    {
                        int n = (int)(Math.random() * 7);
                        if (blocks.get(0)[n] == null)
                        {
                            whiteCircles.get(0)[n] = new WhiteCircle(0, n);
                            break;
                        }
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
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                }
                //keeps going until all the balls have stopped
                int add = 0;
                while (balls.size() > Ball.stopped())
                {
                    inPlay = true;
                    //int count = 0;
                    for (int idx = 0; idx < balls.size(); idx++) {
                        Ball b = balls.get(idx);
                        if (b.inMotion()) {
                            Block blk;
                           if (idx != 0 && balls.get(idx - 1).inMotion() && balls.get(idx - 1).getMoveNum() >= 0 && balls.get(idx - 1).getMoveNum() <= 12) {
                                continue;
                            }
                            //count++;
                            int[] arr = b.move();
                            if (arr[0] != -1 && arr[1] != -1)
                            {
                                blk = blocks.get(arr[1])[arr[0]];
                                if (blk != null) {
                                    if (b.getX() > blk.paintCoord(arr[0]) + Block.getSide() || b.getX() < blk.paintCoord(arr[0]))
                                    {
                                        b.reverseX();
                                    }
                                    else if (b.getY() > blk.paintCoord(arr[1]) + Block.getSide()|| b.getY() < blk.paintCoord(arr[1]))
                                    {
                                        b.reverseY();
                                    }

                                    if (blk.collide())
                                    {
                                        blocks.get(arr[1])[arr[0]] = null;
                                    }
                                }
                                else     
                                {
                                    WhiteCircle w = whiteCircles.get(arr[1])[arr[0]];
                                    if (w != null && Math.sqrt(Math.pow(b.getX() - w.getX() - WhiteCircle.getDiameter() / 2, 2) + 
                                        Math.pow(b.getY() - w.getY() - WhiteCircle.getDiameter() / 2, 2)) <=
                                        Ball.getRadius() + WhiteCircle.getDiameter() / 2)
                                    {
                                        whiteCircles.get(arr[1])[arr[0]] = null;
                                        add++;
                                    }
                                }
                            }
                        }
                    }
                    repaint();
                    try {
                        Thread.sleep(8);
                    } catch (Exception e) {
        
                    }
                }
                repaint();
                Ball.reset();
                System.out.println("not inplay");
                inPlay = false;
                for (int i = 0; i < add; i++) {
                    balls.add(new Ball(0, 0 ));
                }
            }else{
                repaint();
            }
        }
    }

    public static void main(String[] args)
    {
        JFrame myFrame = new JFrame("BlockBuster");
        BlockBuster myPanel = new BlockBuster();
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
                if(!gameOver)
                {
                    System.out.println("mouse clicked at " + x + " " + y);  
                    if (y < 820 && !myPanel.inPlay())
                    {
                        double angle = Math.toDegrees(Math.atan2(myPanel.getBalls().get(0).getY() - y, 
                            x - myPanel.getBalls().get(0).getX()));
                        if (angle < 0)
                            angle += 180;
                        if (angle > 5 && angle < 175)
                        {
                            for (Ball b : myPanel.getBalls())
                            {
                                b.changeAngle(angle);
                                b.resetMoveNum();
                            }
                        myPanel.changePlay();
                        }
                    }   
                } else
                {
                    System.out.println("mouse clicked at " + x + " " + y);  
                    if (myPanel.getColorScreen())
                    {
                        if (x >= 530 && x <= 580 && y >= 690 && y <= 740)
                        {
                            myPanel.changeColorScreen();
                            myFrame.repaint();
                        }
                        else if (x >= 100 && x <= 200 && y <= 450 && y >= 350)
                        {
                            Ball.changeColor(myPanel.getNode().getColor());
                        }
                        else if (x >= 340 && x <= 440 && y <= 450 && y >= 350)
                        {
                            Ball.changeColor(myPanel.getNode().getNext().getColor());
                            myPanel.setNode(myPanel.getNode().getNext());
                            myFrame.repaint();
                        }
                        else if (x <= 680 && x >= 580 && y <= 450 && y >= 350)
                        {
                            Ball.changeColor(myPanel.getNode().getNext().getNext().getColor());
                            myPanel.setNode(myPanel.getNode().getNext().getNext());
                            myFrame.repaint();
                        }
                    }
                    else if (x <= 695 && x >= 670 && y <= 820 && y >= 795)
                    {
                        System.out.println("color");
                        myPanel.changeColorScreen();
                        myFrame.repaint();
                    } 
                    else 
                    {
                        System.out.println("reset");
                        myPanel.reset();
                    }
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

