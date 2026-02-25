package src.NutItem;

import src.Nut1.Game;
import src.Nut1.Player;

public class ResetItem extends Item {

    public ResetItem() {
        super("ResetItem", "src/Nut1/sound/health.wav");
    }

    @Override
    public void use(Game game) {
        
        Player player = game.getCurrentPlayer();
        int count = player.getItemCount();
      
    if(count >= 1 ){
        player.removeItem(count-1);
        Item newItem = game.getRandomItemNoOld(new ResetItem());
        player.addItem(newItem);
        game.getUIManager().getGameScreen().animtext("" + player.getName() + " Reset Card. ", null);
        player.getAllItem();
    }
    else {
        player.addItem(new ResetItem());
    }
    if (game.getUIManager().getGameScreen() != null) {
        game.getUIManager().getGameScreen().revalidate();
        game.getUIManager().getGameScreen().repaint();
    }
    }
}
