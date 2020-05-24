package com.mygdx.game.Dominio;

public class TipoRecurso {

    private int id;
    private String nombre, descripcion;
    private Tipo tipo;
    private float precioMaximo, precioMinimo;


    public TipoRecurso(int id, String nombre, Tipo tipo, float precioMaximo, float precioMinimo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioMaximo = precioMaximo;
        this.precioMinimo = precioMinimo;
    }

    public enum Tipo { MATERIAL, HERRAMIENTA, ANIMAL}; // a revisar mas tarde


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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public float getPrecioMaximo() {
        return precioMaximo;
    }

    public void setPrecioMaximo(float precioMaximo) {
        this.precioMaximo = precioMaximo;
    }

    public float getPrecioMinimo() {
        return precioMinimo;
    }

    public void setPrecioMinimo(float precioMinimo) {
        this.precioMinimo = precioMinimo;
    }
}
