package itu.mmad.dttn.tingle.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import itu.mmad.dttn.tingle.model.Interfaces.IRepository;

/**
 * This class represents a singleton of a repository.
 * This repository will only store the items in memory and not as persistent data
 */
public class InMemoryRepository implements IRepository, Serializable {

    private final List<Entity> thingsDB;

    //Singleton pattern has been replaced by dagger2 singleton
    public InMemoryRepository() {
        thingsDB = new ArrayList<>();
    }

    /**
     * Returns a given item in the repository
     *
     * @param id int
     * @return Thing
     */
    @Override
    public Entity get(int id) {
        for (Entity entity: thingsDB)
        {
            if(entity.getId().hashCode() == id){
                return entity;
            }

        }
        return null;
    }

    /**
     * Returns an iterator of the repository
     *
     * @return Iterator
     */
    @Override
    public Iterator<Entity> getAll() {
        return thingsDB.iterator();
    }

    /**
     * Adds a given item in the repository
     *
     * @param entity
     */
    @Override
    public boolean put(Entity entity) {
        if(entity == null) return false;
        thingsDB.add(entity);
        return true;
    }

    /**
     * Used to modify existing items
     */
    @Override
    public boolean update(Entity toStore) {
        Entity thing = get(toStore.getId().hashCode());
        if(thing == null) return false;
        delete(thing.getId().hashCode());
        put(toStore);

        return true;
    }

    /**
     * Deletes a given item in the repository
     *
     * @param id int
     */
    @Override
    public boolean delete(int id) {

        Entity toRemove = searchById(id);
        if (toRemove == null)
            return false;

        thingsDB.remove(toRemove);
        return true;
    }

    private Entity searchById(int id) {
        for (Entity t : thingsDB) {
            if (t.getId().hashCode() == id) {
                return t;
            }
        }
        return null;
    }


}
