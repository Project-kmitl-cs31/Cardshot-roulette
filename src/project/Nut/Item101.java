package src.project.Nut;
// public abstract class Item101 {

//     protected String id;
//     protected String name;
//     protected int manaCost;

//     public final void activate(ItemContext ctx) {
//         if (!canActivate(ctx)) {
//             throw new IllegalStateException("Cannot use item: " + name);
//         }
//         payMana(ctx.getUser());
//         applyEffect(ctx);

//     }

//     public void setName(ItemContext ctx) {
//         this.name = ctx.getUser().getName();
//     }

//     public String getName() {
//         return name;
//     }

//     protected boolean canActivate(ItemContext ctx) {
//         return ctx.requireMana(manaCost);
//     }

//     protected void payMana(Player player) {
//         player.useMana(manaCost);
//     }

//     protected abstract void applyEffect(ItemContext ctx);

//     public String getId() {
//         return id;
//     }

//     @Override
//     public boolean equals(Object o) {
//         if (this == o) {
//             return true;
//         }
//         if (!(o instanceof Card1)) {
//             return false;
//         }
//         Card1 other = (Card1) o;
//         return this.id != null && this.id.equals(other.getId());
//     }
// }
