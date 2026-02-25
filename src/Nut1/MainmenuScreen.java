package src.Nut1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainmenuScreen extends UIScreen {

    private JButton btnStart;
    private JButton btnExit;
    private JLabel bgImage;
    private JLabel titleImage;
    private JLabel idk;

    public MainmenuScreen(UIManager ui) {
        super(ui);
        this.setLayout(null);

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = screensize.width;
        int h = screensize.height;

        JLayeredPane lp = new JLayeredPane();
        lp.setBounds(0, 0, w, h);

        ImageIcon titleIcon = new ImageIcon("image/title.png");

        Image titleImg = titleIcon.getImage().getScaledInstance(800, 300, Image.SCALE_SMOOTH);

        titleImage = new JLabel(new ImageIcon(titleImg));
        titleImage.setBounds((w / 2) - 390, (h / 2) - 450, 800, 300);

        ImageIcon startIcon = new ImageIcon("image/start_button.png");
        Image startImg = startIcon.getImage().getScaledInstance(500, 240, Image.SCALE_SMOOTH);

        btnStart = new JButton(new ImageIcon(startImg));
        btnStart.setBounds((w / 2) - 250, (h / 2) - 100, 500, 240);

        btnStart.setContentAreaFilled(false);
        btnStart.setBorderPainted(false);
        btnStart.setFocusPainted(false);
        btnStart.setOpaque(false);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ui.openGameScreen();
            }
        });

        ImageIcon exitIcon = new ImageIcon("image/Exit_button_.png");
        Image exitImg = exitIcon.getImage().getScaledInstance(500, 240, Image.SCALE_SMOOTH);

        btnExit = new JButton(new ImageIcon(exitImg));
        btnExit.setBounds((w / 2) - 250, (h / 2), 500, 240);

        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);
        btnExit.setFocusPainted(false);
        btnExit.setOpaque(false);

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        ImageIcon icon = new ImageIcon("image/War.png");
        Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        bgImage = new JLabel(new ImageIcon(img));
        bgImage.setBounds(0, 0, w, h);

        ImageIcon iconidk = new ImageIcon(getClass().getResource("/image/idk.png"));
        Image imgidk = iconidk.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        idk = new JLabel(new ImageIcon(imgidk));
        idk.setBounds(w - 180, h - 160, 200, 200);

        lp.add(bgImage, Integer.valueOf(0));
        lp.add(titleImage, Integer.valueOf(1));
        lp.add(btnStart, Integer.valueOf(1));
        lp.add(btnExit, Integer.valueOf(1));
        lp.add(idk, Integer.valueOf(2));
        lp.revalidate();
        lp.repaint();

        this.add(lp);
        this.setBounds(0, 0, w, h);
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
