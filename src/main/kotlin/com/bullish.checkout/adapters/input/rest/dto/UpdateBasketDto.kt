package com.bullish.checkout.adapters.input.rest.dto

data class UpdateBasketDto(
    val productCount: Map<String, Int>
)