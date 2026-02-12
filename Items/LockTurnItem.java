package Items;

public class LockTurnItem extends Item {
    private String id = "LockTurnItem";

    public void use(Player oop){
        oop.lockTurns(lockTurns);
    }
}
