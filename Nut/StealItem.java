package Nut;


import java.util.ArrayList;
import java.util.List;

public class StealItem extends Item {

    public StealItem() {
        this.manaCost = 3;
        this.name = "Steal";
    }

    @Override
    protected void applyEffect(ItemContext ctx) {
        ctx.user.stealItemFrom(ctx.opponent);
    }
}

