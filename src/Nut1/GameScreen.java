package src.Nut1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import javax.swing.border.Border;
import src.NutItem.Item;

public class GameScreen extends UIScreen {

    private PlayerPanel blueZone;
    private PlayerPanel redZone;
    private ImageIcon pleft = new ImageIcon(getClass().getResource("/image/pLeft.png"));
    private ImageIcon pright = new ImageIcon(getClass().getResource("/image/pRight.png"));

    private ImageIcon selectleft = new ImageIcon(getClass().getResource("/image/selectpLeft.png"));
    private ImageIcon selectright = new ImageIcon(getClass().getResource("/image/selectpRight.png"));
    private JLabel centerZone;
    private volatile Boolean isCenterZone = false;
    Border defaultBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    Border hoverBorder = BorderFactory.createLineBorder(Color.BLUE, 2);

    Image ImgCircleP1 = new ImageIcon(getClass().getResource("/image/circleblue.png")).getImage();
    Image ImgCircleP2 = new ImageIcon(getClass().getResource("/image/circlered.png")).getImage();

    Image resizedImg1 = ImgCircleP1.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
    Image resizedImg2 = ImgCircleP2.getScaledInstance(300, 300, Image.SCALE_SMOOTH);

    private JLabel labelItem = new JLabel("", SwingConstants.CENTER);
    private JLabel selectCardtext = new JLabel("", SwingConstants.CENTER);
    private JLabel selfbutton;
    private JLabel enemyButton;

    float alpha = 1f;

    private JButton[] p1Item = new JButton[6];
    private JButton[] p2Item = new JButton[6];

    private Timer animTimer;
    private Timer msgTimer;
    private Timer cooldown;
    private boolean isAciveAnim = false;

    private JPanel fadeScreen;
    private Timer fadeTimer;

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

    private useSound sound;
    private JLabel togglesound = new JLabel("", SwingConstants.CENTER);
    private boolean isMute = false;
    Image togglesoundIcon = new ImageIcon(getClass().getResource("/image/audiopic.png")).getImage();
    Image resizedSound = togglesoundIcon.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

    private MessageOverlay messageOverlay;
    private TurnColorOverlay turnColorOverlay;

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
        turnColorOverlay = new TurnColorOverlay(setWidth, setHeight);
        this.sound = new useSound();
        Timer time = new Timer(800, e -> {
            sound.loopSound("src/Nut1/sound/musicbg.wav", "start");
        });
        time.setRepeats(false);
        time.start();

        bgPanel.setBounds(0, 0, setWidth, setHeight);

        messageOverlay = new MessageOverlay(lp, setWidth, setHeight);

        // Player 1 slot
        setUpPlayerItem(p1Item, 0);

        // Player 2 slot
        setUpPlayerItem(p2Item, 1);

        lp.setBounds(0, 0, setWidth, setHeight);
        blueZone = new PlayerPanel(0, 0, setWidth / 2, setHeight, "l");
        redZone = new PlayerPanel(setWidth / 2, 0, setWidth / 2, setHeight, "r");

        centerZone = new JLabel("", SwingConstants.CENTER);
        centerZone.setOpaque(false);
        centerZone.setBounds((setWidth / 2) - 150, (setHeight / 2) - 20, 300, 300);

        labelItem.setBounds((setWidth / 2) - 200, (setHeight / 2) - 300, 400, 300);
        labelItem.setForeground(Color.YELLOW);
        labelItem.setFont(new Font("Tahoma", Font.BOLD, 25));

        selectCardtext.setBounds((setWidth / 2) - 250, (setHeight / 2) - 350, 500, 300);
        selectCardtext.setForeground(Color.YELLOW);
        selectCardtext.setFont(new Font("Tahoma", Font.BOLD, 25));


        Carddraw.setBounds((setWidth / 2) - 80, (setHeight / 2) - 170, 500, 500);

        togglesound.setBounds((setWidth) - 150, (setHeight) - 130, 90, 90);
        togglesound.setIcon(new ImageIcon(resizedSound));
        togglesound.setOpaque(false);

        togglesound.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (sound != null) {
                    isMute = !isMute;
                    sound.loopSound("src/Nut1/sound/musicbg.wav", isMute ? "stop" : "start");
                }
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                togglesound.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                togglesound.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

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

