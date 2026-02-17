package src.NutItem;


import src.Nut1.*;

public class LockTurnItem extends Item {
    private String id = "LockTurnItem";


    public ActionResult use1(Player oop){
        return ActionResult.endTurnKeepTurn(oop.getName());
    }
}