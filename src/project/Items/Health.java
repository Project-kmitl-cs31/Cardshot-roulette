<<<<<<< HEAD:src/project/Items/Health.java
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
=======
// package Nut;

// import Item.Item;

// public class Health extends Item {
//     private String id = "HealthItem";

//     public void use(Player player){
//         if(player.getHp() < 6){
//             player.setHp(1);
//         }
//     }
// }
>>>>>>> 0581481 (asd):Nut/Health.java
