package Nut;
import CoreGame.Game;
import CoreGame.Player;

public class test {
    public static void main(String[] args) {
        Game game = new Game();
        Player p1 = new Player("01", "haha", 6, 2);
        HealthPotionItem health = new HealthPotionItem();
        UIManager uiManager = new UIManager();
        uiManager.bindGame(game);
        uiManager.openGameScreen();

        // CentralDeck cn = new CentralDeck();
        // cn.generate(5-1);
        // cn.cutTop();
    }
}
