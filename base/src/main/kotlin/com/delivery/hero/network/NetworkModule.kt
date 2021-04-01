package com.delivery.hero.network

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

@Module
class NetworkModule {

    @Provides
    internal fun provideOkHttpClient(
        networkConfig: NetworkConfig,
        cache: Cache
    ): OkHttpClient = OkHttpClient.Builder()
        .callTimeout(networkConfig.networkTimeoutInSeconds, SECONDS)
        .readTimeout(networkConfig.networkTimeoutInSeconds, SECONDS)
        .certificatePinner(networkConfig.certificatePinner)
        .cache(cache)
        .addInterceptors(networkConfig)
        .addNetworkInterceptors(networkConfig)
        .build()

    @Provides
    internal fun provideNetwork(
        okHttpClient: OkHttpClient,
        networkConfig: NetworkConfig
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(networkConfig.baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    companion object {
        private fun OkHttpClient.Builder.addInterceptors(networkConfig: NetworkConfig) = apply {
            networkConfig.interceptors.forEach {
                addInterceptor(it)
            }
        }

        private fun OkHttpClient.Builder.addNetworkInterceptors(networkConfig: NetworkConfig) =
            apply {
                networkConfig.networkInterceptors.forEach {
                    addInterceptor(it)
                }
            }
    }
}
