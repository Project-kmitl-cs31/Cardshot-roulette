package Nut1;
import NutItem.*;

public class Game {
    private Player p;
    private Player enemy;
    private CentralDeck deck;
    private boolean isP1Turn = true ;
    private boolean targetSelf = true;
    private UIManager ui;
    private String actionMessage = "Game Started! Choose your action.";
     
    public Game(){
        this.p = new Player(6); 
        this.p.setName("P1");
        this.enemy = new Player(6);
        this.enemy.setName("P2");
        this.deck = new CentralDeck();
        this.deck.generate(6);
        refillItem(this.p);
        refillItem(this.enemy);
    }
    public String getActionMessage(){
        return this.actionMessage;
    }

    private boolean checkGameOver() {
        if (p.getHp() <= 0) {
            System.out.println("GAME OVER! Winner is " + enemy.getName());
            if (ui != null) {
                ui.openGameOverSceen(enemy.getName()); 
            }
            return true; 
        } else if (enemy.getHp() <= 0) {
            System.out.println("GAME OVER! Winner is " + p.getName());
            if (ui != null) {
                ui.openGameOverSceen(p.getName()); 
            }
            return true; 
        }
        return false; 
    }
    
    public void PlayerdrawCard(){
        if(deck.isEmpty()){
            deck.generate(6);
        }

        Player currentPlayer = isP1Turn ? p : enemy;
        Player opponentPlayer = isP1Turn ? enemy : p;
        Player targetPlayer = this.targetSelf ? currentPlayer : opponentPlayer;

        System.out.println(currentPlayer.getName()+ "'s Turn is drawing...");
        
        Card1 card = deck.drawTop();
        if (card == null) return;

        if(card instanceof AttackCard){
            this.actionMessage = currentPlayer.getName() + " played an ATTACK Card!";
            ((AttackCard) card).resolveTargeted(currentPlayer, targetPlayer);
            
            if (checkGameOver()) {
                return; 
            }
            
            switchTurn();
        }
        else if(card instanceof ManaCard){
            this.actionMessage = currentPlayer.getName() + " played an BLANK Card";
            if(!targetSelf){
                switchTurn();
            } else {
                System.out.println("Continue playing (Self Target)");
            }
        }
        if(deck.isEmpty()){
            deck.generate(6);
            refillItem(p);
            refillItem(enemy);
            
        }
    }
 
    private void switchTurn(){
        isP1Turn = !isP1Turn;
        System.out.println("Now it is " + (isP1Turn ? p.getName() : enemy.getName()) + "'s Turn");
    }

    public void setPlayer(Player p1 , Player enemy){
        this.p = p1;
        this.enemy = enemy;
        refillItem(this.p);
        refillItem(this.enemy);
    }

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
    public void setUIManager(UIManager ui){
         this.ui = ui;
    }
    public boolean isP1Turn(){ 
        return isP1Turn; 
    }
    
    public Player getCurrentPlayer(){
        return isP1Turn ? p : enemy;
    }

    private Item getRandomItem(){
        int rand = (int)(Math.random() * 6); 
        switch (rand){
            case 0: return new DoubleDamageItem(); 
            case 1: return new HealthPotionItem();
            case 2: return new PeekCardItem();
            case 3: return new CutCardItem(); 
            case 4: return new LockTurnItem();
            case 5: return new SwapCardItem();
            default: return new HealthPotionItem();
        }
    }

    public void refillItem(Player targetPlayer){
        System.out.println("Refilling items for " + targetPlayer.getName());
        while(targetPlayer.getItemCount() < 2){
            Item newItem = getRandomItem();
            targetPlayer.addItem(newItem);
            System.out.println(" - Got: " + newItem.getName());
        }
    }

    public void PlayItem(int index){
        Player currentPlayer = getCurrentPlayer();
        Item item = currentPlayer.getItem(index);

        if(item != null){
            this.actionMessage = currentPlayer.getName() + " uses "+ item.getName();
            item.use(this);
            currentPlayer.removeItem(index);
            
            if (checkGameOver()) {
                return; 
            }
        }
    }
}