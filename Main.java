import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
public class Main extends JPanel implements Runnable{
    ArrayList<Ball> balls = new ArrayList<>();
    ArrayList<Block[]> blocks = new ArrayList<>();
    ArrayList<WhiteCircle> whiteCircles = new ArrayList<>();
    boolean gameOver = false;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(!gameOver){
            g.setColor(Color.blue);
            for()
        }
    }

    public void run(){

    }
    public static void main(String[] args){
        JFrame myFrame = new JFrame("Space Race");
        Main myPanel = new Main();
        myPanel.setPreferredSize(new Dimension(500,1000));
        myPanel.setBackground(Color.BLACK);
        myFrame.add(myPanel);
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        new Thread(myPanel).start();

    }

}
