package src.NutItem;
import src.Nut1.Game;



public class PeekCardItem extends Item {
    public PeekCardItem(){
        super("PeekCard");
    }
    @Override
    public void use(Game game){
        if(game.getDeck() != null && !game.getDeck().isEmpty()) {
            Card1 c = game.getDeck().peekTop();
            game.getUIManager().getGameScreen().setMsgItem("Top Card :"+c.getClass().getSimpleName(),2);
            // System.out.println(c.getClass().getSimpleName());
        }
        }
    }




