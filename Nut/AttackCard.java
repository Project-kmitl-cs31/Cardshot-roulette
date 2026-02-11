public class AttackCard extends Card1{

    private int baseDamage = 1;

    public AttackCard(String id) {
        super(id, "Attack Card", CardType.ATTACK);
    }


    public void multiplyDamage(int multiplier) {
        baseDamage *= multiplier;
    }


    @Override
    public void resolveKept(Player player) {
        player.takeDamage(baseDamage);
    }

    @Override
    public void resolveTargeted(Player attacker, Player defender) {
        defender.takeDamage(baseDamage);
    }
    public String toString(){
        return "AttackCard "+ id;
    }
}

