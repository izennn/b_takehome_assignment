package com.bullish.checkout.adapters.input.rest

import com.bullish.checkout.adapters.output.repository.InMemoryProductRepositoryImpl
import com.bullish.checkout.domain.models.CreateProductDtoBuilder
import com.bullish.checkout.domain.models.Product
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.response.Response
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@QuarkusTest
class ProductsControllerIntegrationTest {
    @Inject
    private lateinit var repository: InMemoryProductRepositoryImpl

    @BeforeEach
    fun setUp() {
        repository.clearAll()
    }

    @Test
    fun `should be able to add a product`() {
        //given
        val createProductADto = CreateProductDtoBuilder
            .set(name = SAMPLE_PRODUCT_A.name)
            .set(description = SAMPLE_PRODUCT_A.description)
            .set(price = SAMPLE_PRODUCT_A.price)
            .build()

        val response: Response = given()
            .contentType(ContentType.JSON)
            .and()
            .body(createProductADto)
        //when
            .`when`()
            .post("/products")
            .then()
            .extract().response()

        //then
        Assertions.assertEquals(200, response.statusCode)
        Assertions.assertEquals(repository.listAll().size, 1)
    }

    @Test
    fun `should be able to delete existing product`() {}

    @Test
    fun `should throw error when adding existing product`() {}

    @Test
    fun `should throw error when deleting non-existent product`() {}



    companion object {
        private val SAMPLE_PRODUCT_A = Product("sample-product-a", "Dishwasher", "Something to wash your dishes", 599.99)
        private val SAMPLE_PRODUCT_B = Product("sample-product-b", "Water", "Quench your thirst with this beverage", 1.99)
    }
}