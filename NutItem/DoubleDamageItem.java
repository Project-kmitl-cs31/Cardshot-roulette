package NutItem;
import Nut1.Game;
import Nut1.Item;
import Nut1.Player;

public class DoubleDamageItem extends Item {
    public DoubleDamageItem(){
        super("Double Damage");
    }
    @Override
    public void use(Game game){
        Player play = game.getCurrentPlayer();
        play.setDamageMultiplier(2);
    }
     
}
