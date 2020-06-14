package com.mygdx.game.dominio;

import java.util.HashMap;

public class TipoAccion {

    private int id;
    private long duracionEnMilisegundos;
    private String nombre, descripcion;
    private HashMap<Integer, TipoRecurso> cantidadesInvertidas;
    private HashMap<Integer, TipoRecurso> cantidadesObtenidas;

}
