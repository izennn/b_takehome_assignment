package com.bullish.checkout.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Receipt(
    val price: Double
)