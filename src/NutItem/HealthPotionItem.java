package src.NutItem;

import src.Nut1.*;

public class HealthPotionItem extends Item {

    public HealthPotionItem() {
        super("HealthPotion", "src/Nut1/sound/health.wav");
    }

    public void use(Game game) {
        Player player = game.getCurrentPlayer();
        if (player.getHp() < 6) {
            game.getUIManager().getGameScreen().animtext("" + player.getName() + " has been healed for 1 HP.", null);
            player.setHp(1);
        } else {
            game.getCurrentPlayer().addItem(new HealthPotionItem());
        }
    }
}
