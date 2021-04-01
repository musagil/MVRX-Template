package com.delivery.hero.productlist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.delivery.hero.BindableListAdapter

internal class ProductAdapter(
    private val viewModel: ProductListViewModel
) : BindableListAdapter<ProductListItem, ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            parent,
            ProductAdapterItemViewBinding(),
            viewModel
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemId(position: Int) = getItem(position).id.toLong()

    class ViewHolder(
        parent: ViewGroup,
        private val viewBinding: ProductAdapterItemViewBinding,
        private val viewModel: ProductListViewModel
    ) : RecyclerView.ViewHolder(
        viewBinding.createView(parent)
    ) {

        fun bind(productListItem: ProductListItem) {
            viewBinding.bindItem(productListItem, viewModel)
        }
    }
}
