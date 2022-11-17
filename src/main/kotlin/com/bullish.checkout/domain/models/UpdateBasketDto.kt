package com.bullish.checkout.domain.models

data class UpdateBasketDto(
    val products: Map<String, Int>
)