package src.NutItem;

import src.Nut1.CentralDeck;


public class CutCardItem extends Item {
    //private String id = "CutCard";
    
    public void use(CentralDeck card){
        card.cutTop();
    }
 }
