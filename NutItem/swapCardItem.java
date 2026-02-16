import java.util.Stack;
import Nut1.AttackCard;
import Nut1.Card1;
import Nut1.CentralDeck;
import Nut1.ManaCard;
import Nut1.Player;
public class swapCardItem extends Item {
    public void use(Player player){
      
    }

    public Stack<Card1> use(Stack<Card1> cards){
        AttackCard atkcard = new AttackCard("A01");
        ManaCard manacard = new ManaCard("M01");
        if(cards.peek() instanceof AttackCard){
            cards.pop();
            cards.push(manacard);
        }
        if(cards.peek() instanceof ManaCard){
            cards.pop();
            cards.push(atkcard);
        }
        return cards;
    }
}
