package NutItem;

import Nut1.Game;
import Nut1.Item;
import Nut1.Player;

public class HealthPotionItem extends Item {
    public HealthPotionItem(){
        super("Health Potion");
    }
    public void use(Game game){
        Player play = game.getCurrentPlayer();
        if(play.getHp() < 6){
            play.setHp(1);
        }
    } 
}