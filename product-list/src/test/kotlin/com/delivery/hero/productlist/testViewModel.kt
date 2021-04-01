package com.delivery.hero.productlist

import com.nhaarman.mockitokotlin2.mock

internal fun testViewModel(
    repository: ProductListRepository = mock(),
    reduce: ProductListState.() -> ProductListState = { this }
) = ProductListViewModel(
    repository,
    ProductListState().reduce()
)
