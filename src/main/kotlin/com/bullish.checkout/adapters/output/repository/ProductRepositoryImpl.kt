package com.bullish.checkout.adapters.output.repository

import com.bullish.checkout.domain.models.Product
import com.bullish.checkout.domain.ports.output.InsertingProductPort
import com.bullish.checkout.domain.ports.output.RemovingProductPort

class ProductRepositoryImpl() : InsertingProductPort, RemovingProductPort {
//    fun findProduct(productId: String): Product {
//        return found product
//    }

    override fun insertProduct(product: Product) {
        TODO("Not yet implemented")
    }

    override fun removeProduct(productId: String) {
        TODO("Not yet implemented")
    }
}