<<<<<<< HEAD:test.java
=======
package Nut;
import CoreGame.Game;
import CoreGame.Player;

>>>>>>> a4dde4300e94ccc352159d3e73ee4965417dfa11:Nut/test.java
public class test {
    public static void main(String[] args) {
        Game game = new Game();
       
        Player p1 = new Player("01", "haha", 6, 2);
        Player p2 = new Player("02", "mmeme", 6, 2);
        HealthPotionItem health = new HealthPotionItem();
        game.startNewgame(p1,p2);
        ItemContext ctx = new ItemContext(p1, p2);
        p1.UseItem(ctx,p1.firstItem());
        // CentralDeck cn = new CentralDeck();
        // cn.generate(5-1);
        // cn.cutTop();
    }
}
