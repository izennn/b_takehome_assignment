package com.bullish.checkout.domain.models

import com.bullish.checkout.domain.service.ReceiptItem
import kotlinx.serialization.Serializable

@Serializable
data class Receipt(
    val items: List<ReceiptItem>,
    val totalPriceBeforeDiscount: Double,
    val totalDiscount: Double,
    val totalPrice: Double
)