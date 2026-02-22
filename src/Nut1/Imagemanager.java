package src.Nut1;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Imagemanager extends JPanel{
    ImageIcon backgroundImage;
    ImageIcon Table;
    private JLabel bgImg;
    private JLabel tableImg;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    int setWidth = screenSize.width;
    int setHeight = screenSize.height;
    public Imagemanager() {
    
        this.setLayout(null); 

        this.setPreferredSize(new Dimension(setWidth, setHeight));
        this.setBounds(0, 0, setWidth, setHeight);
    


        backgroundImage = new ImageIcon(getClass().getResource("/image/bg.jpg"));

        java.awt.Image bgImage = backgroundImage.getImage();
        java.awt.Image fixedscaleBg = bgImage.getScaledInstance(setWidth, setHeight, java.awt.Image.SCALE_SMOOTH);
        
        backgroundImage = new ImageIcon(fixedscaleBg);
        bgImg = new JLabel(backgroundImage);
        bgImg.setBounds(0, 0, setWidth, setHeight);
        bgImg.setLayout(null);

        Table = new ImageIcon(getClass().getResource("/image/table.png"));
        
        Image tableImage = Table.getImage();
        Image fixedscaletable = tableImage.getScaledInstance(setWidth-550, setHeight-300, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(fixedscaletable);
        tableImg = new JLabel(scaledIcon);
        tableImg.setBounds(0, (setHeight / 2) -250, setWidth, setHeight-200);
        System.out.print(setWidth);
        System.out.print(setHeight);
        bgImg.add(tableImg);
        this.add(bgImg);
        this.revalidate(); 
        this.repaint();
      
    }
    
}