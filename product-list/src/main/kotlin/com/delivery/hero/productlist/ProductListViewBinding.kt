package com.delivery.hero.productlist

import android.content.res.Resources
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.withState
import com.delivery.hero.BaseViewBinding
import com.delivery.hero.FragmentNavigation
import com.delivery.hero.NavigationViewBinding
import com.delivery.hero.data.ProductInput
import com.delivery.hero.ktx.navigateTo
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

internal class ProductListViewBinding @AssistedInject constructor(
    fragment: ProductListFragment,
    @Assisted override val viewModel: ProductListViewModel,
    val navigationViewBinding: NavigationViewBinding,
    val navigation: FragmentNavigation,
    resources: Resources
) : BaseViewBinding<ProductListState>(fragment, viewModel) {

    val adapter = ProductAdapter(viewModel)

    val productListItems: List<ProductListItem>
        get() = withState(viewModel) {
            it.products().orEmpty()
        }

    val shouldShowRefreshing: Boolean
        get() = withState(viewModel) {
            it.products is Loading
        }

    val swipeToRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        viewModel.fetchProducts()
    }

    init {
        navigationViewBinding.toolbarTitle = resources.getString(R.string.app_name)
        subscribeToProductClickedId()
    }

    private fun subscribeToProductClickedId() {
        viewModel.selectSubscribeNonNull(
            ProductListState::productClickedId
        ) { productId ->
            navigation.navigate(
                R.id.productDetailFragment,
                ProductInput(productId)
            )
            viewModel.onNavigatedToProductDetail()
        }
    }

    @AssistedInject.Factory
    interface Factory {

        fun create(viewModel: ProductListViewModel): ProductListViewBinding
    }
}
