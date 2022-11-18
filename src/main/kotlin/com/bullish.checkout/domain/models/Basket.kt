package com.bullish.checkout.domain.models

data class Basket(
    var productCount: MutableMap<String, Int>
)