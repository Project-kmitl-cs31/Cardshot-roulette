package Nut1;

import java.awt.*;
import javax.swing.*;

public class GameOverScreen extends UIScreen {
    
    public GameOverScreen(UIManager ui , String winner ){
        super(ui);
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        Dimension  screesize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = screesize.width;
        int h = screesize.height;

        JLabel lblMsg = new JLabel(winner + " WINS!!", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Arial", Font.BOLD, 60));        
        lblMsg.setForeground(Color.YELLOW);
        lblMsg.setBounds(0, (h/2) - 150 , w , 100);

        JButton btnRestart = new JButton("PLAY AGAIN");
        btnRestart.setBounds((w/2) - 150 , (h/2) +20 ,300,60);
        btnRestart.setBackground(new Color(40 , 40 , 40));
        btnRestart.setForeground(Color.ORANGE);
        btnRestart.setFont(new Font("Serif",Font.BOLD,24));
        btnRestart.setFocusPainted(false);
        btnRestart.setBorder(BorderFactory.createLineBorder(new Color(218 , 165 , 32), 3));
        btnRestart.addActionListener(e -> ui.restartGame());

        JButton btnExit = new JButton("EXIT");
        btnExit.setBounds((w/2) - 150 , (h/2) +100 ,300,60);
        btnExit.setBackground(new Color(40, 40 , 40));
        btnExit.setForeground(Color.white); 
        btnExit.setFont(new Font("Serif", Font.BOLD,24));
        btnExit.setFocusPainted(false);
        btnExit.setBorder(BorderFactory.createLineBorder(new Color(218 , 165 , 32), 3));
        btnExit.addActionListener(e -> System.exit(0));

        this.add(lblMsg);
        this.add(btnRestart);
        this.add(btnExit);


        JLabel winImage = new JLabel("", SwingConstants.CENTER);
        winImage.setBounds(0, 0, w, h);
        this.add(winImage);
        
                ImageIcon icon = new ImageIcon("win.png");
                Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                winImage.setIcon(new ImageIcon(img));
       

        this.setComponentZOrder(lblMsg, 0);
        this.setComponentZOrder(btnRestart, 1);
        this.setComponentZOrder(btnExit, 2);
        this.setComponentZOrder(winImage, 3); 
    }
    
    @Override 
    public void show() { this.setVisible(true); }
    @Override 
    public void hide() { this.setVisible(false); }
    @Override
    public void render(Game state) {}
}