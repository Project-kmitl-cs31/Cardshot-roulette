package Nut;

import Item.Card1;

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
