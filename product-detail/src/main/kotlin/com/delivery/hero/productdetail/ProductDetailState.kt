package com.delivery.hero.productdetail

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized

data class ProductDetailState(
    val product: Async<ProductDetailItem> = Uninitialized
) : MvRxState
