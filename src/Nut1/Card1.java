package src.Nut1;

public abstract class Card1 {

    protected String id;
    protected String name;
    protected CardType type;

    public Card1(String id, String name, CardType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public abstract void resolveKept(Player player);

    public abstract void resolveTargeted(
            Player attacker,
            Player defender
    );

    // getter เผื่อใช้
    public CardType getType() {
        return type;
    }
    
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    @Override
    public String toString() {
        return "[" + id + "] " + name + " (" + type + ")";
     }

}
