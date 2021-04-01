package com.delivery.hero.productdetail

import android.content.res.Resources
import com.delivery.hero.NavigationViewBinding
import com.nhaarman.mockitokotlin2.mock

internal fun testViewBinding(
    fragment: ProductDetailFragment = ProductDetailFragment(),
    reduce: ProductDetailState.() -> ProductDetailState = { this },
    resources: Resources = mock(),
    viewModel: ProductDetailViewModel = testViewModel(reduce = reduce)
) = ProductDetailViewBinding(
    fragment,
    viewModel,
    NavigationViewBinding(fragment),
    resources
)
