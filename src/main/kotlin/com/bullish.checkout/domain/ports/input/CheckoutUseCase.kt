package com.bullish.checkout.domain.ports.input

import com.bullish.checkout.domain.models.Receipt

interface CheckoutUseCase {
    fun checkout(): Receipt
}