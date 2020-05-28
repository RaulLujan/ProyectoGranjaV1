package com.mygdx.game.Dominio;

import java.util.GregorianCalendar;

public class Animal {

    private int id;
    private String nombre;
    private GregorianCalendar fechaNacimiento;
    private TipoRecurso tipoAnimal;

    public Animal(int id, String nombre, GregorianCalendar fechaNacimiento, TipoRecurso tipoAnimal) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoAnimal = tipoAnimal;
    }
}
