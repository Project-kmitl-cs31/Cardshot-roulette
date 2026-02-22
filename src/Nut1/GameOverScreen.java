package src.Nut1;

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
        btnRestart.setBackground(Color.green);
        btnRestart.setFont(new Font("Arial",Font.BOLD,24));
        btnRestart.addActionListener(e -> ui.restartGame());

        JButton btnExit = new JButton("EXIT");
        btnExit.setBounds((w/2) - 150 , (h/2) +100 ,300,60);
        btnExit.setBackground(Color.RED);
        btnExit.setBackground(Color.white);
        btnExit.setFont(new Font("Arial", Font.BOLD,24));
        btnExit.addActionListener(e -> System.exit(0));

        this.add(lblMsg);
        this.add(btnRestart);
        this.add(btnExit);
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
