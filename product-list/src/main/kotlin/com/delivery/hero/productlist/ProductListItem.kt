package com.delivery.hero.productlist

import com.delivery.hero.BindableItem

data class ProductListItem(
    val productId: String,
    val name: String,
    val brand: String,
    val image: String,
    val price: Price
) : BindableItem {
    override val id: Int = productId.hashCode()
}
