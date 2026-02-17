package src.NutItem;

import src.Nut1.CentralDeck;


public class PeekCardItem extends Item {
    public void use(CentralDeck card){
      card.peekCurrent();
    }

    

}

