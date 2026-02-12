package logic;
import java.util.Random;

public class ManaCard extends Card1{
    private int amount; // 1 - 4

    public ManaCard(String id) {
        super(id, "Mana Card", CardType.MANA);
        this.amount = rollMana();
    }

    private int rollMana() {
        return new Random().nextInt(4) + 1;
    }

    // @Override
    // public void resolveKept(Player player) {
    //     player.addMana(amount);
    // }

    // @Override
    // public void resolveTargeted(Player attacker,Player defender) {
    //     defender.addMana(amount);
    // }
     public String toString(){
        return "AttackCard "+ id;
    }

     @Override
     public void resolveKept(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resolveKept'");
     }

     @Override
     public void resolveTargeted(Player attacker, Player defender) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resolveTargeted'");
     }
}
