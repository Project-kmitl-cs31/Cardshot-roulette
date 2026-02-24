package src.Nut1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

public class PlayerPanel extends JPanel {

    private JLabel[] hpLabelarr = new JLabel[6];
    private ImageIcon heartIcon;
    private int posX;
    public PlayerPanel(int x, int y, int w, int h,String pos) {
        this.setBounds(x, y, w, h);
        this.setLayout(null);
        this.setOpaque(false);

        posX = pos.equals("r") ? 300 : 37;
    }
    public void refreshFromGame(int hp, String name,String pos) {
        this.removeAll();
        int xOffset = pos.equals("r") ? 300 : 30;
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
        this.revalidate();
        this.repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0, 0, 0, 75));
        g2d.fillRoundRect(posX, 55, 450, 90, 50, 50);
  

    }

   
}
