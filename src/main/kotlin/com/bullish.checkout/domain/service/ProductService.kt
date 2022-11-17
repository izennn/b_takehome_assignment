package com.bullish.checkout.domain.service

import com.bullish.checkout.domain.models.Product
import com.bullish.checkout.domain.ports.input.CreatingProductUseCase
import com.bullish.checkout.domain.ports.input.DeletingProductUseCase
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ProductService () : CreatingProductUseCase, DeletingProductUseCase {
    override fun createProduct(product: Product) {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(productId: String) {
        TODO("Not yet implemented")
    }
}