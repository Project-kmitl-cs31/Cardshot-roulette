<<<<<<< HEAD:PeekCardItem.java

=======
package Nut;
>>>>>>> a4dde4300e94ccc352159d3e73ee4965417dfa11:Nut/PeekCardItem.java
public class PeekCardItem extends Item {

    public PeekCardItem() {
        this.manaCost = 2;
        this.name = "Peek Card";
    }

    @Override
    protected void applyEffect(ItemContext ctx) {
        ctx.deck.peekCurrent();
    }
}

