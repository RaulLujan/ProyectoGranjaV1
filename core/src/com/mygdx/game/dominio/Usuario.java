package com.mygdx.game.dominio;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class Usuario {

    private int id;
    private List<Usuario> vecinos;
    private String nombre, apellidos, email, telefono, login, pass;
    private GregorianCalendar fechaNacimiento;
    private Cooperativa cooperativa;
    private HashMap<GregorianCalendar, TipoAccion> acciones;
    private Granja granja;


    public Usuario(int id, String nombre, String login ) {
        this.id = id;
        this.nombre = nombre;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Granja getGranja() {
        return granja;
    }

    public void setGranja(Granja granja) {
        this.granja = granja;
    }

    public List<Usuario> getVecinos() {
        return vecinos;
    }

    public void setVecinos(List<Usuario> vecinos) {
        this.vecinos = vecinos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Cooperativa getCooperativa() {
        return cooperativa;
    }

    public void setCooperativa(Cooperativa cooperativa) {
        this.cooperativa = cooperativa;
    }

    public HashMap<GregorianCalendar, TipoAccion> getAcciones() {
        return acciones;
    }

    public void setAcciones(HashMap<GregorianCalendar, TipoAccion> acciones) {
        this.acciones = acciones;
    }
}
