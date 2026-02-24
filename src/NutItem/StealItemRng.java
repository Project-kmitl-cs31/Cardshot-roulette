package src.NutItem;

import java.util.Random;
import src.Nut1.Game;
import src.Nut1.Player;

public class StealItemRng extends Item {

    Random rng = new Random();

    public StealItemRng() {
        super("StealItemRng", "src/Nut1/sound/health.wav");
    }

    @Override
    public void use(Game game) {

        Player opponent = game.isP1Turn() ? game.getOpposingP() : game.getPlayer();
        if (opponent.getItemCount() != 0) {

            int indexItem = rng.nextInt(opponent.getItemCount());
            Item newItem = opponent.getItem(indexItem);
            if (game.getCurrentPlayer().getItemCount() < 7) {
                game.getUIManager().getGameScreen().animtext("" + game.getPlayer().getName() + " stole " + newItem.getName() + " from " + opponent.getName(), null);
                game.getCurrentPlayer().addItem(newItem);
                opponent.removeItem(indexItem);
            } else {
                game.getCurrentPlayer().addItem(new StealItemRng());
            }
        } else {
            game.getCurrentPlayer().addItem(new StealItemRng());

        }

    }
}
