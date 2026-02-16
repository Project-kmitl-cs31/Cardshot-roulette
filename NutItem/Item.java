package NutItem;
import Nut1.Player;
public abstract class Item {
    protected String id;
    protected String name;

    public abstract void use(Player player);

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
