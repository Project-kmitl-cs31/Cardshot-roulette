package src.Nut1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import src.NutItem.Item;

public class GameScreen extends UIScreen {

    private useSound sound;

    private PlayerPanel blueZone;
    private PlayerPanel redZone;
    private ImageIcon pleft  = new ImageIcon(getClass().getResource("/image/pLeft.png")); 
    private ImageIcon pright  = new ImageIcon(getClass().getResource("/image/pRight.png")); ;
    private ImageIcon selectleft = new ImageIcon(getClass().getResource("/image/selectpLeft.png"));
    private ImageIcon selectright= new ImageIcon(getClass().getResource("/image/selectpRight.png"));
    private JLabel centerZone;

    Image ImgCircleP1 = new ImageIcon(getClass().getResource("/image/circleblue.png")).getImage();
    Image ImgCircleP2 = new ImageIcon(getClass().getResource("/image/circlered.png")).getImage();
    
    Image resizedImg1 = ImgCircleP1.getScaledInstance(300, 300, Image.SCALE_SMOOTH); 
    Image resizedImg2 = ImgCircleP2.getScaledInstance(300,300, Image.SCALE_SMOOTH);


    private JLabel labelItem = new JLabel("", SwingConstants.CENTER);
    private JLabel selectCardtext = new JLabel("", SwingConstants.CENTER);

    private String msgItem = "";
    private JLabel selfbutton;
    private JLabel enemyButton;

    float alpha = 1f;

    private JButton[] p1Item = new JButton[6];
    private JButton[] p2Item = new JButton[6];

    private Timer animTimer;
    private Timer msgTimer;
    private Timer cooldown;

    private JPanel fadeScreen;
    private Timer fadeTimer;

    JPanel msgPanel = new JPanel();
    Image atkcard;
    Image blkcard;

    JLabel playerDamage = new JLabel();
    JLabel Carddraw = new JLabel();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    int setWidth = screenSize.width;
    int setHeight = screenSize.height;

    int baseY = (setHeight / 2) - 100;
    int leftX = (setWidth / 2) - 700;
    int rightX = (setWidth / 2) + 500;

    private Imagemanager bgPanel = new Imagemanager();

