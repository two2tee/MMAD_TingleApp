package itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import itu.mmad.dttn.tingle.model.Networking.NetworkManager;

/**
 * Module for networking
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    static NetworkManager provideNetworkManager() {
        return new NetworkManager();
    }
}
