package com.delivery.hero.app.di;

import com.delivery.hero.app.config.ProdNetworkConfig;
import com.delivery.hero.network.NetworkConfig;

import javax.inject.Provider;

import dagger.Module;
import dagger.Provides;

@Module
interface NetworkConfigModule {

    @Provides
    static NetworkConfig networkConfig(
            Provider<ProdNetworkConfig> prodProvider
    ) {
        return prodProvider.get();
    }
}
