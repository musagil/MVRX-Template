package com.delivery.hero.app.di;

import com.delivery.hero.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
interface ActivityBindingsModule {
    @ContributesAndroidInjector
    MainActivity mainActivityInjector();
}
