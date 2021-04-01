package com.delivery.hero

interface BindableItem {

    val id: Int

    // Forces implementations to be data class
    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}
