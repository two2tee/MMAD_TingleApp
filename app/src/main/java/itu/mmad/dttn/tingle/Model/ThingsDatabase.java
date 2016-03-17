package itu.mmad.dttn.tingle.Model;

import android.support.v4.os.OperationCanceledException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.inject.Inject;

import itu.mmad.dttn.tingle.Model.Dagger2_DependencyInjection.Components.DaggerRepositoryComponent;
import itu.mmad.dttn.tingle.Model.Dagger2_DependencyInjection.Components.RepositoryComponent;
import itu.mmad.dttn.tingle.Model.Dagger2_DependencyInjection.Modules.RepositoryModule;
import itu.mmad.dttn.tingle.Model.Interfaces.IRepository;

/**
 * Represents a database that contains a repository.
 * This class is used to separate the repository of application logic
 */


public class ThingsDatabase {

    //Setup database with injection
    private static ThingsDatabase DATABASE;
    public static ThingsDatabase getDatabase(){
        if (DATABASE == null) {
            //Setting dependency injection for database and apply
            RepositoryComponent component = DaggerRepositoryComponent.builder()
                    .repositoryModule(new RepositoryModule()).build();

            DATABASE = component.provideDatabase();
        }
        return DATABASE;
    }

    final IRepository<Thing> repository;
    private static boolean isFilled = false;

    @Inject //annotation to request dependencies in constructor,
    public ThingsDatabase(IRepository repository) {
        this.repository = repository;
        fillThingsDB(); //TODO remember to remove
    }

    public Thing get(UUID id) {
        return repository.get(id.hashCode());
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
    public void delete(UUID id) throws OperationCanceledException {
        boolean isSuccess = repository.delete(id.hashCode());
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
