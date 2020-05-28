package com.mygdx.game.control;

import com.mygdx.game.Dominio.Espacio;

import java.util.List;

public class EspacioController {


    private List<Espacio> espacios;

    public EspacioController(List<Espacio> espacios) {
        this.espacios = espacios;
    }


    public boolean putIn(int reourceIndex, int quantity){

        int ocupation = espacios.get(reourceIndex).getOcupacionAactual();
        int capacidad = espacios.get(reourceIndex).getCapacidadMaxima();
        if (capacidad >= ocupation + quantity && ocupation + quantity >=0){
            espacios.get(reourceIndex).setOcupacionAactual(ocupation + quantity);
            return true;
        }
        return false ;
    }
}
