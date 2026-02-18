package src.Nut1;

import java.awt.*;
import javax.swing.*;
import src.NutItem.Item;

public class GameScreen extends UIScreen {
    private PlayerPanel blueZone; 
    private PlayerPanel redZone;  
    private JLabel centerZone;   
    private JButton selfbutton;
    private JButton enemyButton;
    private JButton p1Item1 , p1Item2;
    private JButton p2Item1 , p2Item2;

    public GameScreen(UIManager ui) {
        super(ui);
        this.setLayout(null); 

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int setWidth = screenSize.width;
        int setHeight = screenSize.height;

        p1Item1 = new JButton("Empty");
        p1Item1.setBounds(50 , setHeight - 150 , 120 , 50);
        p1Item1.addActionListener(e -> ui.onItemClicked(0));
        p1Item2 = new JButton("Empty");
        p1Item2.setBounds(180 , setHeight - 150 , 120 , 50);
        p1Item2.addActionListener(e -> ui.onItemClicked(1));

        p2Item1 = new JButton("Empty");
        p2Item1.setBounds(setWidth - 300 , setHeight -150 , 120 ,50);
        p2Item1.addActionListener(e -> ui.onItemClicked(0));
        p2Item2 = new JButton("Empty");
        p2Item2.setBounds(setWidth - 170 , setHeight - 150 , 120 , 50);
        p2Item2.addActionListener(e -> ui.onItemClicked(1));

        JLayeredPane lp = new JLayeredPane();
        lp.setBounds(0, 0, setWidth, setHeight);
        blueZone = new PlayerPanel(0, 0, setWidth / 2, setHeight, Color.BLUE);
        redZone = new PlayerPanel(setWidth / 2, 0, setWidth / 2, setHeight, Color.RED);      
        centerZone = new JLabel("CENTER", SwingConstants.CENTER);
        centerZone.setBackground(Color.GREEN);
        centerZone.setOpaque(true);
        centerZone.setBounds((setWidth / 2) - 150, (setHeight / 2) - 150, 300, 300);


        selfbutton = new JButton("Choose self");
        selfbutton.setBackground(Color.white);

        enemyButton = new JButton("Choose Enemy");
        enemyButton.setBackground(Color.BLACK);
        enemyButton.setForeground(Color.WHITE);

        selfbutton.addActionListener(e -> ui.onTargetSelected(true));
        enemyButton.addActionListener(e -> ui.onTargetSelected(false));
        
        centerZone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    ui.onDeckClicked(); 
                }
            }
        });

        lp.add(blueZone, Integer.valueOf(0));
        lp.add(redZone, Integer.valueOf(0)); 
        lp.add(centerZone, Integer.valueOf(1));
        lp.add(selfbutton, Integer.valueOf(2));
        lp.add(enemyButton, Integer.valueOf(2));
        lp.add(p1Item1, Integer.valueOf(2));
        lp.add(p1Item2, Integer.valueOf(2));
        lp.add(p2Item1, Integer.valueOf(2));
        lp.add(p2Item2, Integer.valueOf(2));

        
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
        
        Player p2 = state.getEnemy();
        if(p2 != null){
            redZone.refreshFromGame(p2.getHp(),p2.getName());
        }
        if(state.isTargetSelf()){
            selfbutton.setBackground(Color.green);
            enemyButton.setBackground(Color.black);
        }else{
            selfbutton.setBackground(Color.black);
            enemyButton.setBackground(Color.red);
        }
        if(state.isTargetSelf()){
            selfbutton.setBackground(Color.green);
            enemyButton.setBackground(Color.black);
        }else{
            selfbutton.setBackground(Color.black);
            enemyButton.setBackground(Color.red);
        }
        if(state.getDeck() != null){
            int count = state.getDeck().getCardCount();
            String turnText = state.isP1Turn() ? "P1 Turn" : "P2 Turn";
            centerZone.setText("<html><center>" + turnText + "<br>Cards: " + count + "</center></html>");
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int w = screenSize.width;
            int h = screenSize.height;
            int buttonY = (h / 2) + 160;
            int leftX = (w / 2) - 300;
            int rightX = (w / 2) + 160;
            if(state.isP1Turn()){
                selfbutton.setBounds(leftX, buttonY, 130, 50);
                selfbutton.setText("Self (P1)");

                enemyButton.setBounds(rightX, buttonY, 130 , 50);
                enemyButton.setText("Enemy (P2)");
                centerZone.setBackground(Color.CYAN);
            }else{
                selfbutton.setBounds(rightX, buttonY, 130, 50);
                selfbutton.setText("Self (P2)");

                enemyButton.setBounds(leftX, buttonY, 130, 50);
                enemyButton.setText("Enemy (P1)");  
                centerZone.setBackground(Color.pink);
            }
        }
        Player p = state.getPlayer();
        Player enemy = state.getEnemy();
        
        updateItemButton(p1Item1, p, 0);
        updateItemButton(p1Item2, p, 1);
        updateItemButton(p2Item1, enemy, 0);
        updateItemButton(p2Item2, enemy, 1);
       
        if(state.isP1Turn()){
            p1Item1.setEnabled(true);
            p1Item2.setEnabled(true);
            p2Item1.setEnabled(false);
            p2Item2.setEnabled(false);
        }else{
            p1Item1.setEnabled(false);
            p1Item2.setEnabled(false);
            p2Item1.setEnabled(true);
            p2Item2.setEnabled(true);
        }
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
}