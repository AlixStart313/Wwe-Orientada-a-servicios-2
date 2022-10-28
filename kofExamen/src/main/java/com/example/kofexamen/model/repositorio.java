package com.example.kofexamen.model;


import com.example.kofexamen.utils.Response;

import java.util.List;

public interface repositorio<T> {

    List<T> findAll();
    Response<T> findById(Long id);
    Response<T> save(T object);
    Response<T> update(T object);
    Response<T> delete(Long id);
}
