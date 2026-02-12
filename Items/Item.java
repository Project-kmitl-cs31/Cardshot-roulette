package Items;

public abstract class Item extends CentralDeck {
    private String id;

    public abstract void use(Player player);
}
