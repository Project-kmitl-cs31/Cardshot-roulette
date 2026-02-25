package src.Nut1;

import java.awt.*;
import javax.swing.*;

public class TurnColorOverlay extends JPanel {

    private boolean isBlue = false;
    private boolean isRed = false;


    Image turnBlueImg = new ImageIcon(getClass().getResource("/image/turnblue.gif")).getImage();
    Image turnRedImg = new ImageIcon(getClass().getResource("/image/turnred.gif")).getImage();

    private int setWidth;
    private int setHeight;

    public TurnColorOverlay(int w, int h) {
        this.setWidth = w;
        this.setHeight = h;
        setOpaque(false); 
        setBounds(0, 0, w, h);
 
    }

    public void swapTurnColor(String swap) {
        Timer time = new Timer(1200, e -> {
            if (swap.equals("blue")) {
                isBlue = true;
                isRed = false;
            }
            if (swap.equals("red")) {
                isRed = true;
                isBlue = false;
            }
            repaint();
        });
        time.setRepeats(false);
        time.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (isBlue) {
            g2d.drawImage(turnBlueImg, -100, 0, this);
            g2d.setColor(new Color(0, 200, 255));
            g2d.fillRect(-30, -10, 50, setHeight);
        }
        if (isRed) {
            g2d.drawImage(turnRedImg, 740, 0, this);
            g2d.setColor(new Color(255, 0, 0));
            g2d.fillRect(setWidth - 25, -10, 50, setHeight);
        }
    }
}