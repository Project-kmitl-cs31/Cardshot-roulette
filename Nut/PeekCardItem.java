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

