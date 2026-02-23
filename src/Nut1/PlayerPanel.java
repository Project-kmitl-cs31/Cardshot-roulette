package src.Nut1;

import java.awt.*;
import javax.swing.*;

public class PlayerPanel extends JPanel {

    private JLabel hpLabel;
    private JLabel[] hpLabelarr = new JLabel[6];
    private ImageIcon heartIcon;

    public PlayerPanel(int x, int y, int w, int h) {
        this.setBounds(x, y, w, h);
        this.setBackground(new Color(0, 0, 0, 50));
        this.setLayout(null);
    }
    public void refreshFromGame(int hp, String name,String pos) {
        this.removeAll();
        int xOffset = pos.equals("r") ? 300 : 0;
        String nameIcon = pos.equals("r") ? "heartPRight" : "heartPLeft";

        heartIcon = new ImageIcon(getClass().getResource("/image/"+nameIcon+".png"));
        for (int i = 0; i < hp; i++) {
            if (i >= hpLabelarr.length) {
                break;
            }
            
            hpLabelarr[i] = new JLabel();
            hpLabelarr[i].setIcon(heartIcon);
            hpLabelarr[i].setBounds(xOffset, 30,   150, 150);
            this.add(hpLabelarr[i]);
            xOffset += 70;
        }
        // System.out.println("name form: "+name);
        this.revalidate();
        this.repaint();
    }
}
