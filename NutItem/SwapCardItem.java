package NutItem;

import Nut1.Game;
import Nut1.Item;

public class SwapCardItem extends Item {
    public SwapCardItem(){
        super("Swap Card");
    }
    public void use(Game game){
        if(game.getDeck() != null && !game.getDeck().isEmpty())
            game.getDeck().swapCard();
}
    }
