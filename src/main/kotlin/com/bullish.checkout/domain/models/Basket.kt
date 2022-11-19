package com.bullish.checkout.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Basket(
    var productCount: MutableMap<String, Int>
)