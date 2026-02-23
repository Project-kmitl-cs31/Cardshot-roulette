package src.NutItem;


import src.Nut1.Game;


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
