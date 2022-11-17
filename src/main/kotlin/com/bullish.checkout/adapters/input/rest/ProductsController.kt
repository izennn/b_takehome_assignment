package com.bullish.checkout.adapters.input.rest

import org.slf4j.LoggerFactory
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/products")
class ProductsController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun listProducts() {
        logger.info("action=listing all products")
    }

    @GET
    @Path("{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun listProduct(
        @PathParam("productId") productId: String
    ) {
        logger.info("action=retrieving product with productId: {}", productId)
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun createProduct() {
        logger.info("action=created product with productId={}")
    }

    @DELETE
    @Path("{productId}")
    fun deleteProduct(
        @PathParam("productId") productId: String
    ) {
        logger.info("action=deleting product with productId={}", productId)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ProductsController::class.java)
    }
}