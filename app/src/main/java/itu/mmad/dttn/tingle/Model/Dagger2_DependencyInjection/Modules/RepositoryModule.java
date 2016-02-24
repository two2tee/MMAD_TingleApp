package itu.mmad.dttn.tingle.Model.Dagger2_DependencyInjection.Modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import itu.mmad.dttn.tingle.Model.InMemoryRepository;
import itu.mmad.dttn.tingle.Model.Interfaces.IRepository;
import itu.mmad.dttn.tingle.Model.ThingsDatabase;

/**
 * This class is going to provide the repository dependencies
 * Used for dependency injection
 *
 * Using dagger2 framework
 */

@Module //annotation to provide dependencies
public class RepositoryModule {


    @Provides @Singleton
    IRepository provideInMemRepository(){
        return new InMemoryRepository();
    }


    @Provides @Singleton
    ThingsDatabase provideDatabase(IRepository repository){
        return new ThingsDatabase(repository);
    }


}
