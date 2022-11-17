package com.bullish.checkout.adapters.input.rest

import io.quarkus.test.junit.QuarkusTest
import io.smallrye.common.constraint.Assert.assertTrue
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test
import org.hamcrest.CoreMatchers.`is`

@QuarkusTest
class HealthCheckControllerIntegrationTest {
    // mock the greeting service

    @Test
    fun `returns greeting on health check`() {
        //given
        given()
            //when
            .`when`()
            .get("/hello")
            //then
            .then()
            .statusCode(200)
            .body(`is`("hello"))
    }
}