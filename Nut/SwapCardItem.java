<<<<<<< HEAD:SwapCardItem.java
=======
package Nut;
<<<<<<< HEAD

import Item.Card1;

=======
>>>>>>> a4dde4300e94ccc352159d3e73ee4965417dfa11:Nut/SwapCardItem.java
>>>>>>> origin/main
public class SwapCardItem extends Item {

    public SwapCardItem() {
        this.manaCost = 3;
        this.name = "Swap Card";
    }

    @Override
    protected void applyEffect(ItemContext ctx) {
        Card1 random = ctx.deck.randomCard();
        ctx.deck.swapCurrent(random);
    }
}
