package itu.mmad.dttn.tingle;

import android.support.v4.os.OperationCanceledException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import itu.mmad.dttn.tingle.model.Interfaces.IRepository;
import itu.mmad.dttn.tingle.model.Thing;
import itu.mmad.dttn.tingle.model.ThingsDatabase;

/**
 * Tests for thingsdatabase
 */
public class ThingsDatabaseTests
{
    private ThingsDatabase SUT;
    private IRepository<Thing> mockRepository;
    @Before
    public void setup(){
         mockRepository = Mockito.mock(IRepository.class);

    }

    @Test
    public void getall_Collection_NotEmpty(){
        List<Thing> toReturn = new ArrayList<>();
        toReturn.add(new Thing("w","x"));
        toReturn.add(new Thing("k", "y"));

        Mockito.when(mockRepository.getAll()).thenReturn(toReturn.iterator());

        SUT = new ThingsDatabase(mockRepository);

        List items = SUT.getAll();
        boolean isEmpty = items.isEmpty();
        int actualSize = items.size();
        int expectedSize = 2;


        Assert.assertFalse("The returned collection is empty",isEmpty);
        Assert.assertTrue("Should contain two items but returned " + actualSize, actualSize == expectedSize);
    }

    @Test
    public void get_NotNull_True(){
        Thing toReturn = new Thing("a", "b");
        UUID dummyId = toReturn.getId();

        Mockito.when(mockRepository.get(dummyId.hashCode())).thenReturn(toReturn);

        SUT = new ThingsDatabase(mockRepository);
        Thing actual = SUT.get(dummyId);

        Assert.assertTrue("Returned null, but should return a thing", actual != null);
    }

    @Test
    public void getTotalSize_moreThanZero_True(){
        List<Thing> toReturn = new ArrayList<>();
        toReturn.add(new Thing("w","x"));
        toReturn.add(new Thing("k", "y"));

        Mockito.when(mockRepository.getAll()).thenReturn(toReturn.iterator());

        SUT = new ThingsDatabase(mockRepository);

        int expected = 2;
        int actual = SUT.getTotalSize();

        Assert.assertTrue("Should have a total size of 2", expected==actual);
    }

    @Test
    public void getTotalSize_isZero_True(){
    List<Thing> toReturn = new ArrayList<>();

    Mockito.when(mockRepository.getAll()).thenReturn(toReturn.iterator());

    SUT = new ThingsDatabase(mockRepository);

    int expected = 0;
    int actual = SUT.getTotalSize();

    Assert.assertTrue("Should have a total size of 0", expected==actual);
    }

    @Test
    public void delete_ExistingITem_NoException(){
        UUID dummyId = UUID.randomUUID();
        Mockito.when(mockRepository.delete(dummyId.hashCode())).thenReturn(true);
        boolean exceptionThrown = false;

        SUT = new ThingsDatabase(mockRepository);


        try
        {
            SUT.delete(dummyId);
        }
        catch (OperationCanceledException e){
            exceptionThrown = true;
        }


        Assert.assertFalse("Should not throw exception here", exceptionThrown);
    }

    @Test
    public void delete_NonExistingITem_ExceptionThrown(){
        UUID dummyId = UUID.randomUUID();
        Mockito.when(mockRepository.delete(dummyId.hashCode())).thenReturn(false);
        boolean exceptionThrown = false;

        SUT = new ThingsDatabase(mockRepository);


        try
        {
            SUT.delete(dummyId);
        }
        catch (OperationCanceledException e){
            exceptionThrown = true;
        }


        Assert.assertTrue("Should have thrown an exception", exceptionThrown);
    }

    @Test
    public void update_ExistingItem_true(){
        Thing toUpdateDummy = new Thing("","");
        Mockito.when(mockRepository.update(toUpdateDummy)).thenReturn(true);

        SUT = new ThingsDatabase(mockRepository);
        boolean expected = SUT.update(toUpdateDummy);

        Assert.assertTrue(expected);
    }

    @Test
    public void update_NonExistingItem_false(){
        Thing toUpdateDummy = new Thing("","");
        Mockito.when(mockRepository.update(toUpdateDummy)).thenReturn(false);

        SUT = new ThingsDatabase(mockRepository);
        boolean expected = SUT.update(toUpdateDummy);

        Assert.assertFalse(expected);
    }

}
