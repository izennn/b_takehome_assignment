package com.bullish.checkout.domain.ports.input

interface DeletingDiscountUseCase {
    fun deleteDiscount(id: String)
}