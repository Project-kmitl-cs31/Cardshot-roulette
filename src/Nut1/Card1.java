package src.Nut1;

public abstract class Card1 {

    protected String id;
    protected String name;
    protected CardType type;
    protected String sourceImg;

    public Card1(String id, String name, CardType type,String source) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.sourceImg = source;
    }

    public abstract void resolveKept(Player player);

    public abstract void resolveTargeted(
            Player attacker,
            Player defender
    );

    public CardType getType() {
        return type;
    }
    
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }


    
    public String getsourceImg(){
       return this.sourceImg;
    }
    @Override
    public String toString() {
        return "[" + id + "] " + name + " (" + type + ")";
     }

}
