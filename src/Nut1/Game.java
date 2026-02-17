package src.Nut1;
import src.NutItem.*;
public class Game {
    // private int round;
    // private int currentTurnIndex;
    // private int state;
    private Player p;
    private Player enemy;
    private CentralDeck deck;
    private boolean isP1Turn = true ;
    private boolean targetSelf = true;
     
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
    public void setTargetSelf(boolean isSelf){
        this.targetSelf = isSelf;
    }
    public boolean isTargetSelf(){
        return targetSelf;
    }
    public void PlayerdrawCard(){
        if(deck.isEmpty()){
            deck.generate(6);
        }
        Player currentPlayer = isP1Turn ? p : enemy;
        Player opponentPlayer = isP1Turn ? enemy : p;
        Player TargertPlayer = this.targetSelf ? currentPlayer : opponentPlayer;
        System.out.println(currentPlayer.getName()+ "'s Turn"+ " is drawing...");
        Card1 card = deck.drawTop();
        if (card == null) 
        return;
        if(card instanceof AttackCard){
            ((AttackCard) card).resolveTargeted(currentPlayer,TargertPlayer);
            switchTurn();
        }
        else if(card instanceof ManaCard){
            System.out.println("this card is noting");
            if(!targetSelf){
                switchTurn();
            }else{
                System.out.println("continue playing");
            }
        }

        if(deck.isEmpty()){
            System.out.println("สุ่มใหม่");
            deck.generate(6);
        }
    
    }
    private void switchTurn(){
        isP1Turn =! isP1Turn;
        System.out.println("Now it is " + (isP1Turn ? p.getName() : enemy.getName()) + "'s Turn");
    }
    public void setPlayer(Player p1 , Player enemy){
        this.p = p1;
        this.enemy = enemy;
    }
    public boolean isP1Turn(){
        return isP1Turn;
    }
    
}