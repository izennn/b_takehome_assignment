package com.bullish.checkout.domain.ports.input

import com.bullish.checkout.domain.models.Product

interface CreatingProductUseCase {
    fun createProduct(product: Product)
}