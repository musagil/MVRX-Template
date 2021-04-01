package com.delivery.hero.productdetail

import com.delivery.hero.network.Cacheable
import io.reactivex.Single
import io.reactivex.annotations.CheckReturnValue
import retrofit2.http.GET

internal interface ProductDetailRequests {

    @CheckReturnValue
    @Cacheable
    @GET("514884a074f872f8cdc30ab71a4703df/raw/2c94601c85abaca7b7abaea33ae4a05b89da1970/foodora-sample-products.json")
    fun productList(): Single<ProductListResponse>
}
