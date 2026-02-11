import java.awt.*;
import javax.swing.*;

public class PlayerPanel extends JPanel {
    private JLabel hpLabel;

    public PlayerPanel(int x, int y, int w, int h, Color bg) {
        this.setBounds(x, y, w, h);
        this.setBackground(bg);
        this.setLayout(null); 

        hpLabel = new JLabel("HP: ???");
        hpLabel.setForeground(Color.WHITE);
        hpLabel.setFont(new Font("Arial", Font.BOLD, 24));
        hpLabel.setBounds(10, 10, 200, 50);
        this.add(hpLabel);
    }
    public void refreshFromGame(int hp) {
        hpLabel.setText("HP: " + hp);
    }
}
