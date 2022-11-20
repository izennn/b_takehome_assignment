package com.bullish.checkout.domain.service

import com.bullish.checkout.adapters.input.rest.dto.CreateDiscountDtoBuilder
import com.bullish.checkout.adapters.output.repository.InMemoryBasketRepositoryImpl
import com.bullish.checkout.adapters.output.repository.InMemoryDiscountRepositoryImpl
import com.bullish.checkout.adapters.output.repository.InMemoryProductRepositoryImpl
import com.bullish.checkout.domain.models.BasketBuilder
import com.bullish.checkout.domain.models.Product
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.*

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CheckoutServiceTest {
    private val productRepositoryImpl = mockk<InMemoryProductRepositoryImpl>()
    private val basketRepositoryImpl = mockk<InMemoryBasketRepositoryImpl>()
    private val discountRepositoryImpl = mockk<InMemoryDiscountRepositoryImpl>()
    private val discountDtoBuilder: CreateDiscountDtoBuilder = CreateDiscountDtoBuilder()
    private val basketBuilder: BasketBuilder = BasketBuilder()
    private val checkoutService: CheckoutService =
        CheckoutService(basketRepositoryImpl, productRepositoryImpl, discountRepositoryImpl)

    @BeforeAll
    fun setUp() {
        MockKAnnotations.init(this)
        every { productRepositoryImpl.getByProductId(PRODUCT_A.id) } returns PRODUCT_A
        every { productRepositoryImpl.getByProductId(PRODUCT_B.id) } returns PRODUCT_B
        every { productRepositoryImpl.getByProductId(PRODUCT_C.id) } returns PRODUCT_C
        every { discountRepositoryImpl.getDiscount(PRODUCT_A.id) } returns DISCOUNT_A
        every { discountRepositoryImpl.getDiscount(PRODUCT_B.id) } returns null
        every { discountRepositoryImpl.getDiscount(PRODUCT_C.id) } returns null
    }

    @Test
    fun `should be able to checkout (without discounts)`() {
        //given
        every { basketRepositoryImpl.getBasket() } returns basketBuilder
            .setProductCount(
                mutableMapOf(
                    PRODUCT_B.id to 1,
                    PRODUCT_C.id to 10
                )
            )
            .build()
        //when
        val actual = checkoutService.checkout()
        //then
        Assertions.assertEquals(PRODUCT_B.price * 1 + PRODUCT_C.price * 10, actual.price)
    }

    @Test
    fun `should be able to checkout, with one discount`() {
        //given
        every { basketRepositoryImpl.getBasket() } returns basketBuilder
            .setProductCount(
                mutableMapOf(
                    PRODUCT_A.id to 5,
                    PRODUCT_B.id to 1,
                    PRODUCT_C.id to 10,
                )
            )
            .build()

        //when
        val actual = checkoutService.checkout()

        //then
        val expectedPrice = (
                PRODUCT_A.price * 5 - (PRODUCT_A.price * DISCOUNT_A.discountInPercentage * 0.01 * 5.floorDiv(DISCOUNT_A.productCount))
                        + PRODUCT_B.price * 1
                        + PRODUCT_C.price * 10
                )
        Assertions.assertEquals(expectedPrice, actual.price)
    }

    @Test
    fun `should be able to checkout, with one discount and that discount is reoccurring`() {
    }

    @Test
    fun `should be able to checkout, with multiple discounts`() {
    }

    companion object {
        private val discountDtoBuilder: CreateDiscountDtoBuilder = CreateDiscountDtoBuilder()

        val PRODUCT_A = Product(id = "product-a", name = "Product A", description = null, price = 1.99)
        val PRODUCT_B = Product(id = "product-b", name = "Product B", description = null, price = 599.99)
        val PRODUCT_C = Product(id = "product-c", name = "Product C", description = "Some descr. of C", price = 60.00)
        val DISCOUNT_A = discountDtoBuilder
            .setId(PRODUCT_A.id)
            .setProductCount(5)
            .setDiscountInPercentage(10)
            .setDescription("Buy 5 get 10 per cent off")
            .build()
            .toDomain()
    }
}