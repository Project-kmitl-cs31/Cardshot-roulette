package src.NutItem;
import src.Nut1.Game;
import src.Nut1.Player;

public class LockTurnItem extends Item { 
    public LockTurnItem() {
        super("Lock Turn Item");
    }

    @Override
    public void use(Game game) {
        Player opponent = game.isP1Turn() ? game.getOpposingP() : game.getPlayer();

        opponent.lockTurn(); 
        
        System.out.println(opponent.getName() + " will be locked for 1 turn!");
    }
}