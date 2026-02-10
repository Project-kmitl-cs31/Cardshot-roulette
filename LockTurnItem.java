

public class LockTurnItem extends Item {

    private int lockTurns = 1;

    public LockTurnItem() {
        this.manaCost = 2;
        this.name = "Lock Turn";
    }

    @Override
    protected void applyEffect(ItemContext ctx) {
        ctx.opponent.lockTurns(lockTurns);
    }
}
