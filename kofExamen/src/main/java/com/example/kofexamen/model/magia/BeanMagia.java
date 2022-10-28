package com.example.kofexamen.model.magia;

public class BeanMagia {
    long id;
    String name;

    public BeanMagia() {
    }

    public BeanMagia(long id, String name) {
        this.id = id;
        this.name = name;
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
}
