package com.bullish.checkout.adapters.input.rest

import com.bullish.checkout.adapters.input.rest.dto.UpdateBasketDtoBuilder
import com.bullish.checkout.adapters.output.repository.InMemoryBasketRepositoryImpl
import com.bullish.checkout.domain.models.Basket
import com.bullish.checkout.domain.models.BasketBuilder
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.response.Response
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject
import javax.transaction.Transactional

@QuarkusTest
class BasketControllerIntegrationTest {
    @Inject
    private lateinit var updateBasketDtoBuilder: UpdateBasketDtoBuilder

    @Inject
    private lateinit var repository: InMemoryBasketRepositoryImpl

    @BeforeEach
    fun setUp() {
        repository.clearAll()
    }

    @Test
    fun `should be able to add product`() {
        //given
        givenBasket(SAMPLE_BASKET)
        val newBasket = updateBasketDtoBuilder
            .setProductCount(
                mutableMapOf(
                    "product-a" to 6,
                    "product-b" to 1,
                    "product-c" to 7
                )
            ).build()

        //when
        val response: Response = given()
            .contentType(ContentType.JSON)
            .and()
            .body(newBasket)
            .`when`()
            .put("/basket")

        //then
        response
            .then()
            .statusCode(200)

        val actualProductCount = repository.getBasket().productCount
        Assertions.assertEquals(3, actualProductCount.size)
        Assertions.assertEquals(6, actualProductCount["product-a"])
        Assertions.assertEquals(1, actualProductCount["product-b"])
        Assertions.assertEquals(7, actualProductCount["product-c"])
    }

    @Transactional
    private fun givenBasket(basket: Basket) {
        repository.updateBasket(basket)
    }

    companion object {
        private val basketBuilder: BasketBuilder = BasketBuilder()
        val SAMPLE_BASKET = basketBuilder
            .setProductCount(
                mutableMapOf(
                    "product-a" to 1,
                    "product-b" to 5
                )
            ).build()
    }
}