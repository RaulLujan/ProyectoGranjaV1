package com.mygdx.game;

import com.mygdx.game.Dominio.Granja;
import com.mygdx.game.Dominio.TipoRecurso;
import com.mygdx.game.Dominio.Usuario;

import java.util.HashMap;

public class DomainMocker {

    public static Usuario getMockedUser(){
        Usuario user = new Usuario(1,"Paco", "SuperFarmer");
        user.setGranja(new Granja(1,user, "Super Granja"));
        HashMap<Float, TipoRecurso> precios = new HashMap<>();
        precios.put(200f, new TipoRecurso(1, "Carne", TipoRecurso.Tipo.MATERIAL, 500, 125));
        precios.put(212f, new TipoRecurso(2, "Agua", TipoRecurso.Tipo.MATERIAL, 500, 125));
        precios.put(125f, new TipoRecurso(3, "Abono", TipoRecurso.Tipo.MATERIAL, 500, 125));
        precios.put(130f, new TipoRecurso(4, "Herbicida", TipoRecurso.Tipo.MATERIAL, 500, 125));
        precios.put(150f, new TipoRecurso(5, "Huevos", TipoRecurso.Tipo.MATERIAL, 500, 125));
        precios.put(166f, new TipoRecurso(6, "Leche", TipoRecurso.Tipo.MATERIAL, 500, 125));
        precios.put(173f, new TipoRecurso(7, "Maiz", TipoRecurso.Tipo.MATERIAL, 500, 125));
        precios.put(137f, new TipoRecurso(8, "Fresas", TipoRecurso.Tipo.MATERIAL, 500, 125));
        precios.put(249f, new TipoRecurso(9, "Patatas", TipoRecurso.Tipo.MATERIAL, 500, 125));
        user.getGranja().setPrecioRecursos(precios);

        return null;
    }
}
