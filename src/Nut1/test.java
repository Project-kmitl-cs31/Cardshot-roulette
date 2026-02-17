package src.Nut1;
// import CoreGame.Game;
// import CoreGame.Player;

import src.NutItem.HealthPotionItem;

public class test {
    public static void main(String[] args) {
        Game game = new Game();
        Player p1 = new Player( "Nut", 6, 2);      
        Player p2 = new Player("Yaya", 6, 2);   
        game.setPlayer(p1, p2);
        HealthPotionItem health = new HealthPotionItem();
        UIManager uiManager = new UIManager();
        uiManager.bindGame(game);
        uiManager.openMainMenu();

        // CentralDeck cn = new CentralDeck();
        // cn.generate(5-1);
        // cn.cutTop();
    }
}
