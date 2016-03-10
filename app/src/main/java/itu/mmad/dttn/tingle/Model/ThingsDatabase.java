package itu.mmad.dttn.tingle.Model;

import android.support.v4.os.OperationCanceledException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import itu.mmad.dttn.tingle.Model.Interfaces.IRepository;

/**
 * Represents a database that contains a repository.
 * This class is used to separate the repository of application logic
 */


public class ThingsDatabase {

    final IRepository<Thing> repository;
    private static boolean isFilled = false;

    @Inject //annotation to request dependencies in constructor,
    public ThingsDatabase(IRepository repository) {
        this.repository = repository;
        fillThingsDB(); //TODO remember to remove
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
        if (entity == null) return;
        repository.put(entity);
    }

    /**
     * Updates existing thing
     *
     * @param entity thing
     *               <p/>
     *               not implemented yet
     */
    public void update(Thing entity) {
        //Not implemented yet
    }

    /**
     * Deletes a thing from database
     *
     * @param id of thing
     * @throws OperationCanceledException if item can not be found
     */
    public void delete(int id) throws OperationCanceledException {
        boolean isSuccess = repository.delete(id);
        if (!isSuccess) throw new OperationCanceledException("Deletion failed.");
    }

    /**
     * Returns the total numbers of things
     *
     * @return int
     */
    public int getTotalSize() {

        int size = 0;

        Iterator<Thing> items = repository.getAll();
        while (items.hasNext()) {
            size++;
            items.next();
        }

        return size;
    }

    /**
     * This is only used during development
     * Todo Remember to remove it when done
     * todo also remember to remove TingleFragment.isFilled field
     */
    private void fillThingsDB() {
        if (!ThingsDatabase.isFilled) {
            Random random = new Random();

            String[] locations = new String[]{
                    "Desk",
                    "Carport",
                    "Car",
                    "Washing machine",
                    "Uncle johns house",
                    "Kindergarten",
                    "Kitchen",
                    "Bedroom desk",
                    "Garden",
                    "Office",
                    "Bag",
                    "Grocery store",
                    "Back pocket",
                    "Shelves",
                    "Living room table",
                    "Vietnam diner",
                    "Gym changing room"



            };
            String[] items = new String[]{
                    "car keys",
                    "phone",
                    "Kids",
                    "Wallet",
                    "Laptop",
                    "Passport",
                    "Train tickets",
                    "Milk",
                    "Shoes",
                    "Eye liner",
                    "white out",
                    "newspaper",
                    "USB stick",
                    "charger",
                    "boombox",
                    "toys",
                    "Credit card",
                    "shovel",
                    "Pencil",
                    "Bucket",
                    "Calculator"
            };

            for(int x =  0; x < 10;x++){
                repository.put(new Thing(items[random.nextInt(items.length)],locations[random.nextInt(locations.length)]));
            }
            ThingsDatabase.isFilled = true;
        }
    }
}
