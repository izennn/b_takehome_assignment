package com.bullish.checkout.domain.models

data class Basket(
    val customerId: String,
    val productCount: Map<String, Int>
)