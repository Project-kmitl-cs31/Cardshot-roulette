package Item;

public class Health extends Item {
    private String id = "HealthItem";

    public void use(Player player){
        if(player.getHp() < 6){
            player.setHp(1);
        }
    }
}