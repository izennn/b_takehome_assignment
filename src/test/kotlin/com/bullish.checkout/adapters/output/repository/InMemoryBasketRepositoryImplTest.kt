package com.bullish.checkout.adapters.output.repository

import com.bullish.checkout.mockDB.BasketDB
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.*
import javax.inject.Inject

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InMemoryBasketRepositoryImplTest {
    @Inject
    private lateinit var database: BasketDB

    @Inject
    private lateinit var implementer: InMemoryBasketRepositoryImpl

    @BeforeEach
    fun setUp() {
        database.clearDB()
    }

    @AfterAll
    fun tearDown() {
        database.clearDB()
    }

    @Test
    fun `add a new product`() {
        //when
        implementer.addProduct("product-a", 5)
        //then
        val actualCount = database.getProductCount()
        Assertions.assertEquals(1, database.getProductCount().size)
        Assertions.assertEquals(5, actualCount["product-a"])
    }

    @Test
    fun `increase a product count`() {
        //given
        givenBasketHasProducts(mutableMapOf("product-a" to 5, "product-b" to 2))
        //when
        implementer.addProduct("product-a", 5)
        //then
        val actualCount = database.getProductCount()
        Assertions.assertEquals(10, actualCount["product-a"])
    }

    @Test
    fun `remove a product`() {
        //given
        givenBasketHasProducts(mutableMapOf("product-a" to 5, "product-b" to 2))
        //when
        implementer.removeProduct("product-b", 3)
        //then
        val actualCount = database.getProductCount()
        Assertions.assertEquals(1, actualCount.keys.size)
    }

    @Test
    fun `decrease a product count`() {
        //given
        givenBasketHasProducts(mutableMapOf("product-a" to 5, "product-b" to 2))
        //when
        implementer.removeProduct("product-a", 3)
        //then
        val actualCount = database.getProductCount()
        Assertions.assertEquals(2, actualCount["product-a"])
    }

    private fun givenBasketHasProducts(productsCount: MutableMap<String, Int>) {
        database.updateProductCount(productsCount)
    }
}