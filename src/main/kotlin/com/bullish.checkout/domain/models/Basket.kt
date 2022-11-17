package com.bullish.checkout.domain.models

data class Basket(
    val productCount: Map<String, Int>
)