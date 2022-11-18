package com.bullish.checkout.adapters.output.repository

import com.bullish.checkout.domain.models.Product
import com.bullish.checkout.domain.ports.output.InsertingProductPort
import com.bullish.checkout.domain.ports.output.RemovingProductPort
import com.bullish.checkout.mockDB.ProductDB
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.BadRequestException

@ApplicationScoped
class InMemoryProductRepositoryImpl @Inject constructor(
    private val database: ProductDB
) : InsertingProductPort, RemovingProductPort {
    fun clearAll() {
        database.clearDB()
    }

    fun listAll(): List<Product> {
        return database.listAllProducts()
    }
    override fun insertProduct(product: Product) {
        if (database.findById(product.id) != null) {
            throw BadRequestException("Product with productId=${product.id} already exists")
        }

        database.addProductToDB(product)
    }

    override fun removeProduct(productId: String) {
        if (database.findById(productId) == null) {
            throw BadRequestException("Product with productId=${productId} does not exists")
        }

        database.removeProductFromDB(productId)
    }
}