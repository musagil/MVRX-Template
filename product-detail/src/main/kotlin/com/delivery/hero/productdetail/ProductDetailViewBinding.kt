package com.delivery.hero.productdetail

import android.content.res.Resources
import android.view.View.*
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.withState
import com.delivery.hero.BaseViewBinding
import com.delivery.hero.NavigationViewBinding
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

internal class ProductDetailViewBinding @AssistedInject constructor(
    fragment: ProductDetailFragment,
    @Assisted override val viewModel: ProductDetailViewModel,
    val navigationViewBinding: NavigationViewBinding,
    resources: Resources
) : BaseViewBinding<ProductDetailState>(fragment, viewModel) {

    val image: String
        get() = withState(viewModel) {
            it.product()?.image ?: ""
        }

    val imageVisibility: Int
        get() = withState(viewModel) {
            if (it.product is Success) VISIBLE else GONE
        }

    init {
        navigationViewBinding.toolbarTitle = resources.getString(R.string.loading)
        subscribeToProduct()
    }

    private fun subscribeToProduct() {
        viewModel.asyncSubscribe(
            ProductDetailState::product
        ) { product ->
            navigationViewBinding.toolbarTitle = product.name
        }
    }

    @AssistedInject.Factory
    interface Factory {

        fun create(viewModel: ProductDetailViewModel): ProductDetailViewBinding
    }
}
