package com.delivery.hero.app.di;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.speech.SpeechRecognizer;

import com.delivery.hero.app.App;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;

@Module
interface AppModule {

    @Provides
    static Resources resources(App app) {
        return app.getResources();
    }

    @Provides
    static Cache okhttpCache(App application) {
        long cacheSize5Mb = 5L * 1024L * 1024L;
        return new Cache(application.getCacheDir(), cacheSize5Mb);
    }

    @Provides
    static ConnectivityManager connectivityManager(App application) {
        return (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

}
