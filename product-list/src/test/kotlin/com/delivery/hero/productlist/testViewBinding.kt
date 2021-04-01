package com.delivery.hero.productlist

import android.content.res.Resources
import com.delivery.hero.FragmentNavigation
import com.delivery.hero.NavigationViewBinding
import com.nhaarman.mockitokotlin2.mock

internal fun testViewBinding(
    fragment: ProductListFragment = ProductListFragment(),
    reduce: ProductListState.() -> ProductListState = { this },
    resources: Resources = mock(),
    navigation: FragmentNavigation = mock(),
    viewModel: ProductListViewModel = testViewModel(reduce = reduce)
) = ProductListViewBinding(
    fragment,
    viewModel,
    NavigationViewBinding(fragment),
    navigation,
    resources
)
