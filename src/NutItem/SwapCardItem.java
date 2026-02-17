package src.NutItem;

import java.util.Stack;
import src.Nut1.*;
 
public class SwapCardItem extends Item {
    
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