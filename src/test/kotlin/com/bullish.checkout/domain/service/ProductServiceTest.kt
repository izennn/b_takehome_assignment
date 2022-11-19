package com.bullish.checkout.domain.service

import com.bullish.checkout.adapters.input.rest.dto.CreateProductDtoBuilder
import com.bullish.checkout.adapters.output.repository.InMemoryProductRepositoryImpl
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

    @Test
    fun `can create product`() {
        val createProductDto = CreateProductDtoBuilder.build()
        server.createProduct(createProductDto)
        Assertions.assertEquals(1, repository.listAll().size)
    }

    // deleteProduct method is just invoking repository method .removeProduct
    // hence for the sake of time will skip that testing as it's already covered in controller integration test
}
