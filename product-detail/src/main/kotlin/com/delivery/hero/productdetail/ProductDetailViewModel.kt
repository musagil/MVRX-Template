package com.delivery.hero.productdetail

import android.util.Log
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.delivery.hero.BaseViewModel
import com.delivery.hero.ViewModelFactory
import com.delivery.hero.data.ProductInput
import com.delivery.hero.ktx.createViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

internal class ProductDetailViewModel @AssistedInject constructor(
    private val repository: ProductDetailRepository,
    private val productInput: ProductInput,
    @Assisted initialState: ProductDetailState
) : BaseViewModel<ProductDetailState>(initialState) {

    init {
        fetchProduct()
    }

    private fun fetchProduct() {
        repository.fetchProduct(productInput.productId)
            .map(Companion::mapToProductDetailItem)
            .doOnError {
                Log.e("Error", it.message)
            }.execute {
                copy(product = it)
            }
    }

    @AssistedInject.Factory
    interface Factory : ViewModelFactory<ProductDetailState> {
        override fun create(initialState: ProductDetailState): ProductDetailViewModel
    }

    companion object : MvRxViewModelFactory<ProductDetailViewModel, ProductDetailState> {

        override fun create(
            viewModelContext: ViewModelContext,
            state: ProductDetailState
        ): ProductDetailViewModel =
            viewModelContext.createViewModel(state)

        private fun mapToProductDetailItem(product: Product) =
            ProductDetailItem(
                productId = product.id,
                name = product.name,
                image = product.image,
                brand = product.brand,
                price = product.price
            )
    }
}
