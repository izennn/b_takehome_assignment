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
    fun `add products to the basket`() {
        //given
        //when
        implementer.addProduct("product-a", 5)
        //then
        Assertions.assertEquals(1, database.getProductCount().size)
    }

    @Test
    fun `remove products from the basket`() {
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