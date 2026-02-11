package Item;

public class PeekCardItem extends Item {
    private String id = "CutCard";

    public void use(){
        System.out.println(peekCurrent());
        ActionResult action = new ActionResult(false, null);
        action.continueTurn()
    }
}

