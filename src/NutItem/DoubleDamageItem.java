package src.NutItem;

import src.Nut1.Player;

public class DoubleDamageItem extends Item {
    @Override
    public void use(Player player){
        player.setDamageMultiplier(2);
    }
     
}
