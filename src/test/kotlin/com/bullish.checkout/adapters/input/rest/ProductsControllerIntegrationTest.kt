package com.bullish.checkout.adapters.input.rest

import com.bullish.checkout.adapters.output.repository.InMemoryProductRepositoryImpl
import com.bullish.checkout.domain.models.CreateProductDtoBuilder
import com.bullish.checkout.domain.models.Product
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.response.Response
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject
import javax.transaction.Transactional

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
        val productADto = CreateProductDtoBuilder
            .set(name = SAMPLE_PRODUCT_A.name)
            .set(description = SAMPLE_PRODUCT_A.description)
            .set(price = SAMPLE_PRODUCT_A.price)
            .build()
        givenProductsExist(SAMPLE_PRODUCT_B)

        //when
        val response: Response = given()
            .contentType(ContentType.JSON)
            .and()
            .body(productADto)
            .`when`()
            .post("/products")

        //then
        response
            .then()
            .statusCode(200)
            .body(
                "id", notNullValue(),
                "name", `is`(SAMPLE_PRODUCT_A.name),
                "description", `is`(SAMPLE_PRODUCT_A.description),
                "price.toString()", `is`(SAMPLE_PRODUCT_A.price.toString()),
            )

        Assertions.assertEquals(repository.listAll().size, 2)
    }

    @Test
    fun `should be able to delete existing product`() {
        //given
        givenProductsExist(SAMPLE_PRODUCT_A, SAMPLE_PRODUCT_B)

        //when
        val response: Response = given()
            .`when`()
            .delete("/products/${SAMPLE_PRODUCT_B.id}")

        //then
        response
            .then()
            .statusCode(204)
        Assertions.assertEquals(1, repository.listAll().size)
    }

    @Test
    fun `should throw error when adding existing product`() {
        //given
        givenProductsExist(SAMPLE_PRODUCT_A, SAMPLE_PRODUCT_B)
        val createProductDto = CreateProductDtoBuilder
            .set(name = SAMPLE_PRODUCT_A.name)
            .set(description = SAMPLE_PRODUCT_A.description)
            .set(price = SAMPLE_PRODUCT_A.price)
            .build()

        //when
        val response: Response = given()
            .contentType(ContentType.JSON)
            .and()
            .body(createProductDto)
            .`when`()
            .post("/products")

        //then
        //TODO: create global web exception mapper
        response
            .then()
            .statusCode(400)
    }

    @Test
    fun `should throw error when deleting non-existent product`() {
        //when
        val response: Response = given()
            .`when`()
            .delete("/products/${SAMPLE_PRODUCT_B.id}")

        //then
        response
            .then()
            .statusCode(400)
    }

    @Transactional
    private fun givenProductsExist(vararg products: Product) {
        for (product in products) {
            repository.insertProduct(product)
        }
    }

    companion object {
        private val SAMPLE_PRODUCT_A = Product("sample-product-a", "Dishwasher", "Something to wash your dishes", 599.99)
        private val SAMPLE_PRODUCT_B = Product("sample-product-b", "Water", "Quench your thirst with this beverage", 1.99)
    }
}