public class DoubleDamageItem extends Item {

    private int multiplier = 2;

    public DoubleDamageItem() {
        this.manaCost = 3;
        this.name = "Double Damage";
    }

    @Override
    protected void applyEffect(ItemContext ctx) {
        Card1 card = ctx.getCurrentCard();
        if (card instanceof AttackCard1 attack) {
            attack.multiplyDamage(multiplier);
        }
    }
}
