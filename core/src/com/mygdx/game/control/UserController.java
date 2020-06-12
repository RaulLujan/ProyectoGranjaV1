package com.mygdx.game.control;

import com.mygdx.game.dao.UserDao;
import com.mygdx.game.dominio.Usuario;

public class UserController {

    private Usuario user;
    private float timeFromLastRead;
    private UserDao userDao;


    public UserController(Usuario user) {
        this.user = user;
        this.userDao = new UserDao();

    }

    public void saveUser(){
        userDao.saveUser(user);
    }

    public boolean validateUser(String login, String pass){
        return UserDao.userExists(login, pass);
    }

    public Usuario getAndSetUser(String login, String pass, FieldController fieldController) {
        Usuario usuario =userDao.readUser(login, pass, fieldController);
        this.user = usuario;
        return usuario;
        // return user;
    }
 }
