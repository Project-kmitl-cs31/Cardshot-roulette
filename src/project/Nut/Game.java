package src.project.Nut;
public class Game {
    private int round;
    private int currentTurnIndex;
    private int state;
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
        //p1.addItem(heal);
        //p2.addItem(heal);
    }

    private void startRound(){

    }

    private void endRound(){

    }

    private void nextTurn(){

    }
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
        System.out.println((isP1Turn ? "P1" : "P2") + " is drawing...");
        Card1 card = deck.drawTop();
        if (card == null) 
        return;
        if(card instanceof AttackCard){
           int dmg = 1 * currentPlayer.getDamageMultiplier(); 
            currentPlayer.takeDamage(dmg);
            currentPlayer.resetDamageMultiplier();
            switchTurn();
        }
        else if(card instanceof ManaCard){
            System.out.println("this card is noting");
        }

        if(deck.isEmpty()){
            System.out.println("สุ่มใหม่");
            deck.generate(6);
        }
    
    }
    private void switchTurn(){
        isP1Turn =! isP1Turn;
        System.out.println("Now it is " + (isP1Turn ? "P1" : "P2") + "'s Turn");
    }
    public void setPlayer(Player p1 , Player p2){
        this.p = p1;
        this.enemy = enemy;
    }
    public boolean isP1Turn(){
        return isP1Turn;
    }
}
