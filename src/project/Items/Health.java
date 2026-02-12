package Items;

import logic.Player;

public class Health extends Item {

    private String id = "HealthItem";
    @Override
    public void use() {
        // 
    }
    public void use(Player player){
        if(player.getHp() < 6){
            player.setHp(1);
        }
    }

}