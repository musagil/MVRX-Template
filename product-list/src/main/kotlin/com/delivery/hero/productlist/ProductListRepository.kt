package com.delivery.hero.productlist

import com.delivery.hero.OpenForTesting
import io.reactivex.Single
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@OpenForTesting
internal class ProductListRepository @Inject constructor(
    private val requests: ProductListRequests
) {

    @CheckReturnValue
    internal fun fetchProducts(): Single<List<Product>> =
        requests
            .productList()
            .subscribeOn(Schedulers.io())
            .map {
                it.items
                    .filter { item -> item.isActive }
                    .sortedBy { item -> item.name }
            }
}
