package itu.mmad.dttn.tingle;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import itu.mmad.dttn.tingle.Model.Interfaces.IRepository;
import itu.mmad.dttn.tingle.Model.Thing;
import itu.mmad.dttn.tingle.Model.ThingsDatabase;

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


        //Mockito.when(mockRepository.getAll())).thenReturn(new Thing("",""));

    }

}
