package com.bullish.checkout.domain.ports.input

import com.bullish.checkout.domain.models.Discount

interface CreatingDiscountUseCase {
    fun createDiscount(discount: Discount)
}