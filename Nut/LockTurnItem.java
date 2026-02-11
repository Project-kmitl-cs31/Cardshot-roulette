<<<<<<< HEAD:LockTurnItem.java

=======
package Nut;
>>>>>>> a4dde4300e94ccc352159d3e73ee4965417dfa11:Nut/LockTurnItem.java
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
