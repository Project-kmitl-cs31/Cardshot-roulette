package src.Nut1;

import java.awt.*;
import javax.swing.*;

public class PlayerPanel extends JPanel {
    private JLabel hpLabel;

    public PlayerPanel(int x, int y, int w, int h) {
        this.setBounds(x, y, w, h);
        this.setBackground(new Color(0,0,0,50));
        this.setLayout(null); 


        hpLabel = new JLabel("HP: ???");
        hpLabel.setForeground(Color.WHITE);
        hpLabel.setFont(new Font("Arial", Font.BOLD, 24));
        hpLabel.setBounds(400, 10, 200, 50);
        this.add(hpLabel);
    }
    public void refreshFromGame(int hp , String name) {
        hpLabel.setText("HP: " + hp);
        this.repaint();

        // namLabel.setText("Name:" + name);
        // this.repaint();
    }  
}  