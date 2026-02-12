package Items;
import logic.CentralDeck;
import logic.Player;
import src.project.Nut.Item;

public class DoubleDamageItem extends Item {
 
    private String id = "DoubleDamageItem";

    public void use(src.project.logic.Player player,Player opp,CentralDeck Deck) {
        if(Deck.peekCurrent==manacard){
            return;
         }
        else{
            player.setDamageMultiplier(1);
            player.
         }
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'use'");
    }
}
