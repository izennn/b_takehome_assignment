package com.bullish.checkout.adapters.input.rest.dto

import com.bullish.checkout.TestUtils.SAMPLE_PRODUCT_DESCR
import com.bullish.checkout.TestUtils.SAMPLE_PRODUCT_NAME
import com.bullish.checkout.TestUtils.SAMPLE_PRODUCT_PRICE
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.inject.Inject

@QuarkusTest
class CreateProductDtoTest {
    @Inject
    private lateinit var createProductDtoBuilder: CreateProductDtoBuilder

    @Test
    fun `when converting product DTO to product model, an UUID is added`() {
        //when
        val actual = createProductDtoBuilder.build().toDomain()
        //then
        Assertions.assertNotNull(actual.id)
        Assertions.assertEquals(SAMPLE_PRODUCT_NAME, actual.name)
        Assertions.assertEquals(SAMPLE_PRODUCT_DESCR, actual.description)
        Assertions.assertEquals(SAMPLE_PRODUCT_PRICE, actual.price)
    }
}