    private JPanel cutscene = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(new Color(0, 0, 0, 200));
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }
    ;
    };

    private int lastCardCount = -1;

    JLayeredPane lp = new JLayeredPane();

    public GameScreen(UIManager ui) {
        super(ui);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(setWidth, setHeight));


        bgPanel.setBounds(0, 0, setWidth, setHeight);

        // Player 1 slot
        setUpPlayerItem(p1Item, 0);

        // Player 2 slot
        setUpPlayerItem(p2Item, 1);

      

   

        lp.setBounds(0, 0, setWidth, setHeight);
        blueZone = new PlayerPanel(0, 0, setWidth / 2, setHeight,"l");
        redZone = new PlayerPanel(setWidth / 2, 0, setWidth / 2, setHeight,"r");

        centerZone = new JLabel("", SwingConstants.CENTER);
        centerZone.setOpaque(false); 
        centerZone.setBounds((setWidth / 2) - 150, (setHeight / 2)-20, 300, 300);

        labelItem.setBounds((setWidth / 2) - 200, (setHeight / 2) - 300, 400, 300);
        labelItem.setForeground(Color.YELLOW);
        labelItem.setFont(new Font("Tahoma", Font.BOLD, 25));

        selectCardtext.setBounds((setWidth / 2) - 200, (setHeight / 2) - 350, 400, 300);
        selectCardtext.setForeground(Color.YELLOW);
        selectCardtext.setFont(new Font("Tahoma", Font.BOLD, 25));


        msgPanel.setLayout(null); 
        msgPanel.setOpaque(false); 
        msgPanel.setBounds((setWidth / 2) - 200, (setHeight / 2) - 450, setWidth+100, setHeight+200);


        Carddraw.setBounds((setWidth / 2)-80,(setHeight/2)-170 ,500,500);

        MouseAdapter btnEmpty = new MouseAdapter() {
        };
        cutscene.setBounds(0, 0, setWidth, setHeight);
        cutscene.setOpaque(false);
        cutscene.addMouseListener(btnEmpty);
        cutscene.addMouseMotionListener(btnEmpty);

        selfbutton = new JLabel();
        btnTransparent(selfbutton, "pLeft");

        enemyButton = new JLabel();
        btnTransparent(enemyButton, "pRight");


        selfbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    ui.onTargetSelected(true);
            

                }
            }
        });

        enemyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    ui.onTargetSelected(false);
                }
            }
        });

        centerZone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {

                    ui.onDeckClicked();

                     bgPanel.playanim(); 

                    showBgblack();

                }
            }
        });

        
        

        lp.add(bgPanel, Integer.valueOf(-1));
        lp.add(blueZone, Integer.valueOf(0));
        lp.add(redZone, Integer.valueOf(0));
        lp.add(centerZone, Integer.valueOf(1));
        lp.add(selfbutton, Integer.valueOf(2));
        lp.add(enemyButton, Integer.valueOf(2));
        lp.add(playerDamage, Integer.valueOf(3));
        lp.add(Carddraw,Integer.valueOf(3));
        lp.add(labelItem, Integer.valueOf(3));
        lp.add(selectCardtext, Integer.valueOf(3));
        lp.add(msgPanel, Integer.valueOf(3));



        fadeScreen = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    };
        fadeScreen.setBounds(0, 0, setWidth, setHeight);
        fadeScreen.setOpaque(false); 
    

        MouseAdapter blockClick = new MouseAdapter() {};
        fadeScreen.addMouseListener(blockClick);
        fadeScreen.addMouseMotionListener(blockClick);


        lp.add(fadeScreen, Integer.valueOf(10));
        this.add(lp);

        this.setBounds(0, 0, setWidth, setHeight);

        this.revalidate(); 
        this.repaint();
        startGame();

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
        if (state == null) {
            return;
        }

        Player p1 = state.getPlayer();
        if (p1 != null) {
            blueZone.refreshFromGame(p1.getHp(), p1.getName(),"l");
        }

        Player p2 = state.getOpposingP();
        if (p2 != null) {
            redZone.refreshFromGame(p2.getHp(), p2.getName(),"r");

        }

        boolean isP1Turn = state.isP1Turn();
        boolean isTargetSelf = state.isTargetSelf();
    
        if (isP1Turn) {
            selfbutton.setBounds(leftX, baseY, 250, 400);
            enemyButton.setBounds(rightX, baseY, 250, 400);
            if (isTargetSelf) {
                selfbutton.setIcon(selectleft);     
                enemyButton.setIcon(pright);        
            } else {
                selfbutton.setIcon(pleft);          
                enemyButton.setIcon(selectright);   
            }
            
             chageIcondelay(resizedImg1);

        } else {
            selfbutton.setBounds(rightX, baseY, 250, 400);
            enemyButton.setBounds(leftX, baseY, 250, 400);
            if (isTargetSelf) {
                selfbutton.setIcon(selectright);    
                enemyButton.setIcon(pleft);        
            } else {
                selfbutton.setIcon(pright);         
                enemyButton.setIcon(selectleft);    
            }
            
            chageIcondelay(resizedImg2);
            }      
   
            repaint();

            if (state.getDeck() != null) {
            int count = state.getDeck().getCardCount();

             if ( count > lastCardCount) {
                ShowCardindeck(state.getDeck(),4);
            }

            lastCardCount = count;
            Timer time = new Timer(1200,e->{
                bgPanel.TurnText(state);
            });
           time.setRepeats(false);
           time.start();

            if (state.isP1Turn()) {
                bgPanel.swapTurnColor("blue");
                selfbutton.setBounds(leftX, baseY, 250, 400);
                enemyButton.setBounds(rightX, baseY, 250, 400);
                chageIcondelay(resizedImg1);
        
     
            } else {
                 bgPanel.swapTurnColor("red");
                selfbutton.setBounds(rightX, baseY, 250, 400);
                enemyButton.setBounds(leftX, baseY, 250, 400);
                chageIcondelay(resizedImg2);

            }
            repaint();
        }
      
        Player p_1 = state.getPlayer();
        Player p_2 = state.getOpposingP();

       
        for (int i = 0; i < p1Item.length; i++) {
            updateItemButton(p1Item[i], p_1, i);
            updateItemButton(p2Item[i], p_2, i);
            p1Item[i].setEnabled(isP1Turn);
            p2Item[i].setEnabled(!isP1Turn);
        }
        this.revalidate();
        this.repaint();
      
    

    }

    private void updateItemButton(JButton btn, Player p, int index) {
        if (p != null) {
            Item item = p.getItem(index);
            if (item != null) {
                String itemName = item.getName();
                try {
                    java.net.URL imgURL = getClass().getResource("/image/" + itemName + ".png");
                    if (imgURL != null) {
                        ImageIcon icon = new ImageIcon(imgURL);
                        Image img = icon.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
                        btn.setIcon(new ImageIcon(img));
                        btn.setText(""); 
                    } else {
                        btn.setIcon(null);
                        btn.setText(itemName);
                    }
                } catch (Exception e) {
                    btn.setIcon(null);
                    btn.setText(itemName);
                }
                btn.setVisible(true);
            } else {
                btn.setIcon(null);
                btn.setText("");
                btn.setVisible(false);
            }
        }
    }

  public void startGame() {
    alpha = 1.0f;
    fadeScreen.setVisible(true);
    repaint();
    Timer delayTimer = new Timer(1500, e1 -> {
        fadeTimer = new Timer(35, e2 -> {
            alpha -= 0.04f; 
            if (alpha <= 0) {
                alpha = 0f;
                fadeTimer.stop();
                lp.remove(fadeScreen); 
                lp.repaint();
            }
            fadeScreen.repaint(); 
        });
        fadeTimer.start(); 
    });
    
    delayTimer.setRepeats(false);
    delayTimer.start(); 
}

