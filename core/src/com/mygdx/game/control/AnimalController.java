package com.mygdx.game.control;

public class AnimalController {

    public static String getRNDName(){
        String[] names = {"Paquita", "Florinda", "Renata", "Manchitas", "Coco", "Patuco", "Mimi", "Didi"};
        int index = (int)(Math.random() * names.length);
        return names[index];
    }
}
