package src.NutItem;

import src.Nut1.Card1;
import src.Nut1.Game;

public class PeekCardItem extends Item {

    public PeekCardItem() {
        super("PeekCard", "src/Nut1/sound/health.wav");
    }

    @Override
    public void use(Game game) {
        if (game.getDeck() != null && !game.getDeck().isEmpty()) {
            Card1 c = game.getDeck().peekTop();
            game.getUIManager().getGameScreen().animtext("Top Card :" + c.getClass().getSimpleName(), c.getsourceImg());
        }
    }
}
