package itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import itu.mmad.dttn.tingle.TingleApplication;
import itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Scopes.ApplicationScope;

/**
 * A module for Android-specific dependencies
 */
@Module
public class ApplicationModule {
    private final TingleApplication mApplication;

    public ApplicationModule(TingleApplication application) {
        mApplication = application;
    }

    /**
     * Allow the application context to be injected but require that it be annotated with
     * {@link ApplicationScope @Annotation} to explicitly differentiate it from an activity context.
     */

    @Provides
    @Singleton
    Context provideApplication() {
        return mApplication.getApplicationContext();
    }


}
