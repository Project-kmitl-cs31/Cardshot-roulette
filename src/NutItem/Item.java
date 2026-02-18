package src.NutItem;
import src.Nut1.Game;


public abstract class Item {
    protected String id;
    protected String name;

    public void use(Game game){}

    public Item(){}
    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
   

}
