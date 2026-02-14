package Items;
import Nut.*;

public class DoubleDamageItem extends Item {
 
    private String id = "DoubleDamageItem";

    public void use(Player player) {
        player.setDamageMultiplier(1);
    }
}
