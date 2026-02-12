package src.project.Nut;
import java.util.Random;

import CoreGame.Player;

public class ManaCard extends Card1{
    private int amount; // 1 - 4

    public ManaCard(String id) {
        super(id, "Mana Card", CardType.MANA);
        this.amount = rollMana();
    }
    public void skip(){
        System.out.println("this card is noting");
    }
    private int rollMana() {
        return new Random().nextInt(4) + 1;
    }

    @Override
    public void resolveKept(Player player) {
        player.addMana(amount);
    }

    @Override
    public void resolveTargeted(Player attacker,Player defender) {
        defender.addMana(amount);
    }
     public String toString(){
        return "AttackCard "+ id;
    }
}