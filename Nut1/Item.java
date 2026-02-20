package Nut1;
public abstract class Item {
    protected String id;
    protected String name;

    public abstract void use(Game game);

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
