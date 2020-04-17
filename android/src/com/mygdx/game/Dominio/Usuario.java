package com.mygdx.game.Dominio;

import java.util.GregorianCalendar;
import java.util.List;

public class Usuario {

    private int id;
    private List<Usuario> vecinos;
    private String nombre, apellidos, email, telefono, login, pass;
    private GregorianCalendar fechaNacimiento;
    private Cooperativa cooperativa;

}
