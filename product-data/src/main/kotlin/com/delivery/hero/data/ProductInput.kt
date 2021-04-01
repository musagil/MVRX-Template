package com.delivery.hero.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductInput(
    val productId: String
) : Parcelable
