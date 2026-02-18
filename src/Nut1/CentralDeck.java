package src.Nut1;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class CentralDeck {
    private Stack<Card1> cards = new Stack<>();
    private Card1 currentCard;
    private Random rng = new Random();
    public void generate(){

        AttackCard atkcard = new AttackCard("A-1");
        ManaCard manacard = new ManaCard("M-1");

        int basecase = 2;

        int n = rng.nextInt(6)+1;
        System.out.println("Deck table : " + (n+basecase));

        cards.push(atkcard);
        cards.push(manacard);
        
       
        for (int i =0;i<n;i++){
            int rand = rng.nextInt(2);
            if (rand %2==0){
                AttackCard atkcard1 = new AttackCard("A-"+(i+1));
                cards.push(atkcard1);
            }else{
                ManaCard manacard1 = new ManaCard("M-"+(i+1));
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
        Card1 card = peekTop();
        AttackCard atkcard = new AttackCard("A01");
        ManaCard manacard = new ManaCard("M01");
        if(card instanceof AttackCard){
            cutTop();
            cards.push(manacard);
        }
        if(card instanceof ManaCard){;
            cutTop();
            cards.push(atkcard);
        }

    }
    public Card1 cutTop(){
       return  cards.pop();
        
    }
    public Card1 drawCurrent(){
        Card1 card = currentCard;
        currentCard = null;
        return card;
    }
    public boolean isEmpty(){
        return cards.isEmpty();
    }
    public Card1 drawTop(){
        if(cards.isEmpty())
        return null;
        Card1 drawCard = cards.pop();

        if(!cards.isEmpty()){
         currentCard = cards.peek();
        }else{
            currentCard = null;
        }
        return drawCard;
    }
    public int getCardCount(){
        return cards.size();
    }
     public Card1 peekTop() {
        if (cards.isEmpty()) return null;
        return cards.get(cards.size() - 1);
    }
}
