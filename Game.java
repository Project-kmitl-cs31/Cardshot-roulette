import HealthPotionItem;
import javax.swing.UIManager;
import CoreGame.Player;
public class Game {
    private int round;
    private int currentTurnIndex;
    private int state;
    private Player p;

    public void startNewgame(Player p1,Player p2){
        // สุ่ม item ทัั้งหมด
        HealthPotionItem heal = new HealthPotionItem();
        p1.addItem(heal);
        p2.addItem(heal);
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

    public Player getPlayer() {
        return this.p;
    }
}
