package Nut1;
import NutItem.Item;;

public class HealthPotionItem extends Item {
    public void use(Player player){
        if(player.getHp() < 6){
            player.setHp(1);
        }
    } 
}