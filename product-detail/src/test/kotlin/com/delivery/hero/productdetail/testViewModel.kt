package com.delivery.hero.productdetail

import com.delivery.hero.data.ProductInput
import com.nhaarman.mockitokotlin2.mock

internal fun testViewModel(
    repository: ProductDetailRepository = mock(),
    productInput: ProductInput = ProductInput("1"),
    reduce: ProductDetailState.() -> ProductDetailState = { this }
) = ProductDetailViewModel(
    repository,
    productInput,
    ProductDetailState().reduce()
)
