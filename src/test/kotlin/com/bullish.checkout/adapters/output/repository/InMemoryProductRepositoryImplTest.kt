package com.bullish.checkout.adapters.output.repository

import com.bullish.checkout.domain.models.Product
import com.bullish.checkout.mockDB.ProductDB
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.BadRequestException

@QuarkusTest
class InMemoryProductRepositoryImplTest {
    @Inject
    private lateinit var database: ProductDB

    @Inject
    private lateinit var implementer: InMemoryProductRepositoryImpl

    @BeforeEach
    fun setUp() {
        database.clearDB()
    }

    @Test
    fun `should be able to insert a product that does not exist yet`() {
        //given
        givenProductsExist(SAMPLE_PRODUCT_A, SAMPLE_PRODUCT_B)
        val newProduct = Product("new-product", "New Product", "Cool Thing", 1999.99)
        //when
        implementer.insertProduct(newProduct)
        //then
        val expectedSize = database.listAllProducts().size
        Assertions.assertEquals(expectedSize, 3)
    }

    @Test
    fun `should throw error when creating an already existing product`() {
        assertThrows<BadRequestException> {
            //given
            givenProductsExist(SAMPLE_PRODUCT_A, SAMPLE_PRODUCT_B)
            //when
            implementer.insertProduct(SAMPLE_PRODUCT_A)
        }
    }

    @Transactional
    private fun givenProductsExist(vararg products: Product) {
        database.addProductsToDB(products.asList())
    }

    companion object {
        private val SAMPLE_PRODUCT_A = Product("sample-product-a", "Dishwasher", "Something to wash your dishes", 599.99)
        private val SAMPLE_PRODUCT_B = Product("sample-product-b", "Water", "Quench your thirst with this beverage", 1.99)
    }
}