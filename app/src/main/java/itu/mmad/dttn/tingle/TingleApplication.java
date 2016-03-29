package itu.mmad.dttn.tingle;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Components.ApplicationComponent;
import itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Components.DaggerApplicationComponent;
import itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Modules.ApplicationModule;
import itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Modules.RepositoryModule;
import itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Qualifiers.QSQLRepository;
import itu.mmad.dttn.tingle.model.database.ThingsDatabase;


/**
 * Is mainly used as container for dagger2 components
 */
public class TingleApplication extends Application {

    private ApplicationComponent mApplicationComponent;
    @Inject static ThingsDatabase DATABASE;


    public static ThingsDatabase getDatabase() {
        return DATABASE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .repositoryModule(new RepositoryModule()).build();

        getComponent(this).inject(this);
    }


    public static TingleApplication getApplication(Context context){
        return (TingleApplication) context.getApplicationContext();
    }

    public static ApplicationComponent getComponent(Context context){
        return ((TingleApplication) context.getApplicationContext()).mApplicationComponent;
    }
}
