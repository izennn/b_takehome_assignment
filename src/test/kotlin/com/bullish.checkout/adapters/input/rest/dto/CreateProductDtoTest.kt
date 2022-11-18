package com.bullish.checkout.adapters.input.rest.dto

import com.bullish.checkout.TestUtils.SAMPLE_PRODUCT_DESCR
import com.bullish.checkout.TestUtils.SAMPLE_PRODUCT_NAME
import com.bullish.checkout.TestUtils.SAMPLE_PRODUCT_PRICE
import com.bullish.checkout.domain.models.CreateProductDtoBuilder
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@QuarkusTest
class CreateProductDtoTest {
    @Test
    fun `when converting product DTO to product model, an UUID is added`() {
        //when
        val actual = SAMPLE_DTO.toDomain()
        //then
        Assertions.assertNotNull(actual.id)
        Assertions.assertEquals(SAMPLE_PRODUCT_NAME, actual.name)
        Assertions.assertEquals(SAMPLE_PRODUCT_DESCR, actual.description)
        Assertions.assertEquals(SAMPLE_PRODUCT_PRICE, actual.price)
    }

    companion object {
        val SAMPLE_DTO = CreateProductDtoBuilder.build()
    }
}