package src.NutItem;
import src.Nut1.*;
public abstract class Item {
    protected String id;
    protected String name;

    public void use(Player player){}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public ActionResult use1(Player player) {
        return ActionResult.endTurnKeepTurn("Nothing happened");
}
}
