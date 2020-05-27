package com.mygdx.game.Dominio;

import java.util.List;

public class Espacio {

    private int id;
    private int ocupacionAactual;
    private Integer capacidadMaxima ;
    private TipoRecurso recurso;
    private List<Animal> animales;

    public Espacio(int id, Integer capacidadMaxima, int ocupacionAactual, TipoRecurso recurso, List<Animal> animales) {
        this.id = id;
        this.capacidadMaxima = capacidadMaxima;
        this.ocupacionAactual = ocupacionAactual;
        this.recurso = recurso;
        this.animales = animales;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOcupacionAactual() {
        return ocupacionAactual;
    }

    public void setOcupacionAactual(int ocupacionAactual) {
        this.ocupacionAactual = ocupacionAactual;
    }

    public Integer getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(Integer capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public TipoRecurso getRecurso() {
        return recurso;
    }

    public void setRecurso(TipoRecurso recurso) {
        this.recurso = recurso;
    }

    public List<Animal> getAnimales() {
        return animales;
    }

    public void setAnimales(List<Animal> animales) {
        this.animales = animales;
    }
}
