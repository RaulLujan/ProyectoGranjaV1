package com.mygdx.game.dominio;

public class TipoRecurso {

    private int id;
    private String nombre, descripcion;
    private Tipo tipo;
    private int resourceindex;
    private int precioMaximo, precioMinimo;

    //ATAJOS A INDICES
    public final static int MONEY      = 0;
    public final static int WATER      = 1;
    public final static int MEAT       = 2;
    public final static int MILK       = 3;
    public final static int EGG        = 4;
    public final static int MANURE     = 5;
    public final static int HERBIZIDE  = 6;
    public final static int POTATO     = 7;
    public final static int CORN       = 8;
    public final static int STRAWBERRY = 9;
    public final static int COW       = 10;
    public final static int PIG       = 11;
    public final static int CHICKEN   = 12;



    public TipoRecurso(int id, String nombre, Tipo tipo, int precioMaximo, int precioMinimo, int resourceindex) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioMaximo = precioMaximo;
        this.precioMinimo = precioMinimo;
        this.resourceindex = resourceindex;
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

    public int getPrecioMaximo() {
        return precioMaximo;
    }

    public void setPrecioMaximo(int precioMaximo) {
        this.precioMaximo = precioMaximo;
    }

    public int getPrecioMinimo() {
        return precioMinimo;
    }

    public void setPrecioMinimo(int precioMinimo) {
        this.precioMinimo = precioMinimo;
    }

    public int getResourceindex() {
        return resourceindex;
    }

    public void setResourceindex(int resourceindex) {
        this.resourceindex = resourceindex;
    }
}
