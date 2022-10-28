package com.example.kofexamen.model.personaje;

import com.example.kofexamen.model.lucha.BeanLucha;
import com.example.kofexamen.model.magia.BeanMagia;
import com.example.kofexamen.model.repositorio;
import com.example.kofexamen.utils.MySQL;
import com.example.kofexamen.utils.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoPersonajes implements repositorio<BeanPersonaje> {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    MySQL mysql = new MySQL();
    @Override
    public List<BeanPersonaje> findAll() {
        List<BeanPersonaje> personajes = new ArrayList<>();
        BeanPersonaje person = null;
        BeanMagia magic = null;
        BeanLucha lucha= null;
        try {
            conn = mysql.getConnection();
            String query = "SELECT pe.*,lucha.name, magia.name from personaje pe inner join magia magia on pe.magia_id =" +
                    " magia.id inner join lucha lucha on pe.tipo_lucha_id = po.id ";
            ps= conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                person = new BeanPersonaje();
                magic = new BeanMagia();
                lucha = new BeanLucha();
                person.setId(rs.getLong("id"));
                person.setName(rs.getString("name"));
                person.setLastname(rs.getString("lastname"));
                person.setBday(rs.getString("birthday"));
                person.setMagia(rs.getInt("utiliza_magia"));
                person.setEstatura(rs.getDouble("estatura"));
                person.setPeso(rs.getDouble("peso"));
                if (person.getMagia()==1){
                    magic.setName(rs.getString("name"));
                    person.setMagic(magic);
                }

                lucha.setName(rs.getString("name"));
                person.setLucha(lucha);
                personajes.add(person);

            }
        }catch (SQLException e){

            Logger.getLogger(DaoPersonajes.class.getName()).log(Level.SEVERE,"Error ->FindAll"+e.getMessage());
        }finally {
            mysql.close(conn,ps,rs);
        }

        return personajes;
    }

    @Override
    public Response<BeanPersonaje> findById(Long id) {
        BeanPersonaje personaje = null;
        BeanMagia magia = null;
        BeanLucha lucha = null;
        Response<BeanPersonaje> response = new Response<>();
        try {
            conn = mysql.getConnection();
            String query = "SELECT pe.*,lucha.name, magia.name from personaje pe inner join magia magia on pe.magia_id =" +
                    " magia.id inner join lucha lucha on pe.tipo_lucha_id = po.id  where pe.id = ? ";
            ps= conn.prepareStatement(query);
            ps.setLong(1,id);
            rs = ps.executeQuery();


            while(rs.next()){
                personaje = new BeanPersonaje();
                magia = new BeanMagia();
                lucha = new BeanLucha();
                personaje.setId(rs.getLong("id"));
                personaje.setName(rs.getString("name"));
                personaje.setLastname(rs.getString("lastname"));
                personaje.setBday(rs.getString("birthday"));
                personaje.setMagia(rs.getInt("utiliza_magia"));
                personaje.setEstatura(rs.getDouble("estatura"));
                personaje.setPeso(rs.getDouble("peso"));
                if (personaje.getMagia()==1){
                magia.setName(rs.getString("name"));
                }
                personaje.setMagic(magia);
                lucha.setName(rs.getString("name"));
                personaje.setLucha(lucha);
                System.out.println(ps.execute());
                if (ps.execute()){
                    response.setStatus(200);
                    response.setMensaje("Personaje Localizado correctamente");
                    response.setError(false);
                    response.setData(personaje);
                }else{
                    response.setStatus(400);
                    response.setMensaje("Personaje no encontrado");
                    response.setError(true);
                    response.setData(null);
                }
            }
        }catch (SQLException e){

            Logger.getLogger(DaoPersonajes.class.getName()).log(Level.SEVERE,"Error ->FindById"+e.getMessage());
        }finally {
            mysql.close(conn,ps,rs);
        }

        return response;
    }

    @Override
    public Response<BeanPersonaje> save(BeanPersonaje personaje) {
        Response<BeanPersonaje> response = new Response<>();
        try{conn= mysql.getConnection();
            String query = " insert into personaje (name, lastname,birthday,utiliza_magia,estatura,peso,equipo,magia_id,tipo_lucha_id)values(?,?,?,?,?,?,?,?,?)";
            ps= conn.prepareStatement(query);
            ps.setString(1,personaje.getName());
            ps.setString(2,personaje.getLastname());
            ps.setString(3,personaje.getBday());
            ps.setInt(4,personaje.getMagia());
            if (personaje.getMagia()==1){
                ps.setLong(8,personaje.getMagic().getId());
            }
            ps.setDouble(5,personaje.getEstatura());
            ps.setDouble(6,personaje.getPeso());
            ps.setInt(7,personaje.getEquipo());
            ps.setLong(9,personaje.getLucha().getId());
            if (ps.executeUpdate()==1){
                response.setStatus(200);
                response.setMensaje("Personaje registrado correctamente");
                response.setError(false);
                response.setData(personaje);
            }else{
                response.setStatus(400);
                response.setMensaje("No se registro correctamente el personaje");
                response.setError(true);
                response.setData(null);

            }
            return response;
        }catch (SQLException e){

            Logger.getLogger(DaoPersonajes.class.getName()).log(Level.SEVERE,"Error al registrar al personal ->",e);
            response.setStatus(400);
            response.setMensaje("Error"+e);
            response.setError(false);
            response.setData(null);
        }finally {
            mysql.close(conn,ps,rs);
        }
        return response;
    }

    @Override
    public Response<BeanPersonaje> update(BeanPersonaje personaje) {
        Response<BeanPersonaje> response = new Response<>();
        try{conn= mysql.getConnection();
            String query = "";
            ps= conn.prepareStatement(query);
            ps.setString(1,personaje.getName());
            ps.setString(2,personaje.getLastname());
            ps.setString(3,personaje.getBday());
            ps.setInt(4,personaje.getMagia());
            ps.setDouble(5,personaje.getEstatura());
            ps.setDouble(6,personaje.getPeso());
            ps.setInt(7,personaje.getEquipo());
            if (personaje.getMagia()==1){
                ps.setLong(8,personaje.getMagic().getId());
            }
            ps.setLong(9,personaje.getLucha().getId());
            if (ps.executeUpdate()==1){
                response.setStatus(200);
                response.setMensaje("Personaje actualizada  correctamente");
                response.setError(false);
                response.setData(personaje);
            }else{
                response.setStatus(400);
                response.setMensaje("No se actualizo correctamente al personaje");
                response.setError(true);
                response.setData(null);

            }
            return response;
        }catch (SQLException e){

            Logger.getLogger(DaoPersonajes.class.getName()).log(Level.SEVERE,"Error al registrar al personal ->",e);
            response.setStatus(400);
            response.setMensaje("No se actualizo correctamente la persona");
            response.setError(false);
            response.setData(null);
        }finally {
            mysql.close(conn,ps,rs);
        }
        return response;
    }

    @Override
    public Response<BeanPersonaje> delete(Long id) {
        Response<BeanPersonaje> response = new Response<>();
        try{conn= mysql.getConnection();
            String query = "delete * from personaje where id=?;";
            ps= conn.prepareStatement(query);
            ps.setLong(1,id);
            if (ps.executeUpdate()==1){
                response.setStatus(200);
                response.setMensaje("Personaje eliminado correctamente");
                response.setError(false);

            }else{
                response.setStatus(400);
                response.setMensaje("No se eliminado correctamente al personaje");
                response.setError(true);
            }
            return response;
        }catch (SQLException e){

            Logger.getLogger(DaoPersonajes.class.getName()).log(Level.SEVERE,"Error al registrar al personal ->",e);
            response.setStatus(400);
            response.setMensaje("No se actualizo correctamente la persona");
            response.setError(false);
        }finally {
            mysql.close(conn,ps,rs);
        }
        return response;
    }
}
