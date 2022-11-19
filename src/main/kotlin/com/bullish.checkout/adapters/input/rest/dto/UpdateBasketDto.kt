package com.bullish.checkout.adapters.input.rest.dto

import com.bullish.checkout.domain.models.Basket
import kotlinx.serialization.Serializable

@Serializable
data class UpdateBasketDto(
    var productCount: Map<String, Int>
) {
    fun toDomain(): Basket {
        return Basket(
            productCount = productCount as MutableMap<String, Int>
        )
    }
}