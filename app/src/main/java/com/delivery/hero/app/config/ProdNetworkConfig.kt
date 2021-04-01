package com.delivery.hero.app.config

import com.delivery.hero.network.NetworkConfig
import com.delivery.hero.network.NetworkInterceptor
import com.delivery.hero.network.OfflineCacheInterceptor
import okhttp3.Interceptor
import javax.inject.Inject

class ProdNetworkConfig @Inject constructor(
    private val networkInterceptor: NetworkInterceptor,
    private val offlineCacheInterceptor: OfflineCacheInterceptor
) : NetworkConfig {
    override val baseUrl: String
        get() = "https://gist.githubusercontent.com/shehabic-work/"

    override val networkInterceptors: List<Interceptor>
        get() = listOf(networkInterceptor)

    override val interceptors: List<Interceptor>
        get() = listOf(offlineCacheInterceptor)
}
