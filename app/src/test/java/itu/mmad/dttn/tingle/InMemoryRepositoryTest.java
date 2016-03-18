package itu.mmad.dttn.tingle;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import itu.mmad.dttn.tingle.model.InMemoryRepository;
import itu.mmad.dttn.tingle.model.Thing;

/**
 * UNIT test of inMemoryRepo
 */
public class InMemoryRepositoryTest
{

    InMemoryRepository SUT;

    @Before
    public void setup(){
        SUT = new InMemoryRepository();
    }

    @Test
    public void newRepo_isEmpty_True()
    {
        Assert.assertFalse("Sut contained items on first creation", SUT.getAll().hasNext());
    }

    @Test
    public void put_ValidItem_notNull(){
        Thing dummy = new Thing("x","y");
        SUT.put(dummy);
        Assert.assertNotNull("Valid item was not added", SUT.get(dummy.getId().hashCode()));
    }

    @Test
    public void put_ValidItem_True(){
        Thing dummy = new Thing("x","y");
        boolean result = SUT.put(dummy);
        Assert.assertTrue("put did not return true on valid argument", result);
    }

    @Test
    public void put_invalidItem_Null_False(){
        boolean result = SUT.put(null);
        Assert.assertFalse("put should not accept null arguments", result);
    }


    @Test
    public void delete_existingItem_True(){
        Thing dummy = new Thing("x","y");
        SUT.put(dummy);
        boolean result = SUT.delete(dummy.getId().hashCode());
        Assert.assertTrue("Did not delete item", result);
    }

    @Test
    public void delete_NonExistingItem_False(){
        int invalidId = 0;
        boolean result = SUT.delete(invalidId);
        Assert.assertFalse("Item should not exists, but delete was still called", result);
    }

    @Test
    public void update_ExistingItem_ValidArgument_True(){
        Thing dummy = new Thing("x","y");
        SUT.put(dummy);

        boolean expected = SUT.update(dummy);

        Assert.assertTrue(expected);
    }


     @Test
    public void update_NonExistingItem_False(){
         Thing dummy = new Thing("x","y");
         boolean expected = SUT.update(dummy);
         Assert.assertFalse(expected);
    }


}
