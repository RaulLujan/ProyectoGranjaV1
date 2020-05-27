package com.mygdx.game;

import com.mygdx.game.Dominio.Espacio;
import com.mygdx.game.Dominio.Estructura;
import com.mygdx.game.Dominio.Granja;
import com.mygdx.game.Dominio.Infraestructura;
import com.mygdx.game.Dominio.Precio;
import com.mygdx.game.Dominio.TipoRecurso;
import com.mygdx.game.Dominio.Usuario;
import java.util.ArrayList;
import java.util.HashMap;

public class DomainMocker {

    public static Usuario getMockedUser(){
        Usuario user = new Usuario(1,"Paco", "aaa");
        user.setPass("1234");
        user.setGranja(new Granja(1,user, "Super Granja"));
        ArrayList<TipoRecurso> resources = getAllResorurcesList();
        ArrayList<Precio> precios= new ArrayList<>();

        for (int i = 0; i < resources.size(); i++){
            precios.add(new Precio(i, resources.get(i), resources.get(i).getPrecioMinimo()));
        }

        user.getGranja().setPrecios(precios);

        ArrayList<Espacio> espacios = new ArrayList<>();

        espacios.add(TipoRecurso.MONEY,new Espacio(TipoRecurso.MONEY, null,1500000, resources.get(TipoRecurso.MONEY), null));
        espacios.add(TipoRecurso.WATER,new Espacio(TipoRecurso.WATER, 50000,10000, resources.get(TipoRecurso.WATER), null));
        espacios.add(TipoRecurso.MEAT,new Espacio(TipoRecurso.MEAT, 20000,0, resources.get(TipoRecurso.MEAT), null));
        espacios.add(TipoRecurso.MILK,new Espacio(TipoRecurso.MILK, 20000,0, resources.get(TipoRecurso.MILK), null));
        espacios.add(TipoRecurso.EGG,new Espacio(TipoRecurso.EGG, 20000,0, resources.get(TipoRecurso.EGG), null));
        espacios.add(TipoRecurso.MANURE,new Espacio(TipoRecurso.MANURE, 50000,1, resources.get(TipoRecurso.MANURE), null));
        espacios.add(TipoRecurso.HERBIZIDE,new Espacio(TipoRecurso.HERBIZIDE, 50000,0, resources.get(TipoRecurso.HERBIZIDE), null));
        espacios.add(TipoRecurso.POPATO,new Espacio(TipoRecurso.POPATO, 30000,0, resources.get(TipoRecurso.POPATO), null));
        espacios.add(TipoRecurso.CORN,new Espacio(TipoRecurso.CORN, 30000,500, resources.get(TipoRecurso.CORN), null));
        espacios.add(TipoRecurso.STRAWBERRY,new Espacio(TipoRecurso.STRAWBERRY, 30000,0, resources.get(TipoRecurso.STRAWBERRY), null));
        espacios.add(TipoRecurso.COW,new Espacio(TipoRecurso.COW, 5,0, resources.get(TipoRecurso.COW), null));
        espacios.add(TipoRecurso.PIG,new Espacio(TipoRecurso.PIG, 5,0, resources.get(TipoRecurso.PIG), null));
        espacios.add(TipoRecurso.CHICKEN,new Espacio(TipoRecurso.CHICKEN, 5,0, resources.get(TipoRecurso.CHICKEN), null));

        user.getGranja().setInfraestructuras(new ArrayList<Infraestructura>());

        Estructura almacen = new Estructura();
        almacen.setEspacios(espacios);
        user.getGranja().getInfraestructuras().add(almacen);

        return user;
    }


    public static ArrayList<TipoRecurso> getAllResorurcesList(){
        ArrayList<TipoRecurso> recursos = new ArrayList<>();
        recursos.add(TipoRecurso.MONEY,new TipoRecurso(TipoRecurso.MONEY,     "Dinero",       TipoRecurso.Tipo.MATERIAL, 1,1));
        recursos.add(TipoRecurso.WATER,new TipoRecurso(TipoRecurso.WATER, "Agua",             TipoRecurso.Tipo.MATERIAL, 10,11));
        recursos.add(TipoRecurso.MEAT,new TipoRecurso(TipoRecurso.MEAT, "Carne",             TipoRecurso.Tipo.MATERIAL, 500,90));
        recursos.add(TipoRecurso.MILK,new TipoRecurso(TipoRecurso.MILK, "Leche",             TipoRecurso.Tipo.MATERIAL, 500,88));
        recursos.add(TipoRecurso.EGG,new TipoRecurso(TipoRecurso.EGG, "Huevos",             TipoRecurso.Tipo.MATERIAL, 500,45));
        recursos.add(TipoRecurso.MANURE,new TipoRecurso(TipoRecurso.MANURE, "Abono",           TipoRecurso.Tipo.MATERIAL, 100,25));
        recursos.add(TipoRecurso.HERBIZIDE,new TipoRecurso(TipoRecurso.HERBIZIDE, "Herbicida",    TipoRecurso.Tipo.MATERIAL, 500,164));
        recursos.add(TipoRecurso.POPATO,new TipoRecurso(TipoRecurso.POPATO, "Papata",          TipoRecurso.Tipo.MATERIAL, 500,91));
        recursos.add(TipoRecurso.CORN,new TipoRecurso(TipoRecurso.CORN, "Maiz",              TipoRecurso.Tipo.MATERIAL, 500,75));
        recursos.add(TipoRecurso.STRAWBERRY,new TipoRecurso(TipoRecurso.STRAWBERRY, "Fresa",       TipoRecurso.Tipo.MATERIAL, 500,75));
        recursos.add(TipoRecurso.COW,new TipoRecurso(TipoRecurso.COW, "Vaca",               TipoRecurso.Tipo.MATERIAL, 1000,500));
        recursos.add(TipoRecurso.PIG,new TipoRecurso(TipoRecurso.PIG, "Cerdo",              TipoRecurso.Tipo.MATERIAL, 750,350));
        recursos.add(TipoRecurso.CHICKEN,new TipoRecurso(TipoRecurso.CHICKEN, "Gallina",        TipoRecurso.Tipo.MATERIAL, 500,150));

        return recursos;
    }

}
