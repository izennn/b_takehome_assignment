package com.bullish.checkout.domain.ports.input

interface DeletingProductUseCase {
    fun deleteProduct(productId: String)
}