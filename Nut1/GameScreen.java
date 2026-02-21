package Nut1;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class GameScreen extends UIScreen {
    private PlayerPanel blueZone; 
    private PlayerPanel redZone;  
    private JLabel centerZone;   
    private JButton selfbutton;
    private JButton enemyButton;
    private JButton p1Item1, p1Item2;
    private JButton p2Item1, p2Item2;
    private JLabel bgImage;
    private JButton btnDrawCard; 
    private JLabel lblAciton;

    public GameScreen(UIManager ui) {
        super(ui);
        this.setLayout(null); 

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int setWidth = screenSize.width;
        int setHeight = screenSize.height;

       
        centerZone = new JLabel("", SwingConstants.CENTER);
        centerZone.setFont(new Font("Monospaced", Font.BOLD, 24));
        centerZone.setForeground(Color.WHITE);
        centerZone.setBounds((setWidth/2) - 150, (setHeight/2) - 250, 300, 100);

        p1Item1 = new JButton("Empty");
        p1Item1.setBounds(50, setHeight - 150, 120, 50);
        p1Item1.addActionListener(e -> ui.onItemClicked(0));
        p1Item2 = new JButton("Empty");
        p1Item2.setBounds(180, setHeight - 150, 120, 50);
        p1Item2.addActionListener(e -> ui.onItemClicked(1));

        p2Item1 = new JButton("Empty");
        p2Item1.setBounds(setWidth - 300, setHeight - 150, 120, 50);
        p2Item1.addActionListener(e -> ui.onItemClicked(0));
        p2Item2 = new JButton("Empty");
        p2Item2.setBounds(setWidth - 170, setHeight - 150, 120, 50);
        p2Item2.addActionListener(e -> ui.onItemClicked(1));

        ImageIcon cardIcon = new ImageIcon("Carddeck.png");
        Image cardImg = cardIcon.getImage().getScaledInstance(200, 280, Image.SCALE_SMOOTH);
        btnDrawCard = new JButton(new ImageIcon(cardImg));
        btnDrawCard.setBounds((setWidth/2) - 100, (setHeight/2) - 140, 200, 280);
        btnDrawCard.setContentAreaFilled(false);
        btnDrawCard.setBorderPainted(false);
        btnDrawCard.setFocusPainted(false);
        btnDrawCard.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDrawCard.addActionListener(e -> ui.onDeckClicked());

        selfbutton = new JButton("Choose self");
        enemyButton = new JButton("Choose Enemy");
        selfbutton.addActionListener(e -> ui.onTargetSelected(true));
        enemyButton.addActionListener(e -> ui.onTargetSelected(false));

        ImageIcon p1IconOriginal = new ImageIcon("sword.png");
        Image p1img = flip(p1IconOriginal.getImage()).getScaledInstance(250, 450, Image.SCALE_SMOOTH);
        JLabel p1Label = new JLabel(new ImageIcon(p1img));
        p1Label.setBounds(50, (setHeight / 2) - 225, 250, 450);

        ImageIcon p2IconOriginal = new ImageIcon("sword.png");
        Image p2img = (p2IconOriginal.getImage()).getScaledInstance(250, 450, Image.SCALE_SMOOTH);
        JLabel p2Label = new JLabel(new ImageIcon(p2img));
        p2Label.setBounds(setWidth - 250 - 100, (setHeight / 2) - 225, 250, 450);

        ImageIcon bgIcon = new ImageIcon("Deck.png");
        Image bgImg = bgIcon.getImage().getScaledInstance(setWidth, setHeight, Image.SCALE_SMOOTH);
        bgImage = new JLabel(new ImageIcon(bgImg));
        bgImage.setBounds(0, 0, setWidth, setHeight);

        blueZone = new PlayerPanel(0, 0, setWidth / 2, 100, Color.BLUE);
        redZone = new PlayerPanel(setWidth / 2, 0, setWidth / 2, 100, Color.RED);
        blueZone.setOpaque(false);
        redZone.setOpaque(false);

        JLayeredPane lp = new JLayeredPane();
        lp.setBounds(0, 0, setWidth, setHeight);

        lp.add(bgImage, Integer.valueOf(0));  
        lp.add(p1Label, Integer.valueOf(1));     
        lp.add(p2Label, Integer.valueOf(1));  
        lp.add(centerZone, Integer.valueOf(1));  
        lp.add(btnDrawCard, Integer.valueOf(2)); 
        lp.add(selfbutton, Integer.valueOf(2));
        lp.add(enemyButton, Integer.valueOf(2));
        lp.add(p1Item1, Integer.valueOf(2));
        lp.add(p1Item2, Integer.valueOf(2));
        lp.add(p2Item1, Integer.valueOf(2));
        lp.add(p2Item2, Integer.valueOf(2));
        lp.add(blueZone, Integer.valueOf(2));
        lp.add(redZone, Integer.valueOf(2));

        lblAciton = new JLabel("Game Started!", SwingConstants.CENTER);
        lblAciton.setFont(new Font("Monospaced", Font.BOLD, 40));
        lblAciton.setForeground(Color.green);
        lblAciton.setBounds(0 , 100 ,setWidth , 50);
        lp.add(lblAciton , Integer.valueOf(3));

        this.add(lp);
        this.setBounds(0, 0, setWidth, setHeight);

        
    }

    @Override
    public void render(Game state) {
        if (state == null || centerZone == null) return;

    
        if (state.getPlayer() != null) blueZone.refreshFromGame(state.getPlayer().getHp(), state.getPlayer().getName());
        if (state.getEnemy() != null) redZone.refreshFromGame(state.getEnemy().getHp(), state.getEnemy().getName());
        if (state.isTargetSelf()) {
            selfbutton.setBackground(Color.GREEN);
            enemyButton.setBackground(Color.GRAY);
        } else {
            selfbutton.setBackground(Color.GRAY);
            enemyButton.setBackground(Color.RED);
        }

        if (state.getDeck() != null) {
            int count = state.getDeck().getCardCount();
            String turnText = state.isP1Turn() ? "P1 Turn" : "P2 Turn";
            centerZone.setText("<html><center><font color='yellow'>" + turnText + "</font><br>Cards: " + count + "</center></html>");
            
            Dimension sz = Toolkit.getDefaultToolkit().getScreenSize();
            int buttonY = (sz.height / 2) + 160;
            if (state.isP1Turn()) {
                selfbutton.setBounds((sz.width/2) - 250, buttonY, 130, 50);
                enemyButton.setBounds((sz.width/2) + 120, buttonY, 130, 50);
            } else {
                selfbutton.setBounds((sz.width/2) + 120, buttonY, 130, 50);
                enemyButton.setBounds((sz.width/2) - 250, buttonY, 130, 50);
            }
        }
        if(state != null){
            lblAciton.setText(state.getActionMessage());
        }

        updateItemButton(p1Item1, state.getPlayer(), 0);
        updateItemButton(p1Item2, state.getPlayer(), 1);
        updateItemButton(p2Item1, state.getEnemy(), 0);
        updateItemButton(p2Item2, state.getEnemy(), 1);

        boolean isP1 = state.isP1Turn();
        p1Item1.setEnabled(isP1); p1Item2.setEnabled(isP1);
        p2Item1.setEnabled(!isP1); p2Item2.setEnabled(!isP1);
    }

    private void updateItemButton(JButton btn, Player p, int index) {
        if (p != null) {
            Item item = p.getItem(index);
            if (item != null) {
                btn.setText(item.getName());
                btn.setVisible(true);
            } else {
                btn.setVisible(false);
            }
        }
    }

    private Image flip(Image img) {
        BufferedImage bimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bimg.createGraphics();
        g.drawImage(img, img.getWidth(null), 0, -img.getWidth(null), img.getHeight(null), null);
        g.dispose();
        return bimg;
    }

    @Override public void show() { 
        this.setVisible(true);
     }
    @Override public void hide() { 
        this.setVisible(false); 
    }
}