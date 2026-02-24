package src.Nut1;

import javax.swing.Timer;
import src.NutItem.*; 

public class Game {
    private Player p;
    private Player enemy;
    private CentralDeck deck;
    private boolean isP1Turn = true ;
    private boolean targetSelf = true;
    private UIManager ui;
    private useSound sound;
     
    public Game(){
        this.p = new Player(6); 
        this.p.setName("P1");
        this.enemy = new Player(6);
        this.enemy.setName("P2");
        this.deck = new CentralDeck();
        this.deck.generate();
        refillItem(this.p);
        refillItem(this.enemy);

        this.sound = new useSound();
        Timer time = new Timer(1200,e->{
            sound.loopSound("src/Nut1/sound/musicbg.wav");
        });
        time.setRepeats(false);
        time.start();
        
    }

    
      public void PlayerdrawCard(){
        if(deck.isEmpty()){
            deck.generate();
        }

        // check player target and player use
        Player currentPlayer = isP1Turn ? p : enemy;
        Player opponentPlayer = isP1Turn ? enemy : p;
        Player targetPlayer = this.targetSelf ? currentPlayer : opponentPlayer;

        
        
        Card1 card = deck.drawTop();
        ui.getGameScreen().animtext("This card is...  "+card.getName(),card.getsourceImg());

        if (card == null) return;

        if(card instanceof AttackCard){
            ((AttackCard) card).resolveTargeted(currentPlayer, targetPlayer);
            sound.playsound("src/Nut1/sound/atksound.wav",1.2);
            Timer time = new Timer(1210,e-> {
                
                ui.getGameScreen().damageSelf(targetPlayer);
            });
            time.setRepeats(false);
            time.start();
            
            if(targetPlayer.getHp() <= 0){
                String winner = (targetPlayer == p) ? enemy.getName() : p.getName();
                if(ui != null){
                    ui.openGameOverSceen(winner); 
                }
                return;
            }
            switchTurn();
        }
        else if(card instanceof BlankCard){
            
            if(!targetSelf){

                sound.playsound("src/Nut1/sound/blanktarget.wav",0.9);
                switchTurn();
            } else{
                sound.playsound("src/Nut1/sound/blanksound.wav",1.5);
                Timer cooldown = new Timer(2500, e -> {
                
                    ui.getGameScreen().setMsgItem(targetPlayer.getName()+" turn continues.", 2);
            });
            cooldown.setRepeats(false);
            cooldown.start();
                
            }
        }
        if(deck.isEmpty()){
            deck.generate();
            refillItem(p);
            refillItem(enemy);
            
        }
    }

    private void switchTurn(){
        isP1Turn = !isP1Turn;
        Player currentPlayer = isP1Turn ? p : enemy;
        if (currentPlayer.checkAndClearLock()) {
        
            switchTurn(); 
        }
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

    public Player getOpposingP(){ 
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
    public UIManager getUIManager(){ 
        return this.ui;
    }

    public boolean isP1Turn(){ 
        return isP1Turn; 
    }
    
    public Player getCurrentPlayer(){
        return isP1Turn ? p : enemy;
    }
    

    private Item getRandomItem(){
        int rand = (int)(Math.random() * 7); 
        switch (rand){
            case 0: return new DoubleDamageItem(); 
            case 1: return new HealthPotionItem();
            case 2: return new PeekCardItem();
            case 3: return new CutCardItem(); 
            case 4: return new LockTurnItem();
            case 5: return new SwapCardItem();
            case 6: return new StealItemRng();
            default: return new HealthPotionItem();

        }
    }

    public void refillItem(Player targetPlayer){
        for(int i=0;i<2;i++){
            Item newItem = getRandomItem();
            targetPlayer.addItem(newItem);
            System.out.println(" - Got: " + newItem.getName());
        }
    }

    public void PlayItem(int index){
        Player currentPlayer = getCurrentPlayer();
        Item item = currentPlayer.getItem(index);

        if(item != null){
            sound.playsound(item.getSource(), 0.5);
            System.out.println(currentPlayer.getName() + " uses " + item.getName());
            item.use(this);
            currentPlayer.removeItem(index);
        }
    }

}