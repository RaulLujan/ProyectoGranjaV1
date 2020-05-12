package com.mygdx.game.Dominio;

public class TipoRecurso {

    private int id;
    private String nombre, descripcion;
    private Tipo tipo;
    private float precioMaximo, precioMinimo;





    public enum Tipo { MATERIAL, HERRAMIENTA, ANIMAL}; // a revisar mas tarde
}
