package com.delivery.hero.productdetail

import com.delivery.hero.OpenForTesting
import io.reactivex.Single
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@OpenForTesting
internal class ProductDetailRepository @Inject constructor(
    private val requests: ProductDetailRequests
) {

    @CheckReturnValue
    internal fun fetchProduct(id: String): Single<Product> =
        requests
            .productList()
            .subscribeOn(Schedulers.io())
            .map {
                it.items.first { item -> item.id == id }
            }
}
