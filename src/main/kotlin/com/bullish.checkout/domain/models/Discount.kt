package com.bullish.checkout.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Discount (
    val productId: String,
    val productCount: Int,
    val discountInPercentage: Int? = null,
    val discountInPrice: Double? = null
)
