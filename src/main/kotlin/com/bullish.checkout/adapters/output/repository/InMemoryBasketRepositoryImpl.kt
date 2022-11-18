package com.bullish.checkout.adapters.output.repository

import com.bullish.checkout.domain.ports.output.UpdateBasketPort
import com.bullish.checkout.mockDB.BasketDB
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class InMemoryBasketRepositoryImpl @Inject constructor(
    private val database: BasketDB
) : UpdateBasketPort {
    fun clearAll() {
        database.clearDB()
    }

    fun addProduct(productId: String, count: Int) {
        database.addProductCount(productId, count)
    }

    fun removeProduct(productId: String, count: Int) {
        database.removeProductCount(productId, count)
    }

    fun updateProductCount(newProductCount: Map<String, Int>) {
        database.updateProductCount(newProductCount)
    }
}