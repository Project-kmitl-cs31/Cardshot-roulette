package NutItem;

import java.util.Stack;

import Nut1.Card1;
import Nut1.CentralDeck;
import Nut1.Player;

public class PeekCardItem extends Item {
    public Card1 use(Stack<Card1> card){
        Card1 currentCard = card.peek();
        System.out.println(currentCard);
        return currentCard;
    }

    @Override
    public void use(Player player) {
        throw new UnsupportedOperationException("Unimplemented method 'use'");
    }


}

