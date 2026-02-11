package Nut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ItemFactory {

    private Random rng;
    private List<Item> allitem;
    public ItemFactory() {
        this.rng = new Random();
    }

    // Methods
    // public List<Item> grantInitialItems() {
    //     List<Item> initialItems = new ArrayList<>();
    //     // มอบไอเทมเริ่มต้น 2 ชิ้น
    //     for (int i = 0; i < 2; i++) {
    //         initialItems.add(createRandomItem());
    //     }
    //     return initialItems;
    // }

    public List<Item> grantRoundItems() {
        List<Item> roundItems = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                roundItems.add(createRandomItem());
            }
        return roundItems;
    }

    // add items
    private Item createRandomItem() {
        allitem = new ArrayList<>(Arrays.asList(new HealthPotionItem(),new PeekCardItem()));
        int index = rng.nextInt(allitem.size());
        return allitem.get(index); 
    }
}