        centerZone.setEnabled(true);
        centerZone.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (isCenterZone && SwingUtilities.isLeftMouseButton(e)) {
                    ui.onDeckClicked();
                    bgPanel.playanim();
                    showBgblack(4);
                }
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                centerZone.setBorder(hoverBorder); 
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                centerZone.setBorder(defaultBorder); 
            }

          
        });

        lp.add(bgPanel, Integer.valueOf(-1));
        lp.add(blueZone, Integer.valueOf(0));
        lp.add(redZone, Integer.valueOf(0));
        lp.add(centerZone, Integer.valueOf(1));
        lp.add(selfbutton, Integer.valueOf(2));
        lp.add(enemyButton, Integer.valueOf(2));
        lp.add(playerDamage, Integer.valueOf(3));
        lp.add(Carddraw, Integer.valueOf(3));
        lp.add(labelItem, Integer.valueOf(3));
        lp.add(selectCardtext, Integer.valueOf(3));
        lp.add(togglesound, Integer.valueOf(4));
        lp.add(turnColorOverlay, Integer.valueOf(5));

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

        MouseAdapter blockClick = new MouseAdapter() {
        };
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
        System.out.println("Hello  P1 "+p1.getItemCount());

        if (p1 != null) {
            blueZone.refreshFromGame(p1.getHp(), p1.getName(), "l");
        }

        Player p2 = state.getOpposingP();
        System.out.println("Hello  P2 "+p2.getItemCount());
        if (p2 != null) {
            redZone.refreshFromGame(p2.getHp(), p2.getName(), "r");

        }

        boolean isP1Turn = state.isP1Turn();
        boolean isTargetSelf = state.isTargetSelf();

        if (isP1Turn) {
            changeHoverColor(Color.BLUE);
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
            changeHoverColor(Color.RED);
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

            if (count > lastCardCount) {
                ShowCardindeck(state.getDeck(), 4);
            }

            lastCardCount = count;
            Timer time = new Timer(1200, e -> {
                bgPanel.TurnText(state);
            });
            time.setRepeats(false);
            time.start();

            if (state.isP1Turn()) {
                turnColorOverlay.swapTurnColor("blue");
                selfbutton.setBounds(leftX, baseY, 250, 400);
                enemyButton.setBounds(rightX, baseY, 250, 400);
                chageIcondelay(resizedImg1);
            } else {
                turnColorOverlay.swapTurnColor("red");
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

    public void chageIcondelay(Image img) {
        Timer time = new Timer(1800, e -> {
            centerZone.setIcon(new ImageIcon(img));
        });
        time.setRepeats(false);
        time.start();
        this.repaint();
    }
    public void changeHoverColor(Color color){
        hoverBorder = BorderFactory.createLineBorder(color, 2);
    }

    private void setUpPlayerItem(JButton[] btn, int pos) {
        int baseY = 330;
        for (int j = 0; j < btn.length; j++) {
            int index = j;

            int setNewX = pos != 1 ? 450 : 1100;
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

            btn[index].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    btn[index].setBorderPainted(true);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    btn[index].setBorderPainted(false);
                }
            });

            btn[index].addActionListener(e -> {
                if(!isAciveAnim){
                    ui.onItemClicked(index);
                }
            });

            lp.add(btn[index], Integer.valueOf(2));
        }
        this.repaint();
    }

    public void setMsgItem(String text, double duration) {
        messageOverlay.setMsgItem(text, duration);
    }

    public void setMsgCard(CentralDeck deck, String text, int duration) {
        messageOverlay.setMsgCard(deck, text, duration, () -> {
            this.isCenterZone = true;
        });
    }
    public MessageOverlay getOverlay(){
        return this.messageOverlay;
    }

    public void animtext(String text, String img) {
        if (animTimer != null && animTimer.isRunning()) {
            animTimer.stop();
        }
        if (msgTimer != null && msgTimer.isRunning()) {
            msgTimer.stop();
        }
        if (img == null) {
            img = "";
        }
        this.isAciveAnim = true;

        ImageIcon icon = new ImageIcon(getClass().getResource(img));
        Image sizeIcon = icon.getImage().getScaledInstance(180, 250, Image.SCALE_SMOOTH);
        Carddraw.setVisible(true);

        this.revalidate();
        this.repaint();

        selectCardtext.setText("");

        animTimer = new Timer(60, new ActionListener() {
            int index = 0;
            Timer showcard1 = new Timer(1100, e -> {

                Carddraw.setIcon(new ImageIcon(sizeIcon));
                Timer duration = new Timer(2000, e1 -> {
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
                    isAciveAnim = false;
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
            isAciveAnim = false;
        });
        cooldown.setRepeats(false);
        cooldown.start();
    }

    public void ShowCardindeck(CentralDeck deck, int duration) {
        showBgblack(5);
        Timer delay = new Timer(2300, e -> {
            setMsgCard(deck, deck.categoryDeck(), duration);
        });
        delay.setRepeats(false);
        delay.start();
    }

    public void showBgblack(int duration) {
        lp.add(cutscene, Integer.valueOf(3));
        lp.repaint();
        Timer timer = new Timer(1000 * duration, e -> {
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

    public void damageSelf(Player target) {
        String imgPath = target.getName().equals("P1") ? "/image/pLeftD.png" : "/image/pRightD.png";
        ImageIcon icon = new ImageIcon(getClass().getResource(imgPath));
        playerDamage.setVisible(true);
        if (target.getName().equals("P1")) {
            playerDamage.setIcon(icon);
            playerDamage.setBounds(leftX + 5, baseY + 40, 400, 300);
        } else {
            playerDamage.setIcon(icon);
            playerDamage.setBounds(rightX - 1, baseY + 40, 400, 300);
        }

        this.revalidate();
        this.repaint();
        Timer delaydamage = new Timer(1800, e -> {
            playerDamage.setIcon(null);
            this.repaint();
        });
        delaydamage.setRepeats(false);
        delaydamage.start();
        this.repaint();
    }
    public void update(){
        
        this.revalidate();
        this.repaint();
    }
}
