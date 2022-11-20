package com.bullish.checkout.domain.service

import com.bullish.checkout.adapters.output.repository.InMemoryBasketRepositoryImpl
import com.bullish.checkout.adapters.output.repository.InMemoryProductRepositoryImpl
import com.bullish.checkout.domain.models.BasketBuilder
import com.bullish.checkout.domain.models.Product
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.*

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CheckoutServiceTest {
    private val productRepositoryImpl = mockk<InMemoryProductRepositoryImpl>()
    private val basketRepositoryImpl = mockk<InMemoryBasketRepositoryImpl>()
    private val basketBuilder: BasketBuilder = BasketBuilder()
    private val checkoutService: CheckoutService = CheckoutService(basketRepositoryImpl, productRepositoryImpl)

    @BeforeAll
    fun setUp() {
        MockKAnnotations.init(this)
        every { productRepositoryImpl.getByProductId(PRODUCT_A.id) } returns PRODUCT_A
        every { productRepositoryImpl.getByProductId(PRODUCT_B.id) } returns PRODUCT_B
        every { basketRepositoryImpl.getBasket() } returns basketBuilder
            .setProductCount(
                mutableMapOf(
                    PRODUCT_A.id to 5,
                    PRODUCT_B.id to 1
                )
            )
            .build()
    }

    @BeforeEach
    fun setUpBeforeEach() {}

    @Test
    fun `should be able to checkout without discounts`() {
        //when
        val actual = checkoutService.checkout()
        //then
        Assertions.assertEquals(PRODUCT_A.price * 5 + PRODUCT_B.price * 1, actual.price)
        verify(exactly = 1) { basketRepositoryImpl.getBasket() }
    }

    companion object {
        val PRODUCT_A = Product(id = "product-a", name = "Product A", description = null, price = 1.99)
        val PRODUCT_B = Product(id = "product-b", name = "Product B", description = null, price = 599.99)
    }
}