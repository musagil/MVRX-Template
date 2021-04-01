package com.delivery.hero.productlist

import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.View.*
import android.view.ViewGroup
import com.delivery.hero.AdapterItemViewBinding
import com.delivery.hero.productlist.databinding.ProductListAdapterItemBinding

internal class ProductAdapterItemViewBinding :
    AdapterItemViewBinding<ProductListItem, ProductListAdapterItemBinding, ProductListViewModel>() {

    override fun createView(parent: ViewGroup) = parent.inflate(R.layout.product_list_adapter_item)

    val name: String
        get() = item.name

    val priceOriginal: SpannableString
        get() = item.price.toFormattedOriginalPrice()

    val priceCurrent: String
        get() = item.price.toFormattedCurrentPrice()

    val brand: String
        get() = item.brand

    val originalPriceVisibility: Int
        get() = if (item.price.hasDiscount()) VISIBLE else GONE

    val image: String
        get() = item.image

    val conversationClickListener = OnClickListener {
        viewModel.onConversationClick(item)
    }

    fun groupAnagrams(strs: Array<String>): List<List<String>> {

        return strs.groupBy {
            String(it.toCharArray().sortedArray())
        }.values.toList()
    }

    companion object {
        private fun Price.toFormattedOriginalPrice(): SpannableString =
            SpannableString(String.format("${currency.getCurrencyCode()} %.2f", original)).apply {
                setSpan(StrikethroughSpan(), 0, this.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

        private fun Price.toFormattedCurrentPrice(): String =
            String.format("${currency.getCurrencyCode()} %.2f", current)

        private fun Price.hasDiscount() = original > current

        private fun String.getCurrencyCode(): String = when (this) {
            "EUR" -> "€"
            else -> "$"
        }
    }
}
