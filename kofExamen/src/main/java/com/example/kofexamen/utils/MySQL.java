package com.example.kofexamen.utils;

import java.sql.*;

public class MySQL {

    public Connection getConnection(){

        final String  DBNAME = "kof",
                USERNAME = "root",
                PASSWORD = "root",
                TIMEZONE = "America/Mexico_City",
                USESSL = "false",
                PUBLICKEY = "true";
        String dataSource = String.format("jdbc:mysql://localhost:3306/%s?user=%s" +
                        "&password=%s&serverTimezone=%s&useSSL=%s&allowPublicKeyRetrieval=%s",
                DBNAME, USERNAME, PASSWORD, TIMEZONE, USESSL, PUBLICKEY);
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            return DriverManager.getConnection(dataSource);
        } catch (SQLException e) {
            System.out.println(this.getClass().getCanonicalName() +
                    "->" + e.getMessage());
        }
        return null;
    }

    public void close(Connection coon, PreparedStatement ps, ResultSet rs){
        try {
            if (coon != null)
                coon.close();
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
