import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class Main extends JPanel implements Runnable
{
    private ArrayList<Ball> balls = new ArrayList<>();
    private static ArrayList<Block[]> blocks = new ArrayList<Block[]>(8);
    private ArrayList<WhiteCircle[]> whiteCircles = new ArrayList<WhiteCircle[]>(8);
    private boolean gameOver = false;

    public Main()
    {
        for (int i = 0; i < 8; i++)
        {
            blocks.add(new Block[7]);
            whiteCircles.add(new WhiteCircle[7]);
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(!gameOver){
            g.setColor(Color.blue);
            for()
        }
    }

    public void run()
    {

    }
    public static void main(String[] args)
    {
        JFrame myFrame = new JFrame("Space Race");
        Main myPanel = new Main();
        myPanel.setPreferredSize(new Dimension(730,1000));
        myPanel.setBackground(Color.BLACK);
        myFrame.add(myPanel);
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        new Thread(myPanel).start();


    }

}
