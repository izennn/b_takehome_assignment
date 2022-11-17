package com.bullish.checkout.domain.ports.output

interface RemovingProductPort {
    fun removeProduct(productId: String)
}