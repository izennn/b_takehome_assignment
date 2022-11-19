package com.bullish.checkout.adapters.input.rest.dto

object UpdateBasketDtoBuilder {
    private var dto = default()

    fun set(newProductCount: MutableMap<String, Int>): UpdateBasketDtoBuilder {
        dto.productCount = newProductCount
        return this
    }

    fun build(): UpdateBasketDto {
        return dto
    }

    private fun default() = UpdateBasketDto(
        productCount = mutableMapOf()
    )
}