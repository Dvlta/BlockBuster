import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JPanel implements Runnable
{
    private ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Block[]> blocks = new ArrayList<Block[]>(8);
    private ArrayList<WhiteCircle[]> whiteCircles = new ArrayList<WhiteCircle[]>(8);
    private boolean gameOver = false;
    private final static int ballRadius = 12;
    private int round;

    public Main()
    {
        for (int i = 0; i < 8; i++)
        {
            blocks.add(new Block[7]);
            whiteCircles.add(new WhiteCircle[7]);
        }
        round = 0;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(!gameOver)
        {
            g.setColor(Color.blue);
            for(int i=0;i<blocks.size();i++){
                for(int j = 0;j<blocks.get(i).length;j++){
                    g.drawRect(blocks.get(i)[j].paintCoordX(i), blocks.get(i)[j].paintCoordY(j), blocks.get(i)[j].getSide(), blocks.get(i)[j].getSide());
                }
            }
            g.setColor(Color.white);
            for(int i=0;i<whiteCircles.size();i++){
                for(int j = 0;j<whiteCircles.get(i).length;j++){
                    int d = whiteCircles.get(i)[j].getDiameter();
                    g.drawOval(x-d/2, y+d/2, d, d);
                }
            }
            g.setColor(Color.orange);
            for(int i=0;i<balls.size();i++){
                Ball b = balls.get(i);
                g.drawOval(b.getX()-ballRadius, b.getY()+ballRadius, ballRadius*2, ballRadius*2);

            g.setColor(Color.white);
            g.drawRect(740, 980, 10, 10);
            }
        }
    }

    public void mouseInput(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(gameOver){
                    gameOver = false;
                }
                
            }
        });
    }

    public void run()
    {
        while (true)
        {
            if (!gameOver)
            {
                round++;
                int start = (int)(Math.random() * 751) + 5;
                if (round == 1)
                {
                    balls.add(new Ball(0, start));
                }

            }

        }
    }
    public static void main(String[] args)
    {
        JFrame myFrame = new JFrame("Space Race");
        Main myPanel = new Main();
        myPanel.setPreferredSize(new Dimension(760,1000)); 
        // width of block = 100, width of spaces = 10
        myPanel.setBackground(Color.BLACK);
        myFrame.add(myPanel);
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        new Thread(myPanel).start();


    }

}

