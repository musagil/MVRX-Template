package com.delivery.hero.productdetail

import com.delivery.hero.BindableItem

data class ProductDetailItem(
    val productId: String,
    val name: String,
    val brand: String,
    val image: String,
    val price: Price
) : BindableItem {
    override val id: Int = productId.hashCode()
}
