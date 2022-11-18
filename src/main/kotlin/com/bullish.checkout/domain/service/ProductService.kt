package com.bullish.checkout.domain.service

import com.bullish.checkout.adapters.input.rest.dto.CreateProductDto
import com.bullish.checkout.adapters.output.repository.InMemoryProductRepositoryImpl
import com.bullish.checkout.domain.models.Product
import com.bullish.checkout.domain.ports.input.CreatingProductUseCase
import com.bullish.checkout.domain.ports.input.DeletingProductUseCase
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class ProductService @Inject constructor(
    private val repository: InMemoryProductRepositoryImpl
) : CreatingProductUseCase, DeletingProductUseCase {
    override fun createProduct(createProductDto: CreateProductDto): Product {
        val product = createProductDto.toDomain()
        repository.insertProduct(product)
        return product
    }

    override fun deleteProduct(productId: String) {
        repository.removeProduct(productId)
    }
}