package com.mygdx.game.dao;

import com.mygdx.game.DomainMocker;
import com.mygdx.game.control.FieldController;
import com.mygdx.game.dominio.Animal;
import com.mygdx.game.dominio.Campo;
import com.mygdx.game.dominio.Espacio;
import com.mygdx.game.dominio.Estructura;
import com.mygdx.game.dominio.Granja;
import com.mygdx.game.dominio.Infraestructura;
import com.mygdx.game.dominio.Precio;
import com.mygdx.game.dominio.TipoRecurso;
import com.mygdx.game.dominio.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class UserDao {

    //final static String ip = "25.90.160.61"; // himachi
    //final static String ip = "88.148.59.247"; // himachi
    final static String ip = "localhost";
    final static String port = "3306";
    final static String dataBase = "BGranja";
    final static String user = "root";
    final static String pass = "";
    final static String DBpass = "";
    private Usuario supportUsuario;

    public UserDao(){
        this.supportUsuario = DomainMocker.getMockedUser();
    }

    public static boolean userExists(String login, String pass){
        Connection conexionMySQL = null;
        Statement st = null;
        ResultSet rs;
        PreparedStatement pst;
        try{
            conexionMySQL = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dataBase, user, DBpass);
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

    public  Usuario readUser(String login, String pass, FieldController fieldController) {
        if ( userExists(login, pass)){
            Usuario usuario = new Usuario(0,"",login);
            Connection conexionMySQL = null;
            Statement st = null;
            ResultSet rs;
            PreparedStatement pst;

            try {
                conexionMySQL = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dataBase, user, DBpass);

                //get user info
                pst = conexionMySQL.prepareStatement("SELECT * FROM TUsuarios WHERE login = ? AND pass = ?");
                pst.setString(1, login);
                pst.setString(2, pass);
                pst.executeQuery();
                rs = pst.getResultSet();
                if (rs.next()) {
                    usuario.setId(rs.getInt("Id"));
                    usuario.setNombre(rs.getString("Nombre"));
                    usuario.setApellidos(rs.getString("Apellidos"));
                }else{
                    return null;
                }
                //get farm info
                pst = conexionMySQL.prepareStatement("SELECT * FROM TGranjas WHERE Id = ?");
                pst.setString(1, rs.getString("Id_Granja"));
                pst.executeQuery();
                rs = pst.getResultSet();
                if (rs.next()) {
                usuario.setGranja(new Granja(rs.getInt("Id"), usuario, rs.getString("Nombre") ));
                }else{
                    return null;
                }
                ArrayList<TipoRecurso> resources = DomainMocker.getAllResorurcesList();
                ArrayList<Precio> precios= new ArrayList<>();

                //SET prices
                pst = conexionMySQL.prepareStatement("SELECT * FROM TPrecios WHERE Id_Granja = ?");
                pst.setInt(1, usuario.getGranja().getId());
                pst.executeQuery();
                rs = pst.getResultSet();

                while (rs.next()) {
                    precios.add(rs.getInt("Id_recurso"), new Precio(rs.getInt("Id"),resources.get(rs.getInt("Id_recurso")), rs.getInt("Precio")));
                }
                usuario.getGranja().setPrecios(precios);

                //Storage id
                int storageID;
                pst = conexionMySQL.prepareStatement("SELECT * FROM TInfraestructuras WHERE Id_Granja = ? AND Id_Tipo = 0");
                pst.setInt(1, usuario.getGranja().getId());
                pst.executeQuery();
                rs = pst.getResultSet();
                if (rs.next()) {
                    storageID = rs.getInt("Id");
                }else{
                    return null;
                }

                //SETTEAR spaces
                pst = conexionMySQL.prepareStatement("SELECT * FROM TEspacios WHERE Id_Infraestructura = ?");
                pst.setInt(1, storageID);
                pst.executeQuery();
                rs = pst.getResultSet();
                usuario.getGranja().setInfraestructuras(new ArrayList<Infraestructura>());
                Estructura almacen = new Estructura();
                ArrayList<Espacio> espacios = new ArrayList<>();

                //SETTEAR ESPACIOS
                int idCowSpace= 0 , idPigSpace= 0, idChickenSpace = 0;
                while (rs.next()) {
                  espacios.add(rs.getInt("Id_Recurso"), new Espacio(
                          rs.getInt("Id"),
                          rs.getObject("capacidad", Integer.class),
                          rs.getInt("ocupacion_actual"),
                          resources.get(rs.getInt("Id_Recurso")),
                          null));
                  if(rs.getInt("Id_Recurso") == TipoRecurso.COW) idCowSpace = rs.getInt("Id");
                  else if(rs.getInt("Id_Recurso") == TipoRecurso.PIG) idPigSpace = rs.getInt("Id");
                  else if(rs.getInt("Id_Recurso") == TipoRecurso.CHICKEN) idChickenSpace = rs.getInt("Id");
                }
                espacios.get(TipoRecurso.COW).setAnimales(new ArrayList<Animal>());
                espacios.get(TipoRecurso.PIG).setAnimales(new ArrayList<Animal>());
                espacios.get(TipoRecurso.CHICKEN).setAnimales(new ArrayList<Animal>());

                //SETEAR ANIMALES
                //cows
                pst = conexionMySQL.prepareStatement("SELECT * FROM TAnimales WHERE Id_Espacio = ?");
                pst.setInt(1, idCowSpace);
                pst.executeQuery();
                rs = pst.getResultSet();
                while (rs.next()) {
                    Date nacimiento = rs.getDate("Fecha_Nacimiento");
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(nacimiento);
                    espacios.get(TipoRecurso.COW).getAnimales().add(new Animal(
                       rs.getInt("Id"),
                       rs.getString("Nombre"),
                       calendar,
                       resources.get(TipoRecurso.COW)
                ));}
                //pigs
                pst = conexionMySQL.prepareStatement("SELECT * FROM TAnimales WHERE Id_Espacio = ?");
                pst.setInt(1, idPigSpace);
                pst.executeQuery();
                rs = pst.getResultSet();
                while (rs.next()) {
                    Date nacimiento = rs.getDate("Fecha_Nacimiento");
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(nacimiento);
                    espacios.get(TipoRecurso.PIG).getAnimales().add(new Animal(
                            rs.getInt("Id"),
                            rs.getString("Nombre"),
                            calendar,
                            resources.get(TipoRecurso.PIG)
                ));}
                //chickens
                pst = conexionMySQL.prepareStatement("SELECT * FROM TAnimales WHERE Id_Espacio = ?");
                pst.setInt(1, idChickenSpace);
                pst.executeQuery();
                rs = pst.getResultSet();
                while (rs.next()) {
                    Date nacimiento = rs.getDate("Fecha_Nacimiento");
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(nacimiento);
                    espacios.get(TipoRecurso.CHICKEN).getAnimales().add(new Animal(
                            rs.getInt("Id"),
                            rs.getString("Nombre"),
                            calendar,
                            resources.get(TipoRecurso.CHICKEN)
                ));}
                almacen.setEspacios(espacios);
                usuario.getGranja().getInfraestructuras().add(Infraestructura.STORAGE, almacen);

                //SETEAR ESTADO campo
                Campo campo = new Campo();
                pst = conexionMySQL.prepareStatement("SELECT * FROM TCampos WHERE Id_Granja = ?");
                pst.setInt(1, usuario.getGranja().getId());
                pst.executeQuery();
                rs = pst.getResultSet();
                if (rs.next()) {
                    campo.setId(rs.getInt("Id"));
                    Integer plantedResurce = rs.getObject("recurso_plantado", Integer.class);
                    GregorianCalendar gregorianCalendar = new GregorianCalendar();
                    Timestamp timestamp = rs.getTimestamp("Fecha_plantado");
                    fieldController.setField(campo);
                    if (plantedResurce == TipoRecurso.CORN || plantedResurce == TipoRecurso.STRAWBERRY || plantedResurce == TipoRecurso.POTATO){
                        gregorianCalendar.setTime(timestamp);
                        fieldController.plant(plantedResurce, gregorianCalendar);
                    }
                }else{
                    return null;
                }
                usuario.getGranja().getInfraestructuras().add(Infraestructura.FIELD, campo);
                conexionMySQL.close();
            }catch (Exception e){
                e.printStackTrace();
            }
           return usuario;
        }else return supportUsuario;
    }

    public boolean saveUser(Usuario usuario){
        Connection conexionMySQL = null;
        Statement st = null;
        ResultSet rs;
        PreparedStatement pst;


        int ocupation = usuario.getGranja().getInfraestructuras().get(Infraestructura.STORAGE).getEspacios().get(TipoRecurso.COW).getAnimales().size();
        usuario.getGranja().getInfraestructuras().get(Infraestructura.STORAGE).getEspacios().get(TipoRecurso.COW).setOcupacionAactual(ocupation);
        ocupation = usuario.getGranja().getInfraestructuras().get(Infraestructura.STORAGE).getEspacios().get(TipoRecurso.PIG).getAnimales().size();
        usuario.getGranja().getInfraestructuras().get(Infraestructura.STORAGE).getEspacios().get(TipoRecurso.PIG).setOcupacionAactual(ocupation);
        ocupation = usuario.getGranja().getInfraestructuras().get(Infraestructura.STORAGE).getEspacios().get(TipoRecurso.CHICKEN).getAnimales().size();
        usuario.getGranja().getInfraestructuras().get(Infraestructura.STORAGE).getEspacios().get(TipoRecurso.CHICKEN).setOcupacionAactual(ocupation);

        try {
            conexionMySQL = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dataBase, user, DBpass);
            int cowSpaceId = 0, pigpaceId = 0, chickenSpaceId = 0;
            int idInfraetructura = usuario.getGranja().getInfraestructuras().get(Infraestructura.FIELD).getId();
            // update epacios
            for (Espacio espacio: usuario.getGranja().getInfraestructuras().get(Infraestructura.STORAGE).getEspacios()) {
                pst = conexionMySQL.prepareStatement("UPDATE TEspacios SET ocupacion_actual = ?, capacidad = ? where id = ?");
                pst.setInt(1, espacio.getOcupacionAactual());
                pst.setInt(2, espacio.getCapacidadMaxima());
                pst.setInt(3, espacio.getId());
                pst.executeUpdate();
                if (espacio.getRecurso().getResourceindex() == TipoRecurso.COW ) cowSpaceId = espacio.getId();
                else if (espacio.getRecurso().getResourceindex() == TipoRecurso.PIG ) pigpaceId = espacio.getId();
                else if (espacio.getRecurso().getResourceindex() == TipoRecurso.CHICKEN ) chickenSpaceId = espacio.getId();
            }

            //delete animals
            pst = conexionMySQL.prepareStatement("DELETE FROM  TAnimales where Id_Espacio IN (?,?,?)");
            pst.setInt(1, cowSpaceId);
            pst.setInt(2, pigpaceId);
            pst.setInt(3, chickenSpaceId);
            pst.executeUpdate();

            // save animals
            //get max value
            pst = conexionMySQL.prepareStatement(" select MAX(Id) from tanimales");
            pst.executeQuery();
            rs = pst.getResultSet();
            int maxAnimalId;
            if (rs.next()) {
                maxAnimalId = rs.getInt(1);
            }else{
                maxAnimalId = 1;
            }

            //Cows
            for (Animal animal: usuario.getGranja().getInfraestructuras().get(Infraestructura.STORAGE).getEspacios().get(TipoRecurso.COW).getAnimales()) {
                pst = conexionMySQL.prepareStatement("INSERT INTO TAnimales VALUES (?,?,?,?,?)");
                pst.setInt(1, ++maxAnimalId);
                pst.setString(2, animal.getNombre());
                pst.setDate(3, new Date(animal.getFechaNacimiento().getTimeInMillis()));
                pst.setInt(4,TipoRecurso.COW);
                pst.setInt(5,cowSpaceId);
                pst.executeUpdate();
            }
            //Pigs
            for (Animal animal: usuario.getGranja().getInfraestructuras().get(Infraestructura.STORAGE).getEspacios().get(TipoRecurso.PIG).getAnimales()) {
                pst = conexionMySQL.prepareStatement("INSERT INTO TAnimales VALUES (?,?,?,?,?)");
                pst.setInt(1, ++maxAnimalId);
                pst.setString(2, animal.getNombre());
                pst.setDate(3, new Date(animal.getFechaNacimiento().getTimeInMillis()));
                pst.setInt(4,TipoRecurso.PIG);
                pst.setInt(5,pigpaceId);
                pst.executeUpdate();
            }
            //Chickens
            for (Animal animal: usuario.getGranja().getInfraestructuras().get(Infraestructura.STORAGE).getEspacios().get(TipoRecurso.CHICKEN).getAnimales()) {
                pst = conexionMySQL.prepareStatement("INSERT INTO TAnimales VALUES (?,?,?,?,?)");
                pst.setInt(1, ++maxAnimalId);
                pst.setString(2, animal.getNombre());
                pst.setDate(3, new Date(animal.getFechaNacimiento().getTimeInMillis()));
                pst.setInt(4,TipoRecurso.CHICKEN);
                pst.setInt(5,chickenSpaceId);
                pst.executeUpdate();
            }

            // save Field
          Campo field = (Campo)usuario.getGranja().getInfraestructuras().get(Infraestructura.FIELD);
           if (field.getPlantedResourceType() != null) {
               pst = conexionMySQL.prepareStatement("UPDATE TCampos SET recurso_plantado = ? , Fecha_plantado = ? WHERE id = ?");
               pst.setInt(1, field.getPlantedResourceType());
               java.text.SimpleDateFormat sdf =new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               String currentTime = sdf.format(field.getTimeFieldWasPlanted().getTime());
               pst.setString(2, currentTime);
               pst.setInt(3, field.getId());
               pst.executeUpdate();
           }
           else{
               pst = conexionMySQL.prepareStatement("UPDATE TCampos SET recurso_plantado = NULL , Fecha_plantado = NULL WHERE id = ?");
               pst.setInt(1, field.getId());
               pst.executeUpdate();
           }
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
