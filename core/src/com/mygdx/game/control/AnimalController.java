package com.mygdx.game.control;

import java.util.ArrayList;
import java.util.Arrays;

public class AnimalController {

    private static ArrayList<String> names =new ArrayList<>();
    private static void rebuildList(){
        if (names.size() == 0){
            names =new ArrayList<>(Arrays.asList("Paquita", "Manyula", "Florinda", "Renata", "Manchitas", "Coco", "Patuco", "Mimi", "Didi", "Lali",
                    "Rafaela", "Chambi", "Chocolate", "Truqui", "Gordispanci", "Morty") );
        }
    }


    public static String getRNDName(){
        if (names.size() == 0) rebuildList();
        int index = (int)(Math.random() * names.size());
        String toReturn = names.get(index);
        names.remove(index);
        return toReturn;
    }

}
