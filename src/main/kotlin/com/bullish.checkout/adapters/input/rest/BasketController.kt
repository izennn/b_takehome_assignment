package com.bullish.checkout.adapters.input.rest

import com.bullish.checkout.adapters.input.rest.dto.UpdateBasketDto
import com.bullish.checkout.domain.models.Basket
import com.bullish.checkout.domain.service.BasketService
import com.bullish.checkout.domain.service.CheckoutService
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/basket")
class BasketController @Inject constructor(
    private val basketService: BasketService,
    private val checkoutService: CheckoutService
) {
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun modifyBasket(@RequestBody updateBasketDto: UpdateBasketDto): Basket {
        logger.info("action=modifying basket, payload={}", updateBasketDto.toString())
        return basketService.modifyBasket(updateBasketDto)
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