package src.Nut1;

import java.awt.*;
import javax.swing.*;

public class MessageOverlay {

    private JLabel labelItem;
    private JLabel selectCardtext;
    private JPanel msgPanel;
    private Timer msgTimer;

    private JLayeredPane lp;
    private int width, height;

    public MessageOverlay(JLayeredPane lp, int width, int height) {
        this.lp = lp;
        this.width = width;
        this.height = height;

        labelItem = new JLabel("", SwingConstants.CENTER);
        labelItem.setBounds((width / 2) - 200, (height / 2) - 300, 400, 300);
        labelItem.setForeground(Color.YELLOW);
        labelItem.setFont(new Font("Tahoma", Font.BOLD, 25));

        selectCardtext = new JLabel("", SwingConstants.CENTER);
        selectCardtext.setBounds((width / 2) - 250, (height / 2) - 350, 500, 300);
        selectCardtext.setForeground(Color.YELLOW);
        selectCardtext.setFont(new Font("Tahoma", Font.BOLD, 25));

        msgPanel = new JPanel();
        msgPanel.setLayout(null);
        msgPanel.setOpaque(false);
        msgPanel.setBounds((width / 2) - 200, (height / 2) - 450, width + 100, height + 200);

        lp.add(labelItem, Integer.valueOf(3));
        lp.add(selectCardtext, Integer.valueOf(3));
        lp.add(msgPanel, Integer.valueOf(3));
    }

    public void setMsgItem(String text, int duration) {
        labelItem.setText(text);

        if (msgTimer != null && msgTimer.isRunning()) {
            msgTimer.stop();
        }

        msgTimer = new Timer(1000 * duration, e -> {
            labelItem.setText("");
        });

        msgTimer.setRepeats(false);
        msgTimer.start();
    }

    public void setMsgCard(CentralDeck deck, String text, int duration, Runnable onFinish) {
        msgPanel.removeAll();

        String[] cardImg = deck.getAllSource();
        Image atkImg = new ImageIcon(getClass().getResource(cardImg[1])).getImage().getScaledInstance(120, 160, Image.SCALE_SMOOTH);
        Image blkImg = new ImageIcon(getClass().getResource(cardImg[0])).getImage().getScaledInstance(120, 160, Image.SCALE_SMOOTH);

        JLabel leftCard = new JLabel(new ImageIcon(blkImg));
        JLabel rightCard = new JLabel(new ImageIcon(atkImg));

        JLabel countatk = new JLabel(String.valueOf(text.charAt(12)));
        JLabel countblk = new JLabel(String.valueOf(text.charAt(text.length() - 1)));

        countatk.setForeground(Color.WHITE);
        countatk.setFont(new Font("Arial", Font.BOLD, 40));
        countblk.setForeground(Color.WHITE);
        countblk.setFont(new Font("Arial", Font.BOLD, 40));

        leftCard.setBounds(0, 70, 120, 160);
        rightCard.setBounds(280, 70, 120, 160);
        countatk.setBounds(140, 100, 60, 100);
        countblk.setBounds(240, 100, 60, 100);

        msgPanel.add(leftCard);
        msgPanel.add(rightCard);
        msgPanel.add(countatk);
        msgPanel.add(countblk);

        msgPanel.revalidate();
        msgPanel.repaint();
        lp.revalidate();
        lp.repaint();

        if (msgTimer != null && msgTimer.isRunning()) {
            msgTimer.stop();
        }

        msgTimer = new Timer(1000 * duration, e -> {
            msgPanel.removeAll();
            msgPanel.revalidate();
            msgPanel.repaint();
            if (onFinish != null) {
                onFinish.run();
            }
        });
        msgTimer.setRepeats(false);
        msgTimer.start();
    }
}
