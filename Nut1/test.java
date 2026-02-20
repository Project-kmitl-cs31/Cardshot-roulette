package Nut1;

import NutItem.HealthPotionItem;

public class test {
    public static void main(String[] args) {
        Game game = new Game();
        Player p1 = new Player( "P1", 6, 2);      
        Player p2 = new Player("P2", 6, 2);  
        HealthPotionItem health = new HealthPotionItem();
        UIManager uiManager = new UIManager();
        uiManager.bindGame(game);
        game.setUIManager(uiManager);
        uiManager.openMainMenu();

        // CentralDeck cn = new CentralDeck();
        // cn.generate(5-1);
        // cn.cutTop();
    }
}
