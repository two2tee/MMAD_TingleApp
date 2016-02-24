package itu.mmad.dttn.tingle.Model.Dagger2_DependencyInjection.Components;

import javax.inject.Singleton;

import dagger.Component;
import itu.mmad.dttn.tingle.Model.Dagger2_DependencyInjection.Modules.RepositoryModule;
import itu.mmad.dttn.tingle.Model.ThingsDatabase;

/**
 * The connection between the provider of dependencies (located in module classes),@Module,
 * and the classes requesting them through @Inject is made using @Component
 */

@Singleton
@Component(modules = RepositoryModule.class) //specify which modules are going to be used
public interface RepositoryComponent  {
    ThingsDatabase provideDatabase();
}
