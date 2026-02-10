package CoreGame;


import java.util.ArrayList;
import java.util.List;

import Item;

public class Player {
    // --- Attributes ตาม UML ---
    private String id;
    private String name;
    private int hp;
    private int mana;
    private List<Item> items;
    private int lockedTurns;
    private int damageMultiplier = 1;
    private Item curItem;
    // private ItemContext ctx;
    private static final int MAX_ITEMS = 8;

   
    public Player(String id, String name, int maxHp, int initialMana) {
        this.id = id;
        this.name = name;
        this.hp = maxHp;
        this.mana = initialMana;
        this.items = new ArrayList<>();
        this.lockedTurns = 0;
        this.damageMultiplier = 1;
    }

    public boolean canAct() {
        // สามารถเล่นได้ถ้าไม่ติด LockedTurn และยังมีชีวิตอยู่
        return lockedTurns <= 0 && hp > 0;
    }

    public void beginTurn() {
        if (lockedTurns > 0) {
            lockedTurns--;
            System.out.println(name + " is locked! Turns remaining: " + lockedTurns);
        }
      
    }


    public Item firstItem(){
        return items.get(0);
    }

    public void UseItem(ItemContext ctx,Object o){
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
            this.curItem.applyEffect(ctx);
            this.curItem = null;
        }
        
    }

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
                System.out.println(name + " got item: " + item.getName());
            } else {
                System.out.println("Inventory Full! Cannot add: " + item.getName());
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
    
    // Helper สำหรับ StealItem (เพื่อให้ขโมยของได้จริง)
    public Item removeRandomItem(Item item) {
        if (items.isEmpty()) return null;
        for(int i =0;i<items.size();i++){
            if(items.get(i).getId().equals(item.getId())){
                items.remove(i);
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
}