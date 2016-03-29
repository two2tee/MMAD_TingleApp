package itu.mmad.dttn.tingle;

import junit.framework.Assert;

import org.junit.Test;

import itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Components.ApplicationComponent;
import itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Components.DaggerApplicationComponent;
import itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Modules.ApplicationModule;
import itu.mmad.dttn.tingle.model.database.ThingsDatabase;

/**
 * Tests for dagger dependency injection
 */
public class DaggerDependencyInjectionTests
{

//    @Test
//    public void DatabaseProvided_NotNull() {
//        ThingsDatabase SUT;
//        ApplicationComponent component = DaggerApplicationComponent.builder()
//                .repositoryModule(new ApplicationModule()).build();
//
//        SUT = component.provideDatabase();
//
//        Assert.assertNotNull(SUT);
//    }
//
//    @Test
//    public void DatabaseProvided_instanceOfThingsDatabase() {
//        ThingsDatabase SUT;
//        ApplicationComponent component = DaggerApplicationComponent.builder()
//                .repositoryModule(new ApplicationModule()).build();
//
//        SUT = component.provideDatabase();
//
//        Assert.assertTrue(SUT instanceof ThingsDatabase);
//    }
}
