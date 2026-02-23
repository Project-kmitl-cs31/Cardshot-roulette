package src.Nut1;


public class BlankCard extends Card1{
 

    public BlankCard(String id) {
        super(id, "BLANK Card!!!", CardType.BLANK,"/image/BLK.png");
        // this.amount = rollMana();
    }
    public void skip(){
        System.out.println("this card is noting");
    }
    // private int rollMana() {
    //     return new Random().nextInt(4) + 1;
    // }

    @Override
    public void resolveKept(Player player) {
        // player.addMana(amount);
    }

    @Override
    public void resolveTargeted(Player attacker,Player defender) {
        // defender.addMana(amount);
    }
     public String toString(){
        return " BLANK "+ id;
        }
   }
