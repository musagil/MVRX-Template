package com.delivery.hero.productlist

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized

data class ProductListState(
    val products: Async<List<ProductListItem>> = Uninitialized,
    val productClickedId: String? = null
) : MvRxState
