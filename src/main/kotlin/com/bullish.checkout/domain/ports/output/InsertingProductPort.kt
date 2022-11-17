package com.bullish.checkout.domain.ports.output

import com.bullish.checkout.domain.models.Product

interface InsertingProductPort {
    fun insertProduct(product: Product)
}