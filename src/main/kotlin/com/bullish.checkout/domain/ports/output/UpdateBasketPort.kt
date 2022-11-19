package com.bullish.checkout.domain.ports.output

import com.bullish.checkout.domain.models.Basket

interface UpdateBasketPort {
    fun updateBasket(newBasket: Basket): Basket
    fun addProduct(productId: String, count: Int)
    fun removeProduct(productId: String, count: Int)
}