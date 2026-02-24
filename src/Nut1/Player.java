package src.Nut1;
import java.util.ArrayList;
import src.NutItem.*;

public class Player {
    private String name;
    int hp;
 
    private int lockedTurns;
    private int damageMultiplier = 1;
    private ArrayList<Item> inventory = new ArrayList<>();
    private boolean isLockedNextTurn = false;

    public Player(int maxhp){
        this.hp = maxhp;
    }
    public Player(String name, int maxHp) {
        this.name = name;
        this.hp = maxHp;
  
        this.lockedTurns = 0;
        this.damageMultiplier = 1;
    }
  
    
    public boolean canAct() {

        return lockedTurns <= 0 && hp > 0;
    }

    public void beginTurn() {
        if (lockedTurns > 0) {
            lockedTurns--;
            // System.out.println("locked turn");
        }
      
    }


    public void takeDamage(int amount) {
        if (amount < 0) return;
        this.hp -= amount;
        if (this.hp < 0) this.hp = 0;
    }

    protected void heal(int amount,int manaCost) {
        if (amount <= 0) return;

        this.hp += amount;
    }



    public void addDamageMultiplier(int mult, boolean forNextCard) {
        this.damageMultiplier *= mult;
        // System.out.println(name + " damage multiplier " + this.damageMultiplier);
    }


    public int getLockedTurns() {
         return lockedTurns; 
        }

    public void setLockedTurns(int lockedTurns) { 
        this.lockedTurns = lockedTurns; 
    }
    


    public void addItem(Item item) {
        if (inventory.size() < 7 ) {
            inventory.add(item);    
    
        }
        System.out.println("My inv "+inventory);
        System.out.println("My inv count "+getItemCount());
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

    public void getAllItem(){
        inventory.forEach(e->System.out.println(e));
    }

    public int getDamageMultiplier(){
        return damageMultiplier;
    }
    public void resetDamageMultiplier() {
        this.damageMultiplier = 1;
        // System.out.println(name + " damage multiplier reset to 1");
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