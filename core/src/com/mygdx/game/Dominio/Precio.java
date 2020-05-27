package com.mygdx.game.Dominio;

public class Precio {

    private int id;
    private TipoRecurso tipoRecurso;
    private float precio;

    public Precio(int id, TipoRecurso tipoRecurso, float precio) {
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
