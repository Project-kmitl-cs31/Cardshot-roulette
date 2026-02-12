package Nut;

import CoreGame.Game;
import CoreGame.Player;
import javax.swing.JFrame;

public class UIManager {
    private Game game;
    private UIScreen activeScreen;
    private JFrame window;
    private Player p;

    public UIManager(){
        window = new JFrame("Game");
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setLocationRelativeTo(null);
    }

    public void bindGame(Game game){
        this.game = game;
    }
    public void openGameScreen(){
        GameScreen screen = new GameScreen(this);
        this.activeScreen = screen;
        window.setContentPane(screen);
        screen.render(game);
        window.revalidate();
        window.setVisible(true);
    
    }
     
    public void openItemSelect(){

    }
    public void showRoundTransition(int roundNo){

    }
    public void onDeckClicked(){
        if(game == null)
            return;
        
        game.PlayerdrawCard();
        
        if(activeScreen != null){
            activeScreen.render(game);
        }
    }
}
