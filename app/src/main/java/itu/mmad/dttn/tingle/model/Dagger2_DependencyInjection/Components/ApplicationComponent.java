package itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Components;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;
import javax.*;

import dagger.Component;
import itu.mmad.dttn.tingle.TingleApplication;
import itu.mmad.dttn.tingle.controller.BaseActivity;
import itu.mmad.dttn.tingle.controller.DetailedThingActivity;
import itu.mmad.dttn.tingle.controller.TingleActivity;
import itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Modules.ApplicationModule;
import itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Modules.RepositoryModule;

/**
 * This interface is a component in Dagger2 dependency injection framework
 * A component (hence the tag @component) act is a bridge interface between modules and injection
 * This component provides dependencies that has a lifetime of the application.
 * http://google.github.io/dagger/users-guide.html
 */
@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class})
public interface ApplicationComponent {

//    void inject(TingleApplication application);
//    void inject(BaseActivity baseActivity);
//    void inject(DetailedThingActivity detailedThingActivity);
//    void inject(TingleActivity tingleActivity);



}
