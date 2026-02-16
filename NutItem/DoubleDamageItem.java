package NutITem;
import Nut1.Player;

public class DoubleDamageItem extends Item {
    public void use(Player player){
        player.setDamageMultiplier(2);
    }
     
}
