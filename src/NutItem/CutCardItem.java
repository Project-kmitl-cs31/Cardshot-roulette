package src.NutItem;

import src.Nut1.Card1;
import src.Nut1.Game;

public class CutCardItem extends Item {

    public CutCardItem() {
        super("CutCard", "src/Nut1/sound/cutcard.wav");
    }

    @Override
    public void use(Game game) {
        if (game.getDeck() != null && !game.getDeck().isEmpty()) {
            Card1 c = game.getDeck().cutTop();
            game.getUIManager().getGameScreen().animtext("use cut Card", null);
        }
    }
}
