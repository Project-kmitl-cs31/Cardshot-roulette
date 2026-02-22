package src.Nut1;
import java.util.ArrayList;
import java.util.List;
import src.NutItem.*;

public class Player {
    private String name;
    int hp;
    private int mana;
    private List<Item> items;
    private int lockedTurns;
    private int damageMultiplier = 1;
    private ArrayList<Item> inventory = new ArrayList<>();
    private boolean isLockedNextTurn = false;

    private static final int MAX_ITEMS = 8;

    public Player(int maxhp){
        this.hp = maxhp;
    }
    public Player(String name, int maxHp, int initialMana) {
        this.name = name;
        this.hp = maxHp;
        this.mana = initialMana;
        this.items = new ArrayList<>();
        this.lockedTurns = 0;
        this.damageMultiplier = 1;
    }
  
    
    public boolean canAct() {

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


    public void endTurn() {
        System.out.println(name + " ends turn.");
    }

    // public void addMana(int amount) {
    //     this.mana += amount;
    // }

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

    // public boolean useMana(int cost) {
    //     if (this.mana >= cost) {
    //         this.mana -= cost;
    //         return true;
    //     }
    //     System.out.println("Not enough mana!");
    //     return false;
    // }

    public void addDamageMultiplier(int mult, boolean forNextCard) {
        this.damageMultiplier *= mult;
        System.out.println(name + " damage multiplier is now x" + this.damageMultiplier);
    }

    
    // public int getMana() { return mana; }

    public int getLockedTurns() {
         return lockedTurns; 
        }

    public void setLockedTurns(int lockedTurns) { 
        this.lockedTurns = lockedTurns; 
    }
    


    public void addItem(Item item) {
        if (inventory.size() < 7 ) {
            inventory.add(item);
            System.out.println("My Items1 : "+inventory);            
        }else{
            System.out.println("Cant add Item");
        }
    }

    public int getItemCount() {
        return inventory.size();
    }

    public Item getItem(int index){
        if(index >= 0 && index < inventory.size()){
             return inventory.get(index);
        }
        return null;
    }

    public void removeItem(int index){
        if(index >= 0 && index < inventory.size()){
            inventory.remove(index);
            System.out.println("My Items2 : "+inventory);
        }
     
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public int getHp(){
        return hp;
    }

    public void setHp(int hp){
        this.hp += hp;
    }

    public int getDamageMultiplier(){
        return damageMultiplier;
    }
    public void resetDamageMultiplier() {
        this.damageMultiplier = 1;
        System.out.println(name + " damage multiplier reset to 1");
    }
    public void setDamageMultiplier(int newDamage)   {
        this.damageMultiplier = newDamage;
    }

    public void lockTurn() {
        this.isLockedNextTurn = true; 
    }

    public boolean checkAndClearLock() {
        if (this.isLockedNextTurn) {
            this.isLockedNextTurn = false; 
            return true;
        }
        return false; 
    }
  }