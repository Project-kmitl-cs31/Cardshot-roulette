package Nut;

import javax.swing.*;
import java.awt.*;
import CoreGame.Game;
import CoreGame.Player;
public class GameScreen extends UIScreen {
    private PlayerPanel blueZone; 
    private PlayerPanel redZone;  
    private JLabel centerZone;   

    public GameScreen(UIManager ui) {
        super(ui);
        this.setLayout(null); 

        int setWidth = 1600;  
        int setHeight = 900;

        JLayeredPane lp = new JLayeredPane();
        lp.setBounds(0, 0, setWidth, setHeight);
        blueZone = new PlayerPanel(35, 0, setWidth/2, setHeight, Color.BLUE);
        redZone = new PlayerPanel(setWidth/2, 0, setWidth/2, setHeight, Color.RED);
        centerZone = new JLabel("CENTER", SwingConstants.CENTER);
        centerZone.setBackground(Color.GREEN);
        centerZone.setOpaque(true);
        centerZone.setBounds(setWidth / 2 - 150, setHeight / 2 - 150, 300, 300);

        lp.add(blueZone, Integer.valueOf(0));
        lp.add(redZone, Integer.valueOf(0)); 
        lp.add(centerZone, Integer.valueOf(1));
        this.add(lp);
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
       Player mp = state.getPlayer();
       if (mp != null) {
        blueZone.refreshFromGame(mp.getHp());
    }
    }
}
