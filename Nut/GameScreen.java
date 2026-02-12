package Nut;

import javax.swing.*;
import java.awt.*;
import CoreGame.Game;
import CoreGame.Player;
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent;

public class GameScreen extends UIScreen {
    private PlayerPanel blueZone; 
    private PlayerPanel redZone;  
    private JLabel centerZone;   

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

        lp.add(blueZone, Integer.valueOf(0));
        lp.add(redZone, Integer.valueOf(0)); 
        lp.add(centerZone, Integer.valueOf(1));
        
        this.add(lp);  
        this.setBounds(0, 0, setWidth, setHeight);

        centerZone.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(SwingUtilities.isLeftMouseButton(e)){
                    System.out.println("Deck Clicked!");
                    ui.onDeckClicked();
                }
            }
        });

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
            blueZone.refreshFromGame(p1.getHp());
        }
        
        Player p2 = state.getEnemy();
        if(p2 != null){
            redZone.refreshFromGame(p2.getHp());
        }
        if(state.getDeck() != null){
            int count = state.getDeck().getCardCount();
            String turnText = state.isP1Turn() ? "P1 Turn" : "P2 Turn";
            centerZone.setText("<html><center>" + turnText + "<br>Cards: " + count + "</center></html>");centerZone.setText("Cards : "+ count);
            if(state.isP1Turn()){
                centerZone.setBackground(Color.CYAN);
            }else{
                centerZone.setBackground(Color.pink);
            }
        }
    }
}