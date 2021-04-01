package com.delivery.hero.app;

import com.delivery.hero.app.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class App extends DaggerApplication {

    @Override
    protected AndroidInjector<App> applicationInjector() {
        return DaggerAppComponent.factory().create(this);
    }
}
