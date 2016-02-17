package itu.mmad.dttn.tingle.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import itu.mmad.dttn.tingle.Model.Interfaces.IRepository;

/**
 * This class represents a singleton of a repository.
 * This repository will only store the items in memory and not as persistent data
 */
public class InMemoryRepository implements IRepository<Thing>, Serializable {

    private static InMemoryRepository inMemoryRepository;
    private final List<Thing> thingsDB;

    private InMemoryRepository() {
        thingsDB = new ArrayList<>();
    }

    public static InMemoryRepository getInMemoryRepository()
    {
        if(inMemoryRepository != null)
            return inMemoryRepository;
        else
        {
            inMemoryRepository = new InMemoryRepository();
            return  inMemoryRepository;
        }
    }


    /**
     * Returns a given item in the repository
     * @param id int
     * @return Thing
     */
    @Override
    public Thing get(int id) {
        return thingsDB.get(id);
    }

    /**
     * Returns an iterator of the repository
     * @return Iterator
     */
    @Override
    public Iterator<Thing> getAll() {
        return thingsDB.iterator();
    }

    /**
     * Adds a given item in the repository
     * @param entity Thing
     */
    @Override
    public void put(Thing entity) {
        thingsDB.add(entity);
    }

    /**
     * Used to modify existing items
     * NOT IMPLEMENTED YET
     * @param entity Thing
     */
    @Override
    public void update(Thing entity) {
        //no implemented
    }

    /**
     * Deletes a given item in the repository
     * @param id int
     */
    @Override
    public void delete(int id) {
        thingsDB.remove(id);
    }

    /**
     * returns the size of the repository
     * @return int
     */
    @Override
    public int returnSize() {
        return thingsDB.size()-1;
    }


}
