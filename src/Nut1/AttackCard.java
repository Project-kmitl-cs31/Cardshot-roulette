package src.Nut1; 


public class AttackCard extends Card1 {

    private int baseDamage = 1;


    public AttackCard(String id) {
        super(id, "Attack Card!!!!", CardType.ATTACK,"/image/ATKcard.png"); 
    }
    @Override
    public void resolveKept(Player player) {
        int totalDamage = baseDamage * player.getDamageMultiplier();
        
        System.out.println("DEBUG: Self shot with damage: " + totalDamage);
        player.takeDamage(totalDamage);
        player.resetDamageMultiplier(); 
    }

    @Override
    public void resolveTargeted(Player attacker, Player defender) {
        int totalDamage =  baseDamage*attacker.getDamageMultiplier();
        

        // System.out.println("DEBUG: Attacking with damage: " + totalDamage);
        defender.takeDamage(totalDamage);
        attacker.resetDamageMultiplier();
    }

    @Override
    public String toString() {
        return "AttackCard " + id + " (Dmg:" + baseDamage + ")";
    }
}