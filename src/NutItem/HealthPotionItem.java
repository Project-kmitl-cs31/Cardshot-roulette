package src.NutItem;

import src.Nut1.*;

public class HealthPotionItem extends Item {

    public HealthPotionItem() {
        super("HealthPotion", "src/Nut1/sound/health.wav");
    }

    public void use(Game game) {
        Player play = game.getCurrentPlayer();
        if (play.getHp() < 6) {
            game.getUIManager().getGameScreen().animtext("" + play.getName() + " has been healed for 1 HP.", null);
            play.setHp(1);
        } else {
            game.getCurrentPlayer().addItem(new HealthPotionItem());
        }
    }
}
