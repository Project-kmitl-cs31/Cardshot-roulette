package Items;

import src.project.Nut.Item;
import src.project.Nut.Player;

public class PeekCardItem extends Item {

    public void use(Player player){
        peekCurrent(); 
        ActionResult action = new ActionResult(false, null);
        action.continueTurn();
    }
}

