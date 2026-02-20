package NutItem;

import Nut1.Card1;
import Nut1.Game;
import Nut1.Item;

public class CutCardItem extends Item {
    public CutCardItem(){
        super("CutCard");
    }
    @Override
    public void use(Game game){
           if(game.getDeck() != null && !game.getDeck().isEmpty()) {
            Card1 c = game.getDeck().cutTop();
        }
    }
}
