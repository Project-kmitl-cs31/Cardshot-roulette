package src.NutItem;
import src.Nut1.Player;

public class HealthPotionItem extends Item {
    public void use(Player player){
        if(player.getHp() < 6){
            player.setHp(1);
        }
    } 
}