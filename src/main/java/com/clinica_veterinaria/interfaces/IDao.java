package com.clinica_veterinaria.interfaces;

import javafx.collections.ObservableList;

public interface IDao<T,I> {
    void create(T entity);
    ObservableList<T> obtenerTodos(String busqueda);

    void actualizar(T entity, I id);

    void borrar(I id);

}
