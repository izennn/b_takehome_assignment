package com.bullish.checkout.domain.service

import com.bullish.checkout.adapters.input.rest.dto.UpdateBasketDtoBuilder
import com.bullish.checkout.adapters.output.repository.InMemoryBasketRepositoryImpl
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@QuarkusTest
class BasketServiceTest {
    @Inject
    private lateinit var repository: InMemoryBasketRepositoryImpl

    @Inject
    private lateinit var service: BasketService

    @BeforeEach
    fun setUp() {
        repository.clearAll()
    }

    @Test
    fun `modifies basket contents`() {
        //given
        val updateDto = UpdateBasketDtoBuilder
            .set(mutableMapOf(
                "product-a" to 5,
                "product-b" to 1,
                "product-c" to 9
            ))
            .build()

        //when
        service.modifyBasket(updateDto)
        //then
        val expectedProductsSize = 3
        val actualSize = repository.getBasket().productCount.size
        Assertions.assertEquals(expectedProductsSize, actualSize)
    }
}