package Nut1;

import java.awt.*;
import javax.swing.*;

public class GameScreen extends UIScreen {
    private PlayerPanel blueZone; 
    private PlayerPanel redZone;  
    private JLabel centerZone;   
    private JButton selfbutton;
    private JButton enemyButton;

    public GameScreen(UIManager ui) {
        super(ui);
        this.setLayout(null); 

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int setWidth = screenSize.width;
        int setHeight = screenSize.height;

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
       
    }
}