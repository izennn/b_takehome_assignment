package com.bullish.checkout.domain.ports.input

import com.bullish.checkout.adapters.input.rest.dto.CreateProductDto
import com.bullish.checkout.domain.models.Product

interface CreatingProductUseCase {
    fun createProduct(createProductDto: CreateProductDto): Product
}