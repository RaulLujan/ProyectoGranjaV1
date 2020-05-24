package com.mygdx.game.Dominio;

import java.util.HashMap;
import java.util.List;

public class Granja {

    private int id;
    private Usuario usuario;
    private String nombre;
    private List<Infraestructura>  infraestructuras;
    private HashMap<Float, TipoRecurso> precioRecursos;



    public Granja(int id, Usuario usuario, String nombre) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Infraestructura> getInfraestructuras() {
        return infraestructuras;
    }

    public void setInfraestructuras(List<Infraestructura> infraestructuras) {
        this.infraestructuras = infraestructuras;
    }

    public HashMap<Float, TipoRecurso> getPrecioRecursos() {
        return precioRecursos;
    }

    public void setPrecioRecursos(HashMap<Float, TipoRecurso> precioRecursos) {
        this.precioRecursos = precioRecursos;
    }
}
