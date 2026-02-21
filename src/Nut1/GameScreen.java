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
    private ImageIcon damageleft;
    private ImageIcon damageright;
    private JLabel centerZone;   
    private JLabel labelItem = new JLabel("", SwingConstants.CENTER);
    private JLabel selectCardtext = new JLabel("", SwingConstants.CENTER);
    private String msgItem= "";
    private JButton selfbutton;
    
    private JButton enemyButton;
    private JButton[] p1Item = new JButton[6];
    private JButton[] p2Item = new JButton[6];

    private Timer animTimer; 
    private Timer msgTimer;
    private Timer cooldown;

    
 



    private JPanel cutscene = new JPanel(){
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
        };
    };

    private int lastCardCount = -1;

    JLayeredPane lp = new JLayeredPane();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    int setWidth = screenSize.width;
    int setHeight = screenSize.height;

    public GameScreen(UIManager ui) {
        super(ui);
        this.setLayout(null); 
        this.setPreferredSize(new Dimension(setWidth, setHeight));

        Imagemanager bgPanel = new Imagemanager(); 
        bgPanel.setBounds(0, 0, setWidth, setHeight);
        
        // Player 1 slot
        setUpPlayerItem(p1Item,0);
        
        // Player 2 slot
        setUpPlayerItem(p2Item,1);


        pleft = new ImageIcon(getClass().getResource("/image/pLeft.png"));
        pright = new ImageIcon(getClass().getResource("/image/pRight.png"));

        damageleft = new  ImageIcon(getClass().getResource("/image/pLeftD.png"));
        damageright = new  ImageIcon(getClass().getResource("/image/pRightD.png"));
        
        lp.setBounds(0, 0, setWidth, setHeight);
        blueZone = new PlayerPanel(0, 0, setWidth / 2, setHeight);
        redZone = new PlayerPanel(setWidth / 2, 0, setWidth / 2, setHeight);      


        centerZone = new JLabel("CENTER", SwingConstants.CENTER);
        centerZone.setBackground(Color.GREEN);
        centerZone.setOpaque(true);
        centerZone.setBounds((setWidth / 2) - 150, (setHeight / 2) - 150, 300, 300);

        
        labelItem.setBounds((setWidth / 2) - 200, (setHeight / 2) - 450, 400, 300);
        labelItem.setForeground(Color.YELLOW); 
        labelItem.setFont(new Font("Tahoma", Font.BOLD, 25));

        selectCardtext.setBounds((setWidth / 2) - 200, (setHeight / 2) - 400, 400, 300);
        selectCardtext.setForeground(Color.YELLOW); 
        selectCardtext.setFont(new Font("Tahoma", Font.BOLD, 25));
        
        MouseAdapter btnEmpty = new MouseAdapter() {};
        cutscene.setBounds(0,0, setWidth, setHeight);
        cutscene.setOpaque(false);
        cutscene.addMouseListener(btnEmpty);
        cutscene.addMouseMotionListener(btnEmpty);

        selfbutton = new JButton(pleft);
        btnTransparent(selfbutton);
    
        enemyButton = new JButton(pright);
        btnTransparent(enemyButton);

        selfbutton.addActionListener(e -> ui.onTargetSelected(true));
        enemyButton.addActionListener(e -> ui.onTargetSelected(false));
        
    
            centerZone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
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
        

        if(state == null) return;
        
        Player p1 = state.getPlayer();

        if(p1 != null){
            blueZone.refreshFromGame(p1.getHp(),p1.getName());
        }
        
        Player p2 = state.getOpposingP();

        if(p2 != null){
            redZone.refreshFromGame(p2.getHp(),p2.getName());
        }
        if(state.isTargetSelf()){
            // selfbutton.setBackground(Color.green);
            // enemyButton.setBackground(Color.black);
        }else{
            selfbutton.setIcon(pleft);
            enemyButton.setIcon(damageright);
        }
        if(state.isTargetSelf()){
            selfbutton.setIcon(damageleft);
            enemyButton.setIcon(pright);
            
        }else{
            // selfbutton.setBackground(Color.black);
            // enemyButton.setBackground(new Color(204,0 , 0));
        }
        if(state.getDeck() != null){
            int currentCount = state.getDeck().getCardCount();

            if ( currentCount > lastCardCount) {
                ShowCardindeck(state.getDeck());
            }
            lastCardCount = currentCount;

            int count = state.getDeck().getCardCount();
            String turnText = state.isP1Turn() ? "P1 Turn" : "P2 Turn";
            centerZone.setText("<html><center>" + turnText + "<br>Cards: " + count + "</center></html>");

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int w = screenSize.width;
            int h = screenSize.height;
            int baseY = (h / 2) -100;
            int leftX = (w / 2) - 750;
            int rightX = (w / 2) + 500;
            if(state.isP1Turn()){
                selfbutton.setBounds(leftX, baseY, 250, 400);
                selfbutton.setText("Self (P1)");

                enemyButton.setBounds(rightX, baseY, 250 , 400);
                enemyButton.setText("Enemy (P2)");
                centerZone.setBackground(Color.CYAN);
            }else{
                selfbutton.setBounds(leftX, baseY, 250, 400);
                selfbutton.setText("Self (P2)");

                enemyButton.setBounds(rightX, baseY, 250, 400);
                enemyButton.setText("Enemy (P1)");  
                centerZone.setBackground(Color.pink);
            }
        }
        
        Player p_1 = state.getPlayer();
        Player p_2 = state.getOpposingP();
        
        boolean isP1Turn = state.isP1Turn();
        for (int i = 0; i < p1Item.length; i++) {
            updateItemButton(p1Item[i], p_1, i);
            updateItemButton(p2Item[i], p_2, i);          
            p1Item[i].setEnabled(isP1Turn);
            p2Item[i].setEnabled(!isP1Turn);
        }
        this.revalidate(); 
        this.repaint();
    }
    private void updateItemButton(JButton btn , Player p , int index){
        if(p != null){
            Item item = p.getItem(index);
            if(item != null){
                btn.setText(item.getName());
                btn.setVisible(true);
            }else{
                btn.setText("Empty");
                btn.setVisible(false);
            }
        }
    }
    private void setUpPlayerItem(JButton[]btn,int pos){
        int baseY = 220;
        for(int j =0;j<btn.length;j++){
            int index =j;
        
            int setNewX = pos != 1 ? 450 : 1100;
            int setNewY = baseY + (index * 60);
            if(index%2==0){
                setNewX = setNewX-110;
                setNewY = baseY + (60*(index+1)-1);
            }

            btn[index] = new JButton("Empty");
            btn[index].setBounds(setNewX, setNewY , 100 , 100);
            btn[index].addActionListener(e -> ui.onItemClicked(index));

            lp.add(btn[index], Integer.valueOf(2));
        }
    }

    public void setMsgItem(String text,int duration){
        // if (msgTimer != null && msgTimer.isRunning()) msgTimer.stop();
        // if (animTimer != null && animTimer.isRunning()) animTimer.stop();
        this.msgItem = text;
        labelItem.setText(msgItem);
        labelItem.repaint();
        lp.repaint();
        msgTimer = new Timer(1000*duration, e -> {
            labelItem.setText(""); 
        
        });
        msgTimer.setRepeats(false);
        msgTimer.start();
    }

    public void animtext(String text){
        // if (animTimer != null && animTimer.isRunning()) animTimer.stop();
        // if (msgTimer != null && msgTimer.isRunning()) msgTimer.stop();
        selectCardtext.setText("");

        animTimer = new Timer(60, new ActionListener(){
            int index = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(index < text.length()){
                    selectCardtext.setText(selectCardtext.getText()+ text.charAt(index));
                    index++;
                }else{
                    clearTextCooldown(selectCardtext);
                    // selectCardtext.repaint();
                    ((Timer)e.getSource()).stop();
                }
            }            
            });
            
        animTimer.start();
    }

    public void clearTextCooldown(JLabel text){
            cooldown = new Timer(1000, e -> {
                text.setText(""); 
            });
            cooldown.setRepeats(false);
            cooldown.start();
    }

    public void ShowCardindeck(CentralDeck deck){
        setMsgItem(deck.categoryDeck(),2);
    }


    public void showBgblack(){
        lp.add(cutscene,Integer.valueOf(3));
        lp.repaint(); 
        Timer timer = new Timer(3200, e -> {
            lp.remove(cutscene); 
            lp.repaint();     
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void btnTransparent(JButton btn) {
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
}
  
}


 