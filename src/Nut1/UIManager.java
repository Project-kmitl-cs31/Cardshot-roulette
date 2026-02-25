package src.Nut1;

import javax.swing.JFrame;
import javax.swing.Timer;

public class UIManager {

    private Game game;
    private UIScreen activeScreen;
    private JFrame window;
    private Player p;

    public UIManager() {
        window = new JFrame("Game");
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        
    }

    public void bindGame(Game game) {
        this.game = game;
    }

    public void openGameScreen() {
        GameScreen screen = new GameScreen(this);
        this.activeScreen = screen;
        window.setContentPane(screen);
        screen.render(game);
        window.revalidate();
        window.setVisible(true);

    }

    public GameScreen getGameScreen() {
        if (activeScreen instanceof GameScreen) {
            return (GameScreen) activeScreen;
        }
        return null;
    }


    public void onTargetSelected(boolean isSelf) {
        if (game != null) {

            game.setTargetSelf(isSelf);
            activeScreen.render(game);
        }
    }

    public void onDeckClicked() {
        if (game != null) {

            Timer timer = new Timer(1200, e -> {
                game.PlayerdrawCard();
                activeScreen.render(game);
            });

            timer.setRepeats(false);
            timer.start();
        }
    }

    public void openMainMenu() {
        MainmenuScreen menu = new MainmenuScreen(this);
        this.activeScreen = menu;
        window.setContentPane(menu);
        window.revalidate();
        window.repaint();
        window.setVisible(true);
    }

    public void openGameOverSceen(String winner) {
        GameOverScreen screen = new GameOverScreen(this, winner);
        this.activeScreen = screen;

        window.setContentPane(screen);
        window.revalidate();
        window.repaint();
        window.setVisible(true);
    }

    public void restartGame() {
        Game newGame = new Game();
        this.bindGame(newGame);
        newGame.setUIManager(this);
        this.openGameScreen();
    }

    public void onItemClicked(int index) {
        if (game != null) {
            game.PlayItem(index);
            activeScreen.render(game);
        }
    }

}
