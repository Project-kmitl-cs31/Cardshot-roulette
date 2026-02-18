package src.NutItem;
import src.Nut1.*;

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