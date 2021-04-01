package com.delivery.hero.productlist

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductListResponse(
    val items: List<Product>
)

@JsonClass(generateAdapter = true)
data class Product(
    val id: String,
    val sku: String,
    val name: String,
    val image: String,
    val brand: String,
    @Json(name = "is_active")
    val isActive: Boolean,
    val price: Price
)

@JsonClass(generateAdapter = true)
data class Price(
    val current: Double,
    val original: Double,
    val currency: String
)
