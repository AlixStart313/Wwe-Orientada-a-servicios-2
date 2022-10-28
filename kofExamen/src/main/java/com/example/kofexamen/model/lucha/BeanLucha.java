package com.example.kofexamen.model.lucha;

public class BeanLucha {
    long id;
    String name;

    public BeanLucha() {
    }

    public BeanLucha(long id, String name) {
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
