package src.project.Nut;

import logic.Player;

public abstract class Item {
    private String id;  


    public void SetId(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }

    


    public abstract void use(Player player);

    //public void use(Player oop) {
       
    //}
    
}
