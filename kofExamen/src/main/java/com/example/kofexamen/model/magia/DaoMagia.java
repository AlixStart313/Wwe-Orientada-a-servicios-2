package com.example.kofexamen.model.magia;

import com.example.kofexamen.model.lucha.DaoLucha;
import com.example.kofexamen.model.repositorio;
import com.example.kofexamen.utils.MySQL;
import com.example.kofexamen.utils.Response;
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

public class DaoMagia implements repositorio<BeanMagia> {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    MySQL mysql = new MySQL();
    @Override
    public List<BeanMagia> findAll() {
        List<BeanMagia> Magics = new ArrayList<>();
        BeanMagia magia;
        try {
            conn = mysql.getConnection();
            String query = "SELECT * FROM magia";
            ps= conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                magia = new BeanMagia();
                magia.setId(rs.getLong("id"));
                magia.setName(rs.getString("name"));
                Magics.add(magia);

            }
        }catch (SQLException e){

            Logger.getLogger(DaoMagia.class.getName()).log(Level.SEVERE,"Error ->FindAll"+e.getMessage());
        }finally {
            mysql.close(conn,ps,rs);
        }

        return Magics;
    }

    @Override
    public Response<BeanMagia> findById(Long id) {
        BeanMagia magic = null;
        Response<BeanMagia> response = new Response<>();
        try {
            conn = mysql.getConnection();
            String query = "SELECT * FROM magia where id=? ";
            ps= conn.prepareStatement(query);
            ps.setLong(1,id);
            rs = ps.executeQuery();


            while(rs.next()){
                magic = new BeanMagia();
                magic.setId(rs.getLong("id"));
                magic.setName(rs.getString("nombre"));


                if (ps.execute()){
                    response.setStatus(200);
                    response.setMensaje("Persona Localizada correctamente");
                    response.setError(false);
                    response.setData(magic);
                }else{
                    response.setStatus(400);
                    response.setMensaje("Persona no encontrada");
                    response.setError(true);
                    response.setData(null);
                }
            }
        }catch (SQLException e){

            Logger.getLogger(DaoMagia.class.getName()).log(Level.SEVERE,"Error ->FindById"+e.getMessage());
        }finally {
            mysql.close(conn,ps,rs);
        }

        return response;
    }

    @Override
    public Response<BeanMagia> save(BeanMagia object) {
        return null;
    }

    @Override
    public Response<BeanMagia> update(BeanMagia object) {
        return null;
    }

    @Override
    public Response<BeanMagia> delete(Long id) {
        return null;
    }


}
