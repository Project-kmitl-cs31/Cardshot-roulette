package src.Nut1;

public class BlankCard extends Card1 {

    public BlankCard(String id) {
        super(id, "BLANK Card!!!", CardType.BLANK, "/image/BLKcard.png");

    }

    public void skip() {
        System.out.println("this card is noting");
    }

    @Override
    public void resolveKept(Player player) {
    }

    @Override
    public void resolveTargeted(Player attacker, Player defender) {
    }

    public String toString() {
        return " BLANK " + id;
    }
}
