import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
import java.awt.*;
import java.util.*;
public class Main extends JPanel implements Runnable{

    public void run(){
        
    }
    public static void main(String[] args){
        ArrayList<Ball> balls = new ArrayList<>();
        ArrayList<Block[]> blocks = new ArrayList<>();
        ArrayList<WhiteCircle> whiteCircles = new ArrayList<>();
        JFrame myFrame = new JFrame("Space Race");
        Main myPanel = new Main();
        myPanel.setPreferredSize(new Dimension(500,800));
        myPanel.setBackground(Color.BLACK);
        myFrame.add(myPanel);
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        new Thread(myPanel).start();

    }

}
