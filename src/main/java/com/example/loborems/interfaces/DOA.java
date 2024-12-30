package com.example.loborems.Interfaces;


import java.util.List;

public interface DOA<T> {
    void save(T entity);
    void update(T entity);
    void delete(T entity);
    T findById(int id);
    List<T> findAll();
}
