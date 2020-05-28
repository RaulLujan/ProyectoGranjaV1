package com.mygdx.game.control;

import com.mygdx.game.Dominio.Usuario;

public class UserController {

    private Usuario user;
    private float timeFromLastRead;

    public UserController(Usuario user) {
        this.user = user;
    }

    public void saveUser(){
        //call DAO and save
        //dao.model.saveUser(user)
    }
}
