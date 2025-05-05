package com.example.mobilproje

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val name: String,
    val price: Double,
    var quantity: Int = 1
) : Parcelable {
    fun totalPrice(): Double {
        return price * quantity
    }
}