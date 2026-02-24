package src.NutItem;
import src.Nut1.Game;
import src.Nut1.Player;

public class DoubleDamageItem extends Item {
    public DoubleDamageItem(){
        super("DoubleDamage","src/Nut1/sound/health.wav");
    }
    @Override
    public void use(Game game){
        Player play = game.getCurrentPlayer();
        play.setDamageMultiplier(2);
    }
     
}
