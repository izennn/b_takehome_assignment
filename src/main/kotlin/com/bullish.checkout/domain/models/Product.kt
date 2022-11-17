package com.bullish.checkout.domain.models

data class Product(
    val id: String,
    val name: String,
    val description: String?,
    val price: Double
)