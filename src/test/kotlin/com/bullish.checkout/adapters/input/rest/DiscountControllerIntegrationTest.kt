package com.bullish.checkout.adapters.input.rest

import com.bullish.checkout.adapters.input.rest.dto.CreateDiscountDtoBuilder
import com.bullish.checkout.adapters.output.repository.InMemoryDiscountRepositoryImpl
import com.bullish.checkout.domain.models.Discount
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject
import javax.transaction.Transactional

@QuarkusTest
class DiscountControllerIntegrationTest {
    @Inject
    private lateinit var createDiscountDto: CreateDiscountDtoBuilder

    @Inject
    private lateinit var repository: InMemoryDiscountRepositoryImpl

    @BeforeEach
    fun setUp() {
        repository.clearAll()
    }

    @Test
    fun `should be able to add a discount`() {}

    @Test
    fun `should be able to modify an existing discount`() {}

    @Test
    fun `should NOT be able to add a discount to a non-existent product`() {}

    @Test
    fun `should be able to remove a discount`() {}

    @Transactional
    private fun givenDiscountRepositoryHas(vararg discounts: Discount) {
        discounts.forEach { discount: Discount -> repository.addDiscount(discount) }
    }
}