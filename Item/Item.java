package Item;

public abstract class Item {
    private String id;

    public abstract boolean canUse(Player player);
    public abstract void use(Player player);
}
