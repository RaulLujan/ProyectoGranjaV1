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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public TipoRecurso getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(TipoRecurso tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }
}
