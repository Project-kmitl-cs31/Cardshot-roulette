<<<<<<< HEAD:src/project/Nut/Card1.java
package src.project.Nut;
=======

>>>>>>> origin/main:src/project/logic/Card1.java
public abstract class Card1 {

    protected String id;
    protected String name;
    protected CardType type;

    public Card1(String id, String name, CardType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // ใช้กับตัวเอง (เช่น เก็บไว้ / เปิดใช้) , RulesEngine rules
    public abstract void resolveKept(Player player);

    // ใช้กับเป้าหมาย (เช่น โจมตี) RulesEngine rules
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
