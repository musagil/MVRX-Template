package com.delivery.hero.productlist

import android.util.Log
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.delivery.hero.BaseViewModel
import com.delivery.hero.ViewModelFactory
import com.delivery.hero.ktx.createViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

internal class ProductListViewModel @AssistedInject constructor(
    private val repository: ProductListRepository,
    @Assisted initialState: ProductListState
) : BaseViewModel<ProductListState>(initialState) {

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        repository.fetchProducts()
            .map(Companion::mapToProductListItem)
            .doOnError {
                Log.e("Error", it.message)
            }.execute {
                copy(products = it)
            }
    }

    fun onConversationClick(productListItem: ProductListItem) = setState {
        copy(productClickedId = productListItem.productId)
    }

    fun onNavigatedToProductDetail() = setState {
        copy(productClickedId = null)
    }

    @AssistedInject.Factory
    interface Factory : ViewModelFactory<ProductListState> {
        override fun create(initialState: ProductListState): ProductListViewModel
    }

    companion object : MvRxViewModelFactory<ProductListViewModel, ProductListState> {

        override fun create(
            viewModelContext: ViewModelContext,
            state: ProductListState
        ): ProductListViewModel =
            viewModelContext.createViewModel(state)

        private fun mapToProductListItem(products: List<Product>) =
            products.map {
                ProductListItem(
                    productId = it.id,
                    name = it.name,
                    image = it.image,
                    brand = it.brand,
                    price = it.price
                )
            }
    }
}
