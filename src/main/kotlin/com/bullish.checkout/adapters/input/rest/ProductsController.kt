package com.bullish.checkout.adapters.input.rest

import com.bullish.checkout.adapters.input.rest.dto.CreateProductDto
import com.bullish.checkout.domain.models.Product
import com.bullish.checkout.domain.service.ProductService
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/products")
class ProductsController @Inject constructor(
    val server: ProductService
) {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun listProducts() {
        logger.info("action=listing all products")
    }

    @GET
    @Path("{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getProduct(
        @PathParam("productId") productId: String
    ) {
        logger.info("action=retrieving product with productId={}", productId)
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createProduct(createProductDto: CreateProductDto): Product {
        logger.info("action=creating product={}", createProductDto.toString())
        return server.createProduct(createProductDto)
    }

    @DELETE
    @Path("{productId}")
    fun deleteProduct(
        @PathParam("productId") productId: String
    ) {
        logger.info("action=deleting product with productId={}", productId)
        server.deleteProduct(productId)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ProductsController::class.java)
    }
}