package logic;

import Items.Health;

public class test {
    public static void main(String[] args) {
        Game game = new Game();
       
        Player p1 = new Player("01", "haha", 6, 2);
        Player p2 = new Player("02", "mmeme", 6, 2);
        Health health = new Health();
        game.startRound(p1,p2);
        // ItemContext ctx = new ItemContext(p1, p2);
        // p1.UseItem();
        // CentralDeck cn = new CentralDeck();
        // cn.generate(5-1);
        // cn.cutTop();
    }
}


// public class test {
//     public static void main(String[] args) {
//         Game game = new Game();
//         Player p1 = new Player("01", "haha", 6, 2);
//         // HealthPotionItem health = new HealthPotionItem();
//         UIManager uiManager = new UIManager();
//         uiManager.bindGame(game);
//         uiManager.openGameScreen();

//         // CentralDeck cn = new CentralDeck();
//         // cn.generate(5-1);
//         // cn.cutTop();
//     }
// }