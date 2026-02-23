package src.Nut1;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Imagemanager extends JPanel implements ActionListener{
    Image backgroundImage;
    Image tableImage;

    
    Image turnBlueImg = new ImageIcon(getClass().getResource("/image/turnblue.gif")).getImage();
    Image turnRedImg = new ImageIcon(getClass().getResource("/image/turnred.gif")).getImage();
    boolean isBlue = false;
    boolean isRed = false;

    int xVelocity = 10;
    int x= 0;
    int yVelocity = 10;
    int y= 0;
    
    Timer timer1;


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    int setWidth = screenSize.width;
    int setHeight = screenSize.height;
    public Imagemanager() {
    
        this.setLayout(null); 

        this.setPreferredSize(new Dimension(setWidth, setHeight));
        this.setBounds(0, 0, setWidth, setHeight);

        backgroundImage = new ImageIcon(getClass().getResource("/image/bg.jpg")).getImage();

        Image table = new ImageIcon(getClass().getResource("/image/table.png")).getImage();
        tableImage = table.getScaledInstance(setWidth - 550, setHeight - 300, Image.SCALE_SMOOTH);

        
        this.revalidate(); 
        this.repaint();
      
    }

    public void playanim(){
        timer1 = new Timer(40,this);
        timer1.start();
    }
     public void stopanim(){
        timer1.stop();
        x = 0;
        y = 0;
        this.revalidate();
        this.repaint();
    }
    public void swapTurnColor(String swap){
        if(swap.equals("blue")){
            this.isBlue =true;
            this.isRed =false;  
        }
        if(swap.equals("red")){
            this.isRed =true; 
            this.isBlue =false;
        }
        
        repaint();

    }
    @Override
     public void paintComponent(Graphics g){
    super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage, x, y, setWidth, setHeight,null);
    
        int tableX = (setWidth / 2) - (tableImage.getWidth(null) / 2);
        int tableY = (setHeight / 2) - 150;
            
        g2d.drawImage(tableImage, tableX, tableY, null);

        if(isBlue){
            g2d.drawImage(turnBlueImg, -50, 0, this);
        }
        if(isRed){
            g2d.drawImage(turnRedImg, tableX+370, 0, this);
    
        }


   }

    public void actionPerformed(ActionEvent e) {
        
        repaint();

        if(y>=yVelocity || y<0  ){
            yVelocity = yVelocity * -1;
        }
        y += yVelocity;
        repaint();
        
       
    }

    
}