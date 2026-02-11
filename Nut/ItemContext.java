<<<<<<< HEAD:ItemContext.java
=======
package Nut;
import CoreGame.Player;

>>>>>>> a4dde4300e94ccc352159d3e73ee4965417dfa11:Nut/ItemContext.java
public class ItemContext {
    // private CentralDeck deck;
    // private RulesEngine rules;
    private Player user;
    private Player opponent;

    public ItemContext( Player user, Player opponent) {
        // CentralDeck deck
        // this.deck = deck;
        // , RulesEngine rules
        // this.rules = rules;
        this.user = user;
        this.opponent = opponent;
    }

    public boolean requireMana(int cost) {
        // ตรวจสอบว่าผู้เล่นมี Mana เพียงพอหรือไม่
        return user.getMana() >= cost;
    }



    // public Card1 getCurrentCard() {
    //     return deck.drawCurrent();
    // }


    public Player getUser() { 
        return user; 
    }
    public Player getOpponent() { 
        return opponent; 
    }
    // public CentralDeck getDeck() { 
    //     return deck; 
    // }
    // public RulesEngine getRules() { return rules; }
}