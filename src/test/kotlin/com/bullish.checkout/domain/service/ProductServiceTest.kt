package com.bullish.checkout.domain.service

import com.bullish.checkout.adapters.output.repository.InMemoryProductRepositoryImpl
import com.bullish.checkout.domain.models.CreateProductDtoBuilder
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@QuarkusTest
class ProductServiceTest {
    @Inject
    private lateinit var repository: InMemoryProductRepositoryImpl

    @Inject
    private lateinit var server: ProductService

    @BeforeEach
    fun setUp() {
        repository.clearAll()
    }

    // all the logic tested in ProductService is already covered in the repository implementation
    // hence will only ensure that it runs okay
    @Test
    fun `can create product`() {
        val createProductDto = CreateProductDtoBuilder.build()
        server.createProduct(createProductDto)
        Assertions.assertEquals(1, repository.listAll().size)
    }
}
