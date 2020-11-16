package AnimationTemplate_withExample_;

/**
 *
 * @author ISlam
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements Runnable {
    private final int B_WIDTH = 350;
    private final int B_HEIGHT = 350;
    private final int INITIAL_X = 175;
    private final int INITIAL_Y = 175;
    private final int DELAY = 1;//the delay time for every new frame.
    
    private Image dot;
    private Thread animator;
    private nextDotLocation ndl=new nextDotLocation();
    private int x, y;
    
    public Board(){
        initBoard();
    }
    
    private void initBoard(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(B_WIDTH,B_HEIGHT));
        loadImage();
        x=INITIAL_X;
        y=INITIAL_Y;
        ndl.set(x, y);
        
    }
    private void loadImage(){
        ImageIcon ii=new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("resources/dot.png")).getImage());
        dot=ii.getImage();
    }
    @Override
    public void addNotify(){
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawObject(g);
    }
    
    private void drawObject(Graphics g) {
         /*
        
        
        here you draw the objects that will aperear for the user 
        drawing images :
        g.drawImage(object, x, y, this);
        drawing strings :
        g.drawString(string, x, y);
        and so on
        
        */
        g.drawImage(dot, x, y, this);
        Toolkit.getDefaultToolkit().sync();
    }
    private void cycle() {
         /*
        
        
        the changes that happens every cycle,like changing the coordinates for an object 
        or changing values in the background.
        
        
        
        */
        x = ndl.getX();
        y = ndl.getY();
        ndl.checkNextMove();
        }
   
     @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            cycle();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                
                JOptionPane.showMessageDialog(this, msg, "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
        }
    }
    
    
      private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            
              /*
            
            action preformed when pressing a key.
           
            
            */
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT)
                ndl.set(-5,0);
             else if (key == KeyEvent.VK_RIGHT) 
                ndl.set(+5,0);
            else if (key == KeyEvent.VK_UP) 
                 ndl.set(0,-5);
            else if (key == KeyEvent.VK_DOWN) 
                 ndl.set(0,+5);
        }
    }
        }
        
      

    

 
