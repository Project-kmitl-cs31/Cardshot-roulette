package Nut;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class panel implements ActionListener {

    static private JFrame f;
    private static JPanel jpanelred;
    private static JPanel jpanelblue;
    private static JPanel black;
    private static JLabel jcenter;

    private static JPanel layout;

    public static void main(String[] args) {
        int setWidth = 1500;
        int setheight = 850;
        int opacity = 80;
        f = new JFrame("game");
        jpanelred = new JPanel();
        jpanelblue = new JPanel();
        black = new JPanel();
        jcenter = new JLabel();
        JLayeredPane lp = new JLayeredPane();
        lp.setLayout(null);
        lp.setBounds(0, 0, setWidth, setheight);

        jpanelblue.setBackground(Color.BLUE);
        jpanelblue.setBounds(35, 0, setWidth/2, setheight);

        jpanelred.setBackground(Color.RED);
        jpanelred.setBounds(setWidth/2, 0, setWidth/2, setheight);

        jcenter.setBackground(Color.GREEN);
        jcenter.setOpaque(true);
        jcenter.setBounds(setWidth / 2 - 150,setheight / 2 - 150,300,300);
        lp.add(jpanelblue, Integer.valueOf(0));
        lp.add(jpanelred, Integer.valueOf(1));
        lp.add(jcenter, Integer.valueOf(2));
        
        black.setBounds(0, 0, setWidth+100, setheight+100);
        // black.setBackground(new Color(0,0,0,opacity));
        // f.add(black);
        f.add(lp);
        f.setResizable(false);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException(" Not supported yet. ") ;
    }
}
