package com.delivery.hero.app.di;

import com.delivery.hero.PerApplication;
import com.delivery.hero.app.App;
import com.delivery.hero.network.NetworkModule;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(
        modules = {
                ActivityBindingsModule.class,
                AndroidSupportInjectionModule.class,
                AppModule.class,
                NetworkModule.class,
                NetworkConfigModule.class,
                FragmentBindingsModule.class
        }
)
@PerApplication
public interface AppComponent extends AndroidInjector<App> {

    @Component.Factory
    interface Factory extends AndroidInjector.Factory<App> {
    }
}
