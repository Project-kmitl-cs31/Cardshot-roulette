package Nut;


import Items.Player;

public class Game {
    private int round;
    private int currentTurnIndex;
    private int state;

    private void startRound(Player p1,Player p2){
    
        CentralDeck deck = new CentralDeck();
        deck.generate();

        ItemFactory itemfactory = new ItemFactory();
        System.out.println("Item inv player : ");
        p1.grantItems(itemfactory.grantRoundItems());
        p2.grantItems(itemfactory.grantRoundItems());

    

    }

    private void endRound(){

    }

    private void nextTurn(){

    }
    // private Player checkWinRound(){
    //     return p;
    // }
}
