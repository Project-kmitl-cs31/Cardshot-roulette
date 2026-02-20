package NutItem;
import Nut1.Game;
import Nut1.Player;
import Nut1.Item;

public class LockTurnItem extends Item { 
    public LockTurnItem() {
        super("Lock Turn Item");
    }

    @Override
    public void use(Game game) {
        Player opponent = game.isP1Turn() ? game.getEnemy() : game.getPlayer();

        opponent.lockTurn(); 
        
        System.out.println(opponent.getName() + " will be locked for 1 turn!");
    }
}
