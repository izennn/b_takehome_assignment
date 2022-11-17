package com.bullish.checkout.domain.models

data class CreateProductDto(
    val name: String,
    val description: String?,
    val price: Double
)