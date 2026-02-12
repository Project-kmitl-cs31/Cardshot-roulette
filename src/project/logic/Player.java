<<<<<<< HEAD:src/project/logic/Player.java
package logic;
=======
package Nut;
>>>>>>> 0530cb408d9d43ce7cbe2b08683a39ad0aabac42:Nut/Player.java


import Items.Item;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD:src/project/logic/Player.java

=======
>>>>>>> 0530cb408d9d43ce7cbe2b08683a39ad0aabac42:Nut/Player.java
public class Player {
    // --- Attributes ตาม UML ---
    private String id;
    private String name;
    int hp;
    private int mana;
    private List<Item> items;
    private int lockedTurns;
    private int damageMultiplier = 1;
    //private Item curItem;
    // private ItemContext ctx;
    private static final int MAX_ITEMS = 8;

    public Player(int maxhp){
        this.hp = maxhp;
    }
    public Player(String id, String name, int maxHp, int initialMana) {
        this.id = id;
        this.name = name;
        this.hp = maxHp;
        this.mana = initialMana;
        this.items = new ArrayList<Item>();
        this.lockedTurns = 0;
        this.damageMultiplier = 1;
    }

    // public boolean canAct() {
    //     // สามารถเล่นได้ถ้าไม่ติด LockedTurn และยังมีชีวิตอยู่
    //     return lockedTurns <= 0 && hp > 0;
    // }

    public void beginTurn() {
        if (lockedTurns > 0) {
            lockedTurns--;
            System.out.println(name + " is locked! Turns remaining: " + lockedTurns);
        }
      
    }


    public Item firstItem(){
        return items.get(0);
    }

<<<<<<< HEAD:src/project/logic/Player.java
    public void UseItem(Object o){
        if(items.isEmpty()) return;
        this.curItem = null;
        for(Item item01 : items){
            System.out.println(o);
            System.out.println(item01);
            if(item01.equals(o)){
                System.out.println("333");
                this.curItem = item01;
                break;
            }
        }
        if(this.curItem != null){
            // import something
            // this.curItem.applyEffect();
            this.curItem = null;
        }
=======
    // public void UseItem(Object o){
    //     if(items.isEmpty()) return;
    //     this.curItem = null;
    //     for(Item item01 : items){
    //         System.out.println(o);
    //         System.out.println(item01);
    //         if(item01.equals(o)){
    //             System.out.println("333");
    //             this.curItem = item01;
    //             break;
    //         }
    //     }
    //     if(this.curItem != null){
    //         this.curItem.applyEffect(ctx);
    //         this.curItem = null;
    //     }
>>>>>>> 0530cb408d9d43ce7cbe2b08683a39ad0aabac42:Nut/Player.java
        
    // }

    public void endTurn() {
        System.out.println(name + " ends turn.");
    }

    public void addMana(int amount) {
        this.mana += amount;
    }

    public void takeDamage(int amount) {
        if (amount < 0) return;
        this.hp -= amount;
        if (this.hp < 0) this.hp = 0;
        System.out.println(name + " took " + amount + " damage. HP: " + hp);
    }

    protected void heal(int amount,int manaCost) {
        if (amount <= 0) return;
        if(this.mana < manaCost) return;
        this.hp += amount;
        this.mana -= manaCost;
        System.out.println(name + " healed " + amount + " HP. Current HP: " + hp);
        System.out.println("my mana :"+this.mana);
    }

    public void grantItems(List<Item> newItems) {
        for (Item item : newItems) {
            if (this.items.size() < MAX_ITEMS) {
                this.items.add(item);
                System.out.println(name + " got item: " + item.toString());
            } else {
                System.out.println("Inventory Full! Cannot add: " + item.toString());
            }
        }
    }

    public boolean useMana(int cost) {
        if (this.mana >= cost) {
            this.mana -= cost;
            return true;
        }
        System.out.println("Not enough mana!");
        return false;
    }

    public void addDamageMultiplier(int mult, boolean forNextCard) {
        this.damageMultiplier *= mult;
        System.out.println(name + " damage multiplier is now x" + this.damageMultiplier);
    }

    // --- Getters / Setters เพิ่มเติมเพื่อให้ Item Class อื่นๆ ทำงานได้ ---
    
    public int getMana() { return mana; }
    
    // สำหรับ LockTurnItem
    public int getLockedTurns() { return lockedTurns; }
    public void setLockedTurns(int lockedTurns) { this.lockedTurns = lockedTurns; }

    public Item removeRandomItem(Item item1) {
        if (items.isEmpty()) return null;
        for(Item item : items){
            if(item.getId().equals(item1.getId())){
                items.remove(item);
                return item;
            }
        }
        return null;
    }
    
    public void addItem(Item item) {
        if (this.items.size() < MAX_ITEMS) {
            this.items.add(item);
        }
    }
    
    public String getName(){
        return name;
    }

    public int getHp(){
        return hp;
    }

    public void setHp(int hp){
        this.hp += hp;
    }

    public int getDamageMultiplier(){
        return damageMultiplier;
<<<<<<< HEAD:src/project/logic/Player.java
      }
=======
    }
    public void resetDamageMultiplier() {
        this.damageMultiplier = 1;
        System.out.println(name + " damage multiplier reset to 1");
}
<<<<<<< HEAD:src/project/logic/Player.java
>>>>>>> 0530cb408d9d43ce7cbe2b08683a39ad0aabac42:Nut/Player.java
=======
>>>>>>> 4ee7dfe (dent):Nut/Player.java
}