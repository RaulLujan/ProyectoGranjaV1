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
}
