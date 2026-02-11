package Item;

public class PeekCardItem extends Item {
    private String id = "CutCard";

    public void use(Player player){
        peekCurrent(); 
        ActionResult action = new ActionResult(false, null);
        action.continueTurn()
    }
}

