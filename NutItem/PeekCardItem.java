package NutITem;

import Nut1.CentralDeck;
import Nut1.Player;

public class PeekCardItem extends Item {
    public void use(CentralDeck card){
      card.peekCurrent();
    }

    @Override
    public void use(Player player) {
        throw new UnsupportedOperationException("Unimplemented method 'use'");
    }


}

