package com.delivery.hero.productdetail

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.fragmentViewModel
import com.delivery.hero.BaseFragment
import com.delivery.hero.HasViewModelFactory
import com.delivery.hero.ViewModelFactory
import com.delivery.hero.productdetail.databinding.ProductDetailFragmentBinding
import javax.inject.Inject

class ProductDetailFragment :
    BaseFragment<ProductDetailFragmentBinding>(),
    HasViewModelFactory<ProductDetailState> {

    @Inject
    internal lateinit var viewModelFactory: ProductDetailViewModel.Factory

    @Inject
    internal lateinit var viewBindingFactory: ProductDetailViewBinding.Factory

    override val factory: ViewModelFactory<ProductDetailState>
        get() = viewModelFactory

    override val layoutId: Int
        get() = R.layout.product_detail_fragment

    private val viewModel by fragmentViewModel(ProductDetailViewModel::class)

    override fun invalidate() = viewDataBinding.viewBinding!!.notifyChange()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewBinding = viewBindingFactory.create(viewModel)
    }
}
