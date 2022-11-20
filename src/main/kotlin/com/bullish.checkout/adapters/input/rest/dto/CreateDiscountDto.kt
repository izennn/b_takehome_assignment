package com.bullish.checkout.adapters.input.rest.dto

import com.bullish.checkout.domain.models.Discount
import kotlinx.serialization.Serializable

@Serializable
data class CreateDiscountDto(
    var id: String,
    var productCount: Int,
    var discountInPercentage: Int? = null,
    var discountInPrice: Double? = null
) {
    fun toDomain() = Discount(
        productId = id,
        productCount = productCount,
        discountInPercentage = discountInPercentage,
        discountInPrice = discountInPrice
    )
}