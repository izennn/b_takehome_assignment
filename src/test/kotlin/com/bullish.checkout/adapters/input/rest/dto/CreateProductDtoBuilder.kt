package com.bullish.checkout.adapters.input.rest.dto

import com.bullish.checkout.TestUtils.SAMPLE_PRODUCT_DESCR
import com.bullish.checkout.TestUtils.SAMPLE_PRODUCT_NAME
import com.bullish.checkout.TestUtils.SAMPLE_PRODUCT_PRICE

object CreateProductDtoBuilder {
    private var dto = default()

    @JvmName("setName")
    fun set(name: String): CreateProductDtoBuilder {
        dto.name = name
        return this
    }

    @JvmName("setDescription")
    fun set(description: String?): CreateProductDtoBuilder {
        dto.description = description
        return this
    }

    @JvmName("setPrice")
    fun set(price: Double): CreateProductDtoBuilder {
        dto.price = price
        return this
    }

    fun build(): CreateProductDto {
        return dto
    }

    private fun default() = CreateProductDto(
        name = SAMPLE_PRODUCT_NAME,
        description = SAMPLE_PRODUCT_DESCR,
        price = SAMPLE_PRODUCT_PRICE
    )
}