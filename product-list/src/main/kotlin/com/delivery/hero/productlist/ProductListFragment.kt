package com.delivery.hero.productlist

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.fragmentViewModel
import com.delivery.hero.BaseFragment
import com.delivery.hero.HasViewModelFactory
import com.delivery.hero.ViewModelFactory
import com.delivery.hero.productlist.databinding.ProductListFragmentBinding
import javax.inject.Inject

class ProductListFragment :
    BaseFragment<ProductListFragmentBinding>(),
    HasViewModelFactory<ProductListState> {

    @Inject
    internal lateinit var viewModelFactory: ProductListViewModel.Factory

    @Inject
    internal lateinit var viewBindingFactory: ProductListViewBinding.Factory

    override val factory: ViewModelFactory<ProductListState>
        get() = viewModelFactory

    override val layoutId: Int
        get() = R.layout.product_list_fragment

    private val viewModel by fragmentViewModel(ProductListViewModel::class)

    override fun invalidate() = viewDataBinding.viewBinding!!.notifyChange()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewBinding = viewBindingFactory.create(viewModel)
    }
}
