package src.NutItem;

import src.Nut1.Game;
import src.Nut1.Player;

public class LockTurnItem extends Item {

    public LockTurnItem() {
        super("LockTurnItem", "src/Nut1/sound/health.wav");
    }

    @Override
    public void use(Game game) {
        Player opponent = game.isP1Turn() ? game.getOpposingP() : game.getPlayer();

        opponent.lockTurn();
        game.getUIManager().getGameScreen().animtext(opponent.getName() + " will be locked for 1 turn!", null);
    }
}
