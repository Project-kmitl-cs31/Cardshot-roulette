package Nut1;

public class HealthPotionItem extends Item {

    private int healAmount = 1;

    public HealthPotionItem() {
        this.manaCost = 1;
        this.name = "Health Potion";
    }

    @Override
    protected void applyEffect(ItemContext ctx) {
        ctx.getUser().heal(healAmount,manaCost);
        System.out.println(this.name);
    }
}
