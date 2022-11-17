package com.bullish.checkout.adapters.input.rest

import com.bullish.checkout.domain.models.Basket
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import org.slf4j.LoggerFactory
import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/basket")
class BasketController {
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    fun modifyBasket(@RequestBody basket: Basket) {
        logger.info("action=modifying basket")
    }

    @GET
    @Path("/checkout")
    fun checkout() {
        logger.info("action=checking out basket, calculating price")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(BasketController::class.java)
    }
}