public void chageIcondelay(Image img){
    Timer time = new Timer(1800,e->{
         centerZone.setIcon(new ImageIcon(img));
    });
    time.setRepeats(false);
    time.start();
    this.repaint();
}
 private void setUpPlayerItem(JButton[] btn, int pos) {
        int baseY = 330;
        for (int j = 0; j < btn.length; j++) {
            int index = j;

            int setNewX = pos != 1 ? 450 : 1090;
            int setNewY = baseY + (index * 60);
            if (index % 2 == 0) {
                setNewX = setNewX - 120;
                setNewY = baseY + (60 * (index + 1) - 1);
            }

            btn[index] = new JButton();
            btn[index].setBounds(setNewX, setNewY, 110, 110);
            
            btn[index].setContentAreaFilled(false);
            btn[index].setBorderPainted(false);
            btn[index].setFocusPainted(false);
            btn[index].setOpaque(false);

            btn[index].addActionListener(e -> ui.onItemClicked(index));

            lp.add(btn[index], Integer.valueOf(2));
        }
    }

    public void setMsgItem(String text, int duration) {

        this.msgItem = text;
        labelItem.setText(msgItem);

        msgTimer = new Timer(1000 * duration, e -> {
            labelItem.setText("");
        });

        msgTimer.setRepeats(false);
        msgTimer.start();
    }

    public void setMsgCard(CentralDeck deck, String text, int duration) {
   
        msgPanel.removeAll();

        String[] cardImg = deck.getAllSource();
        Image atkcard1 = new ImageIcon(getClass().getResource(cardImg[1])).getImage();
        atkcard = atkcard1.getScaledInstance(120 , 160, Image.SCALE_SMOOTH);

        Image blkcard1 = new ImageIcon(getClass().getResource(cardImg[0])).getImage();
        blkcard = blkcard1.getScaledInstance(120, 160, Image.SCALE_SMOOTH);

        ImageIcon finalBLKIcon = new ImageIcon(blkcard);
        ImageIcon finalATKIcon = new ImageIcon(atkcard);

        JLabel leftCardLabel = new JLabel(finalBLKIcon);
        JLabel rightCardLabel = new JLabel(finalATKIcon);

        JLabel countatk = new JLabel(String.valueOf(text.charAt(12)));
        JLabel countblk = new JLabel(String.valueOf(text.charAt(text.length() - 1)));

        countatk.setForeground(Color.WHITE); 
        countatk.setFont(new Font("Arial", Font.BOLD, 40)); 

        countblk.setForeground(Color.WHITE);
        countblk.setFont(new Font("Arial", Font.BOLD, 40));

        countatk.setBounds(140, 100, 60, 100);
        countblk.setBounds(240, 100, 60, 100);

        leftCardLabel.setBounds(0, 70, 120, 160);
        rightCardLabel.setBounds(280, 70, 120, 160);

        if (msgPanel.getParent() == null) {
            lp.add(msgPanel, Integer.valueOf(3));
        }

        msgPanel.revalidate();
        msgPanel.repaint();
        lp.revalidate();
        lp.repaint();
        
        msgPanel.add(leftCardLabel);
        msgPanel.add(rightCardLabel);
        msgPanel.add(countatk);
        msgPanel.add(countblk);

        Container parent = this; 
        if (msgPanel.getParent() == null) {
            parent.add(msgPanel);
        }

        msgPanel.revalidate();
        msgPanel.repaint();
        parent.revalidate();
        parent.repaint();

    if (msgTimer != null && msgTimer.isRunning() ) {

        msgTimer.stop(); 
    }
    centerZone.setEnabled(false);
    msgTimer = new Timer(1000 * duration, e -> {
        Container p = msgPanel.getParent();
        if (p != null) {
            centerZone.setEnabled(true);
            p.remove(msgPanel);
            p.revalidate();
            p.repaint();
        }
    });

    msgTimer.setRepeats(false);
    msgTimer.start();
}

    public void animtext(String text,String img) {
        if (animTimer != null && animTimer.isRunning()) {
            animTimer.stop();
        }
        if (msgTimer != null && msgTimer.isRunning()) {
            msgTimer.stop();
        }
        ImageIcon icon = new ImageIcon(getClass().getResource(img));
        Image sizeIcon = icon.getImage().getScaledInstance(180, 250, Image.SCALE_SMOOTH);
        Carddraw.setVisible(true);

        this.revalidate();
        this.repaint();

        selectCardtext.setText("");

        animTimer = new Timer(60, new ActionListener() {
            int index = 0;
            Timer showcard1 = new Timer(1100,e->{

                Carddraw.setIcon(new ImageIcon(sizeIcon));
                Timer duration = new Timer(2000,e1->{
                    Carddraw.setIcon(null);
                    repaint();
                });
                duration.setRepeats(false);
                duration.start();
            });
       

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < text.length()) {
                    selectCardtext.setText(selectCardtext.getText() + text.charAt(index));
                    index++;
                } else {
                    clearTextCooldown(selectCardtext);
                    selectCardtext.repaint();
                    bgPanel.stopanim();
                    ((Timer) e.getSource()).stop();
                }
                showcard1.setRepeats(false);
                showcard1.start();
                repaint();
            }
        });

        animTimer.start();
    }

    public void clearTextCooldown(JLabel text) {
        cooldown = new Timer(1300, e -> {
            text.setText("");
        });
        cooldown.setRepeats(false);
        cooldown.start();
    }




    public void ShowCardindeck(CentralDeck deck,int duration) {
       
        Timer delay = new Timer(2300,e->{
            setMsgCard(deck,deck.categoryDeck(), duration);
        });
        delay.setRepeats(false);
        delay.start();
    }


    public void showBgblack() {
        lp.add(cutscene, Integer.valueOf(3));
        lp.repaint();
        Timer timer = new Timer(4000, e -> {
            lp.remove(cutscene);
            
            lp.repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void btnTransparent(JLabel btn, String defaultIconPath) {

        ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/image/" + defaultIconPath + ".png"));
        btn.setIcon(defaultIcon);

    }

    public void damageSelf(Player target){
        
        String imgPath = target.getName().equals("P1") ? "/image/pLeftD.png" : "/image/pRightD.png";
        ImageIcon icon = new ImageIcon(getClass().getResource(imgPath));
        playerDamage.setVisible(true);
        if(target.getName().equals("P1")){
            playerDamage.setIcon(icon);
            playerDamage.setBounds(leftX+5, baseY+42, 400, 300);
        }else{
            playerDamage.setIcon(icon);
            playerDamage.setBounds(rightX-3, baseY+43 , 400, 300);
        }

        this.revalidate();
        this.repaint();
        Timer delaydamage = new Timer(2100,e->{
            playerDamage.setIcon(null);
            this.repaint();
        });
        delaydamage.setRepeats(false);
        delaydamage.start();
        
        this.repaint();
       
    }
  

}
