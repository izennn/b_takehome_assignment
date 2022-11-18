package com.bullish.checkout.domain.service

import com.bullish.checkout.adapters.output.repository.InMemoryProductRepositoryImpl
import com.bullish.checkout.domain.models.CreateProductDtoBuilder
import com.bullish.checkout.domain.models.Product
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import io.quarkiverse.test.junit.mockk.InjectMock
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@QuarkusTest
class ProductServiceTest {
    @InjectMock
    private lateinit var repository: InMemoryProductRepositoryImpl

    @Inject
    private lateinit var server: ProductService

    @BeforeEach
    fun setUp() {
        every { repository.insertProduct(SAMPLE_PRODUCT_A) } just runs
    }

    // all the logic tested in ProductService is already covered in the repository implementation
    // hence will only ensure that it runs okay
    @Test
    fun `can create product`() {
        val createProductDto = CreateProductDtoBuilder.build()
        server.createProduct(createProductDto)
        verify(exactly = 1) { repository.insertProduct(SAMPLE_PRODUCT_A) }
    }

    companion object {
        private val SAMPLE_PRODUCT_A = Product("sample-product-a", "Dishwasher", "Something to wash your dishes", 599.99)
    }
}
