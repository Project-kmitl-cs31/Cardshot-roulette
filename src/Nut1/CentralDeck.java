package src.Nut1;

import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Collectors;

public class CentralDeck {

    private Stack<Card1> cards = new Stack<>();
    private Card1 currentCard;
    private Random rng = new Random();
    AttackCard atkcard = new AttackCard("A-1");
    BlankCard blankCard = new BlankCard("M-1");

    public void generate() {

        int n = rng.nextInt(6) + 1;

        cards.push(atkcard);
        cards.push(blankCard);

        for (int i = 0; i < n; i++) {
            int rand = rng.nextInt(2);
            if (rand % 2 == 0) {
                AttackCard atkcard1 = new AttackCard("A-" + (i + 1));
                cards.push(atkcard1);
            } else {
                BlankCard manacard1 = new BlankCard("M-" + (i + 1));
                cards.push(manacard1);
            }

        }

        Collections.shuffle(cards);
        currentCard = cards.peek();
    }

    public void swapCard() {
        Card1 card = peekTop();
        String getId = card.getId();
        AttackCard atkcard = new AttackCard(getId);
        BlankCard manacard = new BlankCard(getId);
        if (card instanceof AttackCard) {
            cutTop();
            cards.push(manacard);
        }
        if (card instanceof BlankCard) {
            cutTop();
            cards.push(atkcard);
        }

    }

    public Card1 cutTop() {
        return cards.pop();

    }

    public Card1 drawCurrent() {
        Card1 card = currentCard;
        currentCard = null;
        return card;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card1 drawTop() {
        if (cards.isEmpty()) {
            return null;
        }

        Card1 drawCard = cards.pop();

        if (!cards.isEmpty()) {
            currentCard = cards.peek();
        } else {
            currentCard = null;
        }
        return drawCard;
    }

    public int getCardCount() {
        return cards.size();
    }

    public String[] getAllSource() {
        String[] newarr = new String[]{atkcard.getsourceImg(), blankCard.getsourceImg()};
        return newarr;
    }

    public Card1 peekTop() {
        if (cards.isEmpty()) {
            return null;
        } else {
            currentCard = cards.peek();
        }
        return currentCard;
    }

    public String categoryDeck() {
        Map<?, Long> countDeck = cards.stream().collect(Collectors.groupingBy(c -> c.getClass().getSimpleName(), Collectors.counting()));
        return countDeck.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));
    }
}
