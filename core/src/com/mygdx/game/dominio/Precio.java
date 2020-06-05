package com.mygdx.game.dominio;

public class Precio {

    private int id;
    private TipoRecurso tipoRecurso;
    private int precio;

    public Precio(int id, TipoRecurso tipoRecurso, int precio) {
        this.id = id;
        this.tipoRecurso = tipoRecurso;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoRecurso getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(TipoRecurso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
