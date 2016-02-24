package itu.mmad.dttn.tingle.Model;

import android.support.v4.os.OperationCanceledException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import itu.mmad.dttn.tingle.Model.Interfaces.IRepository;

/**
 * Created by new on 24-Feb-16.
 */


public class ThingsDatabase {

    IRepository<Thing> repository;

    @Inject //annotation to request dependencies in constructor,
    public ThingsDatabase(IRepository repository) {
        this.repository = repository;
    }

    public Thing get(int id) {
        return repository.get(id);
    }

    public List<Thing> getAll() {

        ArrayList things = new ArrayList();
        Iterator<Thing> items = repository.getAll();
        while (items.hasNext()) {
            things.add(items.next());
        }

        return things;
    }

    public void put(Thing entity) {
        repository.put(entity);
    }

    public void update(Thing entity) {
    }

    public void delete(int id)throws OperationCanceledException
    {
        boolean isSuccess = repository.delete(id);
        if(!isSuccess) throw new OperationCanceledException("Deletion failed.");
    }

    public int getTotalSize() {

        int size = 0;

        Iterator<Thing> items = repository.getAll();
        while (items.hasNext()) {
            size++;
            items.next();
        }

        return size;
    }
}
