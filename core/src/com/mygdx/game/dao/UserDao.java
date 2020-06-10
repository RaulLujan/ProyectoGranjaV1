package com.mygdx.game.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class UserDao {

    final static String ip = "192.168.0.13";
    final static String port = "3306";
    final static String dataBase = "prueba";
    final static String user = "root";
    final static String pass = "";


    public static boolean userExists(String login, String pass){
        Connection conexionMySQL = null;
        Statement st = null;
        ResultSet rs;
        PreparedStatement pst;

        try{
            //String driver = "com.mysql.jdbc.Driver";

            //Class.forName("com.mysql.jdbc.Driver").newInstance ();
            conexionMySQL = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dataBase, user, pass);
            pst = conexionMySQL.prepareStatement("SELECT * FROM TUsuarios WHERE login = ? AND pass = ?");
            pst.setString(1, login);
            pst.setString(2, pass);
            pst.executeQuery();
            rs = pst.getResultSet();
            if (!rs.next())
                return false;
            if(rs.getString("login").equals(login) && rs.getString("pass").equals(pass)){
                return true;
            }


            conexionMySQL.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        return false;
    }

/*
    public Pelicula consulta(int id) {
        Pelicula peli = null;
        ResultSet rs;
        PreparedStatement ps1;

        try {
            Connection con = DriverManager.getConnection(url, usuario, password);

            ps1 = con.prepareStatement("SELECT * FROM TPelicula WHERE IdPelicula = ?;");
            ps1.setInt(1, id);
            ps1.executeQuery();
            rs = ps1.getResultSet();
            if (!rs.next())
                return null;

            peli = new Pelicula(id, rs.getInt("IdDirector"), rs.getInt("Anyo"), rs.getString("Titulo"),
                    rs.getString("Descripcion"));
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return peli;
    }
    */

/*

    class PeliculaDAO {

        private static String url = "jdbc:mysql://localhost:3306/Bservicios";
        private static String usuario = "root";
        private static String password = "root";

        public boolean alta(Pelicula p) {

            try {

                Connection con = DriverManager.getConnection(url, usuario, password);
                PreparedStatement pst = con.prepareStatement("INSERT INTO TPeliculas VALUES(null,?,?,?,?)");

                pst.setInt(1, p.getIdDirector());
                pst.setInt(2, p.getAnyo());
                pst.setString(3, p.getTitulo());
                pst.setString(4, p.getDescripcion());

                pst.executeUpdate();
                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
            return true;
        }

*/


    }
