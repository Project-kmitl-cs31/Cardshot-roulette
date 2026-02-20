package Nut1;

import java.awt.*;
import javax.swing.*;

public class PlayerPanel extends JPanel {
    private JLabel hpLabel;
    private JLabel namLabel;

    public PlayerPanel(int x, int y, int w, int h, Color bg) {
        this.setBounds(x, y, w, h);
        this.setBackground(bg);
        this.setLayout(null); 

        hpLabel = new JLabel("HP: ???");
        hpLabel.setForeground(Color.WHITE);
        hpLabel.setFont(new Font("Arial", Font.BOLD, 24));
        hpLabel.setBounds(400, 10, 200, 50);
        this.add(hpLabel);
        
        //namLabel = new JLabel("Name: ");
        //namLabel.setForeground(Color.WHITE);
        //namLabel.setFont(new Font("Arial", Font.BOLD, 24));
        //namLabel.setBounds(10, 10, 200, 50);
        //this.add(namLabel);
    }
    public void refreshFromGame(int hp , String name) {
        hpLabel.setText("HP: " + hp);
        this.repaint();

        //namLabel.setText("Name:" + name);
        //this.repaint();
    }  
}  