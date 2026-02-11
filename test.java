public class test {
    public static void main(String[] args) {
        Game mygame = new Game();
        UIManager uiManager = new UIManager();
        uiManager.bindGame(mygame);
        uiManager.openGameScreen();
        //Game game = new Game();
       
        Player p1 = new Player("01", "haha", 6, 2);
        Player p2 = new Player("02", "mmeme", 6, 2);
        HealthPotionItem health = new HealthPotionItem();
        mygame.startNewgame(p1,p2);
        ItemContext ctx = new ItemContext(p1, p2);
        p1.UseItem(ctx,p1.firstItem());
        CentralDeck cn = new CentralDeck();
        cn.generate(5-1);
        cn.cutTop();
    }
}
