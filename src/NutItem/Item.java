package src.NutItem;

import src.Nut1.Game;

public abstract class Item {

    protected String id;
    protected String name;
    protected String source;

    public void use(Game game) {
    }

    public Item() {
    }

    public Item(String name, String source) {
        this.name = name;
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return this.source;
    }

}
