package Nut;
public class Game {
    // private int round;
    // private int currentTurnIndex;
    // private int state;
    private Player p;
    private Player enemy;
    private CentralDeck deck;
    private boolean isP1Turn = true ;
     
    public Game(){
        this.p = new Player(6); 
        this.enemy = new Player(6);
        this.deck = new CentralDeck();
        this.deck.generate(6);
    }

    public void startNewgame(Player p1,Player p2){
        // สุ่ม item ทัั้งหมด
        HealthPotionItem heal = new HealthPotionItem();
        p1.addItem(heal);
        p2.addItem(heal);
    }

    // private void startRound(){

    // }

    // private void endRound(){

    // }

    // private void nextTurn(){

    // }
    // private Player checkWinRound(){
    //     return p;
    // }
    public Player getPlayer(){
        return this.p;
    }
    public Player getEnemy(){
        return this.enemy;
    }
    public CentralDeck getDeck(){
        return this.deck;
    }
    public void PlayerdrawCard(){
        if(deck.isEmpty()){
            deck.generate(6);
        }
        Player currentPlayer = isP1Turn ? p : enemy;
<<<<<<< HEAD
<<<<<<< HEAD
        Player opponentPlayer = isP1Turn ? enemy : p;
        System.out.println(currentPlayer.getName()+ "'s Turn"+ " is drawing...");
=======
        System.out.println((isP1Turn ? "P1" : "P2") + " is drawing...");
>>>>>>> 4ee7dfe (dent)
=======
        Player opponentPlayer = isP1Turn ? enemy : p;
        System.out.println(currentPlayer.getName()+ "'s Turn"+ " is drawing...");
>>>>>>> 0581481 (asd)
        Card1 card = deck.drawTop();
        if (card == null) 
        return;
        if(card instanceof AttackCard){
<<<<<<< HEAD
<<<<<<< HEAD
            ((AttackCard) card).resolveTargeted(currentPlayer,opponentPlayer);
=======
           int dmg = 1 * currentPlayer.getDamageMultiplier(); 
            currentPlayer.takeDamage(dmg);
            currentPlayer.resetDamageMultiplier();
>>>>>>> 4ee7dfe (dent)
=======
            ((AttackCard) card).resolveTargeted(currentPlayer,opponentPlayer);
>>>>>>> 0581481 (asd)
            switchTurn();
        }
        else if(card instanceof ManaCard){
            System.out.println("this card is noting");
<<<<<<< HEAD
<<<<<<< HEAD
            switchTurn();
=======
>>>>>>> 4ee7dfe (dent)
=======
            switchTurn();
>>>>>>> 0581481 (asd)
        }

        if(deck.isEmpty()){
            System.out.println("สุ่มใหม่");
            deck.generate(6);
        }
    
    }
    private void switchTurn(){
        isP1Turn =! isP1Turn;
<<<<<<< HEAD
<<<<<<< HEAD
        System.out.println("Now it is " + (isP1Turn ? p.getName() : enemy.getName()) + "'s Turn");
    }
    public void setPlayer(Player p1 , Player enemy){
=======
        System.out.println("Now it is " + (isP1Turn ? "P1" : "P2") + "'s Turn");
    }
    public void setPlayer(Player p1 , Player p2){
>>>>>>> 4ee7dfe (dent)
=======
        System.out.println("Now it is " + (isP1Turn ? p.getName() : enemy.getName()) + "'s Turn");
    }
    public void setPlayer(Player p1 , Player enemy){
>>>>>>> 0581481 (asd)
        this.p = p1;
        this.enemy = enemy;
    }
    public boolean isP1Turn(){
        return isP1Turn;
    }
<<<<<<< HEAD
    
=======
>>>>>>> 4ee7dfe (dent)
}
