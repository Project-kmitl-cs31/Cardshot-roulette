package CoreGame;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

import AttackCard;
import Card1;
import ManaCard;

public class CentralDeck {
    private Stack<Card1> cards = new Stack<>();
    private Card1 currentCard;
    private Random rng = new Random();
    public void generate(int roundNumber){
        AttackCard atkcard = new AttackCard("A01");
        ManaCard manacard = new ManaCard("M01");

        int basecase = 2;

        int n = rng.nextInt(roundNumber)+basecase;
        System.out.println("Deck table : " + (n));
        cards.push(atkcard);
        cards.push(manacard);
        
       
        for (int i =basecase;i<n;i++){
            int rand = rng.nextInt(2);
            if (rand %2==0){
                AttackCard atkcard1 = new AttackCard(""+i);
                cards.push(atkcard1);
            }else{
                ManaCard manacard1 = new ManaCard(""+i);
                cards.push(manacard1);
            }
            
        }
       
        // Shuffle cards
        Collections.shuffle(cards);
        currentCard = cards.peek();
        System.out.println(cards);
        System.out.println("Current "+currentCard);
    }

    public void peekCurrent(){
        currentCard = cards.peek();
        System.out.println(currentCard);

    }
    public void swapCard(){

    }
    public void cutTop(){
        System.out.println(cards.pop());
        System.out.println(cards);
        
    }
    public Card1 drawCurrent(){
        Card1 card = currentCard;
        currentCard = null;
        return card;
    }
    public boolean isEmpty(){
        return cards.isEmpty();
    }
}
