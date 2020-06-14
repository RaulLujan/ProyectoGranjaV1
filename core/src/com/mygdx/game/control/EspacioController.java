package com.mygdx.game.control;

import com.mygdx.game.DomainMocker;
import com.mygdx.game.dominio.Animal;
import com.mygdx.game.dominio.Espacio;
import com.mygdx.game.dominio.TipoRecurso;

import java.util.GregorianCalendar;
import java.util.List;

public class EspacioController {
    private List<Espacio> espacios;
    public EspacioController(List<Espacio> espacios) {
        this.espacios = espacios;
    }

    public boolean put(int reourceIndex, int quantity){
        int ocupation = espacios.get(reourceIndex).getOcupacionAactual();
        int capacidad = 0;
        boolean unlimited = false;
        if (espacios.get(reourceIndex).getCapacidadMaxima() != null && espacios.get(reourceIndex).getCapacidadMaxima() != 0 ){
            capacidad = espacios.get(reourceIndex).getCapacidadMaxima();
        }else{
            unlimited = true;
        }
        if (( unlimited && ocupation + quantity >=0 ) ||
                (capacidad >= ocupation + quantity && ocupation + quantity >=0) ){
            espacios.get(reourceIndex).setOcupacionAactual(ocupation + quantity);
            return true;
        }
        return false ;
    }

    public boolean putForMoney(int reourceIndex, int quantity, int money){
        int moneyOwned = espacios.get(TipoRecurso.MONEY).getOcupacionAactual();
        if (moneyOwned >= money){
            if (put(reourceIndex, quantity)){
                espacios.get(TipoRecurso.MONEY).setOcupacionAactual(moneyOwned-money);
                return true;
            }
        }
        return false;
    }

    public void fillSpace(int reourceIndex){
        if (espacios.get(reourceIndex).getCapacidadMaxima() != null){
            espacios.get(reourceIndex).setOcupacionAactual(espacios.get(reourceIndex).getCapacidadMaxima());
        }
    }
    public int emptyCompletely(int reourceIndex){
        int ocupation = espacios.get(reourceIndex).getOcupacionAactual();
        espacios.get(reourceIndex).setOcupacionAactual(0);
        return ocupation;
    }
    public boolean addAnimal(int reourceIndex){
        if(reourceIndex == TipoRecurso.COW || reourceIndex == TipoRecurso.PIG || reourceIndex == TipoRecurso.CHICKEN){
            if(espacios.get(reourceIndex).getCapacidadMaxima() > espacios.get(reourceIndex).getAnimales().size()){
               espacios.get(reourceIndex).getAnimales().add(new Animal(
                       espacios.get(reourceIndex).getAnimales().size(),
                       "Paquita",
                       new GregorianCalendar(),
                       DomainMocker.getAllResorurcesList().get(reourceIndex)));
               return true;
            }

        }
        return false;
    }

    public void removeAnimal (int resourceIndez, int animalIndex){
        espacios.get(resourceIndez).getAnimales().remove(animalIndex);
    }
}
