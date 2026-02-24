package src.NutItem;


import src.Nut1.*;
 
public class SwapCardItem extends Item {
    public SwapCardItem(){
        super("SwapCard","src/Nut1/sound/health.wav");
    }
    public void use(Game game){
        if(game.getDeck() != null && !game.getDeck().isEmpty())
            game.getDeck().swapCard();
}
    }
