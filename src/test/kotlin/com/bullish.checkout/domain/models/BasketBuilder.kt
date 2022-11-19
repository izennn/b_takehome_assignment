package com.bullish.checkout.domain.models

object BasketBuilder {
    private var basket: Basket = default()

    fun setProductCount(newProductCount: MutableMap<String, Int>): BasketBuilder {
        basket.productCount = newProductCount
        return this
    }

    fun build() : Basket {
        return basket
    }

    private fun default() = Basket(
        productCount = mutableMapOf()
    )
}