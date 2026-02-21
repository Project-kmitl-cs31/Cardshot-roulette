package src.Nut1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainmenuScreen extends UIScreen{
    private JButton btnStart;
    private JButton btnExit;
    private JLabel bgImage;

    public MainmenuScreen(UIManager ui){
        super(ui);
        this.setLayout(null);
        
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = screensize.width;
        int h = screensize.height;

        JLayeredPane lp = new JLayeredPane();
        lp.setBounds(0,0,w,h);
        btnStart = new JButton("START GAME");
        btnStart.setBounds((w/2) - 100 , (h/2) + 50 , 200 , 60);
        btnStart.setFont(new Font("Arial", Font.BOLD,24));
        btnStart.setBackground(Color.GREEN);
        
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ui.openGameScreen();
            }
        });
        btnExit = new JButton("EXIT");
        btnExit.setBounds((w/2)- 100, (h/2) + 130 , 200, 60);
        btnExit.setFont(new Font("Arial", Font.BOLD, 24));
        btnExit.setBackground(Color.RED);
        btnExit.setForeground(Color.WHITE);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        ImageIcon icon = new ImageIcon("War.png");
        Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        bgImage = new JLabel(icon);
        bgImage.setBounds(0,0,w,h);

        lp.add(bgImage, Integer.valueOf(0));
        lp.add(btnStart, Integer.valueOf(1));
        lp.add(btnExit, Integer.valueOf(1));
        this.add(lp);
        this.setBounds(0,0,w,h);
    }
    @Override
    public void show() { 
        this.setVisible(true);
     }

    @Override
    public void hide() {
         this.setVisible(false); 
        }

    @Override
    public void render(Game state) {
       
    }
}