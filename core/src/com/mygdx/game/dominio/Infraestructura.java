package com.mygdx.game.dominio;

import java.util.List;

public class Infraestructura {


    public static final int STORAGE = 0;
    public static final int FIELD = 1;



    private int id;
    private String nombre, descripcion ;
    private int mantenimientoSemanal, puntuacion;
    private List<Espacio> espacios;
    private List<TipoAccion> accionesDisponibles;



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

    public int getMantenimientoSemanal() {
        return mantenimientoSemanal;
    }

    public void setMantenimientoSemanal(int mantenimientoSemanal) {
        this.mantenimientoSemanal = mantenimientoSemanal;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public List<Espacio> getEspacios() {
        return espacios;
    }

    public void setEspacios(List<Espacio> espacios) {
        this.espacios = espacios;
    }

    public List<TipoAccion> getAccionesDisponibles() {
        return accionesDisponibles;
    }

    public void setAccionesDisponibles(List<TipoAccion> accionesDisponibles) {
        this.accionesDisponibles = accionesDisponibles;
    }
}
