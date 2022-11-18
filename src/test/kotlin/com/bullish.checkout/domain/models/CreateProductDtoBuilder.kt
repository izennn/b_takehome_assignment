package com.bullish.checkout.domain.models

import com.bullish.checkout.adapters.input.rest.dto.CreateProductDto

object CreateProductDtoBuilder {
    private var dto = CreateProductDtoBuilder.default()

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
        name = "Product",
        description = null,
        price = 1.99
    )
}