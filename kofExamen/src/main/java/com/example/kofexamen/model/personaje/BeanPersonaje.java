package com.example.kofexamen.model.personaje;

import com.example.kofexamen.model.lucha.BeanLucha;
import com.example.kofexamen.model.magia.BeanMagia;
import com.example.kofexamen.model.lucha.BeanLucha;
import com.example.kofexamen.model.magia.BeanMagia;

public class BeanPersonaje {
    long id;
    String name;
    String lastname;
    String bday;
    int magia;
    double estatura;
    double peso;
    int equipo;
    BeanMagia magic;
    BeanLucha lucha;

    public BeanPersonaje() {
    }

    public BeanPersonaje(long id, String name, String lastname, String bday, int magia, double estatura, double peso, int equipo, BeanMagia magic, BeanLucha lucha) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.bday = bday;
        this.magia = magia;
        this.estatura= estatura;
        this.peso = peso;
        this.equipo = equipo;
        this.magic = magic;
        this.lucha = lucha;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBday() {
        return bday;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public int getMagia() {
        return magia;
    }

    public void setMagia(int magia) {
        this.magia = magia;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getEquipo() {
        return equipo;
    }

    public void setEquipo(int equipo) {
        this.equipo = equipo;
    }

    public BeanMagia getMagic() {
        return magic;
    }

    public void setMagic(BeanMagia magic) {
        this.magic = magic;
    }

    public BeanLucha getLucha() {
        return lucha;
    }

    public void setLucha(BeanLucha lucha) {
        this.lucha = lucha;
    }
}
