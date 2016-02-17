package itu.mmad.dttn.tingle.Model.Interfaces;

import java.util.Iterator;

/**
 * Interface used to create repositories for the app
 */
public interface IRepository<E>{
    E get(int id);
    Iterator<E> getAll ();
    void put(E entity);
    void update (E entity);
    void delete(int id);
    int returnSize();

}
