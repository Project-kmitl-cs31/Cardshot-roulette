package src.Nut1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import src.NutItem.Item;

public class GameScreen extends UIScreen {

    private PlayerPanel blueZone;
    private PlayerPanel redZone;
    private ImageIcon pleft;
    private ImageIcon pright;
    private ImageIcon selectleft;
    private ImageIcon selectright;
    private JLabel centerZone;
    private JLabel labelItem = new JLabel("", SwingConstants.CENTER);
    private JLabel selectCardtext = new JLabel("", SwingConstants.CENTER);
    private String msgItem = "";
    private JLabel selfbutton;
    private JLabel enemyButton;
    private JButton[] p1Item = new JButton[6];
    private JButton[] p2Item = new JButton[6];

    private Timer animTimer;
    private Timer msgTimer;
    private Timer cooldown;

    private JPanel cutscene = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(new Color(0, 0, 0, 200));
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }
    };

    private int lastCardCount = -1;

    JLayeredPane lp = new JLayeredPane();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    int setWidth = screenSize.width;
    int setHeight = screenSize.height;

    public GameScreen(UIManager ui) {
        super(ui);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(setWidth, setHeight));

        Imagemanager bgPanel = new Imagemanager();
        bgPanel.setBounds(0, 0, setWidth, setHeight);

        // Player 1 slot
        setUpPlayerItem(p1Item, 0);

        // Player 2 slot
        setUpPlayerItem(p2Item, 1);

        pleft = new ImageIcon(getClass().getResource("/image/pLeft.png"));
        pright = new ImageIcon(getClass().getResource("/image/pRight.png"));

        selectleft = new ImageIcon(getClass().getResource("/image/selectpLeft.png"));
        selectright = new ImageIcon(getClass().getResource("/image/selectpRight.png"));

        lp.setBounds(0, 0, setWidth, setHeight);
        blueZone = new PlayerPanel(0, 0, setWidth / 2, setHeight);
        redZone = new PlayerPanel(setWidth / 2, 0, setWidth / 2, setHeight);

        centerZone = new JLabel("CENTER", SwingConstants.CENTER);
        centerZone.setBackground(Color.GREEN);
        centerZone.setOpaque(true);
        centerZone.setBounds((setWidth / 2) - 150, (setHeight / 2) - 70, 300, 300);

        labelItem.setBounds((setWidth / 2) - 200, (setHeight / 2) - 450, 400, 300);
        labelItem.setForeground(Color.YELLOW);
        labelItem.setFont(new Font("Tahoma", Font.BOLD, 25));

        selectCardtext.setBounds((setWidth / 2) - 200, (setHeight / 2) - 400, 400, 300);
        selectCardtext.setForeground(Color.YELLOW);
        selectCardtext.setFont(new Font("Tahoma", Font.BOLD, 25));

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
        lp.add(labelItem, Integer.valueOf(3));
        lp.add(selectCardtext, Integer.valueOf(3));

        this.add(lp);

        this.setBounds(0, 0, setWidth, setHeight);
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
            blueZone.refreshFromGame(p1.getHp(), p1.getName(), "l");
        }

        Player p2 = state.getOpposingP();
        if (p2 != null) {
            redZone.refreshFromGame(p2.getHp(), p2.getName(), "r");
        }
        
        int baseY = (setHeight / 2) - 100;
        int leftX = (setWidth / 2) - 750;
        int rightX = (setWidth / 2) + 500;
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
            centerZone.setBackground(Color.CYAN);
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
            centerZone.setBackground(Color.PINK);
        }

        if (state.getDeck() != null) {
            int count = state.getDeck().getCardCount();

            if (count > lastCardCount) {
                ShowCardindeck(state.getDeck());
            }
            lastCardCount = count;

            String turnText = state.isP1Turn() ? "P1 Turn" : "P2 Turn";
            centerZone.setText("<html><center>" + turnText + "<br>Cards: " + count + "</center></html>");
            if (state.isP1Turn()) {
                selfbutton.setBounds(leftX, baseY, 250, 400);
                selfbutton.setText("Self (P1)");

                enemyButton.setBounds(rightX, baseY, 250, 400);
                enemyButton.setText("Enemy (P2)");
                centerZone.setBackground(Color.CYAN);
            } else {
                selfbutton.setBounds(rightX, baseY, 250, 400);
                selfbutton.setText("Self (P2)");

                enemyButton.setBounds(leftX, baseY, 250, 400);
                enemyButton.setText("Enemy (P1)");
                centerZone.setBackground(Color.pink);
            }
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

    // --- ส่วนที่แก้ไขให้แสดงรูปไอเทมแบบไดนามิก ---
    private void updateItemButton(JButton btn, Player p, int index) {
        if (p != null) {
            Item item = p.getItem(index);
            if (item != null) {
                String itemName = item.getName();
                try {
                    // ดึงรูปภาพตามชื่อของไอเทม
                    java.net.URL imgURL = getClass().getResource("/image/" + itemName + ".png");
                    if (imgURL != null) {
                        ImageIcon icon = new ImageIcon(imgURL);
                        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        btn.setIcon(new ImageIcon(img));
                        btn.setText(""); // ลบตัวหนังสือออก
                    } else {
                        // ถ้าหาไฟล์รูปไม่เจอ ให้แสดงชื่อเป็นตัวหนังสือแทน
                        btn.setIcon(null);
                        btn.setText(itemName);
                    }
                } catch (Exception e) {
                    btn.setIcon(null);
                    btn.setText(itemName);
                }
                btn.setVisible(true);
            } else {
                // ถ้าสล็อตว่าง
                btn.setIcon(null);
                btn.setText("");
                btn.setVisible(false);
            }
        }
    }

    // --- ส่วนที่แก้ไขให้ปุ่มไอเทมไม่มีกรอบและพื้นหลัง ---
    private void setUpPlayerItem(JButton[] btn, int pos) {
        int baseY = 220;
        for (int j = 0; j < btn.length; j++) {
            int index = j;

            int setNewX = pos != 1 ? 450 : 1100;
            int setNewY = baseY + (index * 150);
            if (index % 2 == 0) {
                setNewX = setNewX - 110;
                setNewY = baseY + (150 * (index + 1) - 1);
            }

            btn[index] = new JButton();
            btn[index].setBounds(setNewX, setNewY, 100, 100);
            
            // ทำให้ปุ่มโปร่งใส โชว์แค่รูป
            btn[index].setContentAreaFilled(false);
            btn[index].setBorderPainted(false);
            btn[index].setFocusPainted(false);
            btn[index].setOpaque(false);

            btn[index].addActionListener(e -> ui.onItemClicked(index));

            lp.add(btn[index], Integer.valueOf(2));
        }
    }

    public void setMsgItem(String text, int duration) {
        if (msgTimer != null && msgTimer.isRunning()) {
            msgTimer.stop();
        }

        this.msgItem = text;
        labelItem.setText(msgItem);

        msgTimer = new Timer(1000 * duration, e -> {
            labelItem.setText("");
        });
        msgTimer.setRepeats(false);
        msgTimer.start();
    }

    public void animtext(String text) {
        if (animTimer != null && animTimer.isRunning()) {
            animTimer.stop();
        }
        if (msgTimer != null && msgTimer.isRunning()) {
            msgTimer.stop();
        }
        selectCardtext.setText("");

        animTimer = new Timer(60, new ActionListener() {
            int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < text.length()) {
                    selectCardtext.setText(selectCardtext.getText() + text.charAt(index));
                    index++;
                } else {
                    clearTextCooldown(selectCardtext);
                    selectCardtext.repaint();
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        animTimer.start();
    }

    public void clearTextCooldown(JLabel text) {
        cooldown = new Timer(1000, e -> {
            text.setText("");
        });
        cooldown.setRepeats(false);
        cooldown.start();
    }

    public void ShowCardindeck(CentralDeck deck) {
        setMsgItem(deck.categoryDeck(), 2);
    }

    public void showBgblack() {
        lp.add(cutscene, Integer.valueOf(3));
        lp.repaint();
        Timer timer = new Timer(3200, e -> {
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
}