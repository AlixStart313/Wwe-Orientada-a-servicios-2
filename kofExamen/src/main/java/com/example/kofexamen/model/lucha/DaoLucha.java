package com.example.kofexamen.model.lucha;

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

public class DaoLucha implements repositorio<BeanLucha> {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    MySQL mysql = new MySQL();
    @Override
    public List<BeanLucha> findAll() {
        List<BeanLucha> estilosDePelea = new ArrayList<>();
        BeanLucha lucha;
        try {
            conn = mysql.getConnection();
            String query = "SELECT * FROM tipo_lucha ";
            ps= conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                lucha = new BeanLucha();
                lucha.setId(rs.getLong("id"));
                lucha.setName(rs.getString("name"));
                estilosDePelea.add(lucha);

            }
        }catch (SQLException e){

            Logger.getLogger(DaoLucha.class.getName()).log(Level.SEVERE,"Error ->FindAll"+e.getMessage());
        }finally {
            mysql.close(conn,ps,rs);
        }

        return estilosDePelea;
    }

    @Override
    public Response<BeanLucha> findById(Long id) {
        BeanLucha lucha = null;
        Response<BeanLucha> response = new Response<>();
        try {
            conn = mysql.getConnection();
            String query = "SELECT * FROM tipo_lucha where id=? ";
            ps= conn.prepareStatement(query);
            ps.setLong(1,id);
            rs = ps.executeQuery();


            while(rs.next()){
                lucha = new BeanLucha();
                lucha.setId(rs.getLong("id"));
                lucha.setName(rs.getString("nombre"));


                if (ps.execute()){
                    response.setStatus(200);
                    response.setMensaje("Persona Localizada correctamente");
                    response.setError(false);
                    response.setData(lucha);
                }else{
                    response.setStatus(400);
                    response.setMensaje("Persona no encontrada");
                    response.setError(true);
                    response.setData(null);
                }
            }
        }catch (SQLException e){

            Logger.getLogger(DaoLucha.class.getName()).log(Level.SEVERE,"Error ->FindById"+e.getMessage());
        }finally {
            mysql.close(conn,ps,rs);
        }

        return response;
    }

    @Override
    public Response<BeanLucha> save(BeanLucha object) {
        return null;
    }

    @Override
    public Response<BeanLucha> update(BeanLucha object) {
        return null;
    }

    @Override
    public Response<BeanLucha> delete(Long id) {
        return null;
    }
}
